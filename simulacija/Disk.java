package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public class Disk extends Figura {
	public Disk(Vektor pol,Vektor pom,double r) {
		super(pol,pom,r);
	}
	public Disk(Vektor pol,Vektor pom) {
		this(pol,pom,20);
	}
	@Override
	public Color getBoja() {
		return Color.BLUE;
	}
	@Override
	public void paint(Graphics g) {
		Color pretboja=g.getColor();
		g.setColor(Color.BLUE);
		int xkor[]=new int[8];
		int ykor[]=new int[8];
		for(int i=0;i<8;i++) {
			xkor[i]=(int)(getPolozaj().getX()+getR()*Math.sin(i*Math.PI/4));
			ykor[i]=(int)(getPolozaj().getY()-getR()*Math.cos(i*Math.PI/4));
		}
		g.fillPolygon(xkor, ykor, 8);
		g.setColor(pretboja);
		
	}

}
