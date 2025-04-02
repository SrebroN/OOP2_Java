package energija;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {
	private int red,kolona;
	private Button dodaj;
	private Plac plac;
	private Baterija baterija;
	Panel gornjiDeo=new Panel();
	Panel upper=new Panel();
	public EnergetskiSistem(int k,int r,int kapacitet) {
		super("Energetski sistem");
		red=k;
		kolona=r;
		baterija=new Baterija(kapacitet);
		setSize(500,500);
		setResizable(false);
	//	setPreferredSize(getSize());
		popuniProzor(k,r);
		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustaviProizvodjace();
				dispose();
			}
		});
		setVisible(true);
	}
	private void popuniProzor(int k,int r) {
		dodaj=new Button("Dodaj");
		gornjiDeo.add(dodaj,CENTER_ALIGNMENT);
		add(gornjiDeo,BorderLayout.NORTH);
		//setMenuBar(getMenuBar());

		Menu meni=new Menu("meni");
		meni.add("PRVI MENI");
		meni.add("AAAAAA");
		Menu meni2=new Menu("meni2");
		MenuBar meniji=new MenuBar();
		meniji.add(meni);
		meniji.add(meni2);
		setMenuBar(meniji);
		plac=new Plac(k,r);
		plac.setVisible(true);
		plac.setPreferredSize(new Dimension(500,500));
		add(plac,BorderLayout.CENTER);
	    dodaj.addActionListener((ae->{
	    	Hidroelektrana nova=new Hidroelektrana(baterija);
	    	plac.dodajProizvodjaca(nova);
	    	revalidate();
	
	    	repaint();
	    }));	
	}
	
}
