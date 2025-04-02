package energija;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

public class Plac extends Panel {
	private int red,kolona;
	private ArrayList<Parcela> parcele;
	private Parcela selektovana;
	
	public Plac(int ii,int jj) {
		parcele=new ArrayList<Parcela>();
		red=ii;
		kolona=jj;
		popuni();
	}
	public void dodajProizvodjaca(Proizvodjac p) {
		if(selektovana==null || !(p instanceof Proizvodjac)) return;
		/*int i=parcele.indexOf(selektovana);
		parcele.remove(i);
		
		parcele.add(i, p);
		remove(i);
		add(p,i);
		azurirajHidroelektrane();
		selektovana=null;*/
	       for(int i=0;i<parcele.size();i++) {
			    this.remove(parcele.get(i));}
			    int n=parcele.indexOf(selektovana);
			    parcele.remove(n);
		        parcele.add(n,p);
		
		        for(int i=0;i<parcele.size();i++) {
					this.add(parcele.get(i));
				}
		        azurirajHidroelektrane();
		        selektovana=null;
	}
	private void azurirajHidroelektrane() {
		int i=0;
		for(Parcela p:parcele) {
			if(p instanceof Hidroelektrana) {
				int vode=0;
				if (i-kolona>=0) {
					if(parcele.get(i-kolona) instanceof VodenaPovrs)vode++;
				}
				if(i+kolona<red*kolona) {
					if(parcele.get(i+kolona) instanceof VodenaPovrs)vode++;
				}
				if(i%kolona!=0) {
					if(parcele.get(i-1)instanceof VodenaPovrs)vode++;
				}
				if(i%kolona!=(kolona-1)) {
					if(parcele.get(i+1)instanceof VodenaPovrs)vode++;
				}
				if(i-kolona>=0 && i%kolona!=0) {
					if(parcele.get(i-kolona-1)instanceof VodenaPovrs)vode++;
				}
				if(i-kolona>=0 && i%kolona!=kolona-1) {
					if(parcele.get(i-kolona+1)instanceof VodenaPovrs)vode++;
				}
				if(i+kolona<red*kolona && i%kolona!=0) {
					if(parcele.get(i+kolona-1)instanceof VodenaPovrs)vode++;
				}
				if(i+kolona<red*kolona && i%kolona!=kolona-1) {
					if(parcele.get(i+kolona+1)instanceof VodenaPovrs)vode++;
				}
				((Hidroelektrana) p).postaviVode(vode);
			}
			i++;
		}
	}
	private void popuni() {
		setLayout(new GridLayout(red,kolona,5,5));
		for (int i=0;i<red*kolona;i++) {		
			double rand=Math.random();
			if(rand<0.3) {
				parcele.add(new VodenaPovrs());
			}
			else {
				parcele.add(new TravnataPovrs());	
			}
			add(parcele.get(i),BorderLayout.CENTER);
		}
	}
	public void zaustaviProizvodjace() {
		for(Parcela p:parcele) {
			if(p instanceof Proizvodjac) {((Proizvodjac) p).zavrsi();}
		}
	}
	public void izaberiParcelu(Parcela p) {
		Parcela stara=selektovana;
		selektovana=p;
		if(selektovana!=null) {selektovana.setFont(new Font("serif",Font.BOLD,20));}
		if(stara!=null) {stara.setFont(new Font("serif",Font.BOLD,14));}
	}
	
}
