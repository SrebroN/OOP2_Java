package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	private Vektor polozaj,pomeraj;
	private double R;
	public Figura(Vektor pol,Vektor pom,double r) {
		polozaj=pol;
		pomeraj=pom;
		R=r;
	}
	public Figura(Vektor pol,Vektor pom) {
		this(pol,pom,20);
	}
	public boolean vektorNalazi(Vektor v) {
		return (Math.abs(polozaj.getX()-v.getX())<=R)&&(
				Math.abs(polozaj.getY()-v.getY())<=R);
	}
	public boolean sePreklapa(Figura v) {
		if(!(v instanceof Figura))return false;
		return (Math.abs(polozaj.getX()-v.getPolozaj().getX())<=R+v.getR())&&
			   (Math.abs(polozaj.getY()-v.getPolozaj().getY())<=R+v.getR());
	}
	public abstract Color getBoja();
	public abstract void paint(Graphics g);
	public Vektor getPolozaj() {
		return polozaj;
	}
	public Vektor getPomeraj() {
		return pomeraj;
	}
	public double getR() {
		return R;
	}
}
