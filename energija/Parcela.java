package energija;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {
	private char oznaka;
	private Color boja;
	public Parcela(char o,Color b) {
		setText(Character.toString(o));
		oznaka=o;
		boja=b;
		Font font=new Font("Serif",Font.BOLD,14);
		setFont(font);
		setBackground(boja);
		setAlignment(CENTER);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                  Plac plac=(Plac)getParent();
                  plac.izaberiParcelu((Parcela)e.getSource());
			}});

	}
	public void promeniBoju(Color b) {
		boja=b;
		setBackground(b);
	}
	

}
