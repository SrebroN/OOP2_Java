
package simulacija;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulacija extends Frame {
	private Scena scena=new Scena(this);
	Panel centerPanel=new Panel();
	
	public Simulacija() {
		super("Simulacija");
		setSize(700, 700);
		setResizable(true);
		popuniProzor();
		revalidate();
		pack();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key=Character.toUpperCase(e.getKeyChar());
				switch(key) {
				case KeyEvent.VK_SPACE:{
					if(scena.Radi()) scena.pauziraj();
					else scena.pokreni();
					break;
					}
				case KeyEvent.VK_ESCAPE:{
					scena.ugasi();
					dispose();
					break;
					}
				}
				repaint();
				requestFocus();
			}
			});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.ugasi();
				dispose();
			}
		});

		addComponentListener(new ComponentAdapter() {
		@Override
		public void componentResized(ComponentEvent e) {
			scena.packScene();
			scena.repaint();
			repaint();
			}
		});
		setVisible(true);
	}
	
	private void popuniProzor() {
		scena.setPreferredSize(new Dimension(700,700));
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		centerPanel.add(scena);
		scena.repaint();
		add(centerPanel,BorderLayout.CENTER);
		
	}
}
