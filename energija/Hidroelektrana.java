package energija;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {
	private int vodene;
	public Hidroelektrana(Baterija bat) {
		super('H',Color.BLUE,1500,bat);
		vodene=0;
	}
	public void postaviVode(int k) {
		vodene=k;
	}
	@Override
	public boolean proizvedi() {
		if (vodene==0)return false;
		baterija.dodajEnergiju(vodene);
		return true;
	}

}
