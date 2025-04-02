package simulacija;

public class Vektor {
	private double x,y;
	public Vektor(double xx,double yy) {
		x=xx;
		y=yy;
	}
	public Vektor() {
		x=Math.random()*2-1;
		y=Math.random()*2-1;
		while (x==0 && y==0) {
			x=Math.random()*2-1;
			y=Math.random()*2-1;
		}
	}
	public Vektor jedinicniVektor() {
		double r=Math.sqrt(x*x+y*y);
		return new Vektor(x/r,y/r);
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

}
