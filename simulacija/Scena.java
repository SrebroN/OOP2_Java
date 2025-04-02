package simulacija;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Scena extends Canvas implements Runnable {
	private Simulacija simulacija;
	private boolean radi;
	private ArrayList<Figura> figure=new ArrayList<Figura>();
	private Thread nit=new Thread(this);
	
	public Scena(Simulacija owner) {
		setBackground(Color.GRAY);
		simulacija=owner;
		radi=false;
		nit.start();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				owner.requestFocus();
				if(Radi()==false) {
					dodajFiguru(new Disk(new Vektor(e.getX(),e.getY()),
							new Vektor()));
					repaint();	
					}
			}
		});
	}
	
	@Override
	public void run() {
		try {
			while(!(Thread.interrupted())) {
				synchronized (this) {
					while(!(Radi()))wait();
				}
				for(Figura f:figure) {
					pomeri(f);
					udaraIvicu(f);
					for(Figura f1:figure) {
						sudarFigura(f,f1);
					}
				}
				repaint();
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {}
	}
	private void pomeri(Figura f) {
		f.getPolozaj().setX(f.getPolozaj().getX()+f.getPomeraj().getX()*3);
		f.getPolozaj().setY(f.getPolozaj().getY()+f.getPomeraj().getY()*3);
		
	}
	private static void sudarFigura(Figura f1,Figura f2) {
		if(!f1.sePreklapa(f2)||f1.equals(f2))return;
		Vektor tmp=new Vektor(f1.getPomeraj().getX(),f1.getPomeraj().getY());
		f1.getPomeraj().setX(f2.getPomeraj().getX());
		f1.getPomeraj().setY(f2.getPomeraj().getY());
		f2.getPomeraj().setX(tmp.getX());
		f2.getPomeraj().setY(tmp.getY());
	}
	private void udaraIvicu(Figura f) {
		if(f.getPolozaj().getX()-f.getR()<=0||
		f.getPolozaj().getX()+f.getR()>=getWidth()) {
			f.getPomeraj().setX(f.getPomeraj().getX()*(-1));
			}
		if(f.getPolozaj().getY()-f.getR()<=0||
		f.getPolozaj().getY()+f.getR()>=getHeight()) {
			f.getPomeraj().setY(f.getPomeraj().getY()*(-1));
		}
		
	}
	public void dodajFiguru(Figura f) {
		if (f.getR()*2>=simulacija.getSize().getHeight()
				|| f.getR()*2>=simulacija.getSize().getWidth())return;
		if(f.getPolozaj().getX()-f.getR()<=0||
				f.getPolozaj().getX()+f.getR()>=getWidth())return;
		if(f.getPolozaj().getY()-f.getR()<=0||
				f.getPolozaj().getY()+f.getR()>=getHeight()) return;
		for (Figura fig:figure) {
			if (f.sePreklapa(fig))return;
		}
		figure.add(f);
	}
	
	
	public boolean Radi() {
		return radi;
	}
	public synchronized void pokreni() {radi=true;repaint();notify();}
	public synchronized void pauziraj() {radi=false;repaint();}
	@Override
	public void paint(Graphics g) {
		crtajFigure();
		crtajPauzu();
	}
	
	private void crtajPauzu() {
		if (radi==false) {
			Graphics g=getGraphics();
			Color pret=g.getColor();
			g.setFont(new Font("comic sans ms",Font.BOLD, 20));
			g.setColor(Color.BLACK);
			g.drawString("PAUZA", getWidth()/2, getHeight()/2);
			g.setColor(pret);
		}
	}
	private void crtajFigure() {
		for(Figura f:figure) {
			f.paint(getGraphics());
		}
	}
	synchronized void ugasi() {nit.interrupt();}

	public void packScene() {
		setPreferredSize(simulacija.getSize());
		
	}
}
