package energija;

public class Baterija {
	private int kapacitet,energija;
	public Baterija(int kap) {
		kapacitet=kap;
		energija=kap;
	}
	public boolean jePuna() {return kapacitet<=energija;}
	public void dodajEnergiju(int e) {
		energija+=e;
		if (jePuna()) {energija=kapacitet;}
	}
	public void isprazni() {energija=0;}
}
