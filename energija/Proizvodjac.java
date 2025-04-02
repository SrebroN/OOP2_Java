package energija;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable {
	protected Baterija baterija;
	private int vreme;
	private boolean radi;
	private Thread nit=new Thread(this);
	public Proizvodjac(char o,Color b,int vr,Baterija bat) {
		super(o,b);
		vreme=vr+(int)(Math.random()*300);
		baterija=bat;
		radi=true;
		nit.start();
	}
	@Override
	public void run() {
		try {
			while (!(Thread.interrupted())) {
				synchronized (this) {
					while(!radi) {wait();}
				}
					Thread.sleep(vreme);
					Color c=getForeground();
					if(proizvedi()) {
						setForeground(Color.RED);
						repaint();
					}
					Thread.sleep(300);
					setForeground(c);
					repaint();
			}
		} catch (InterruptedException e) {}
	}
	
	public abstract boolean proizvedi();
	public synchronized void zaustavi() {
		radi=false;
	}
	public synchronized void zavrsi() {nit.interrupt();}
}
