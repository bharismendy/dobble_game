package view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame implements observer.Observer{
	private Hashtable<String,Component> panels = new Hashtable<String,Component>();
	private String currentJpanel;

	private Accueil affichage_accueil;
	private about_us affichage_about_us;

	public FenetrePrincipale() {
		//set title
		this.setTitle("The Dobble Game");
		//définition du panel par défaut
		currentJpanel = "accueil";

		//instanciation des panels
		affichage_accueil = new Accueil();
		affichage_about_us = new about_us();

		//ajout des panels à une liste
		panels.put("accueil", affichage_accueil);
		panels.put("about_us", affichage_about_us);

		this.add((Component) panels.get(currentJpanel));
		this.setSize(new Dimension(800, 480));
		this.revalidate();
		this.repaint();
		this.setVisible(true);

	}
	public void changePanel(String nomPanel){
		panels.get(this.currentJpanel).setVisible(false);
		this.add(panels.get(nomPanel));
		this.currentJpanel = nomPanel;
		this.requestFocus();
		panels.get(nomPanel).revalidate();
		panels.get(nomPanel).repaint();
		panels.get(nomPanel).setVisible(true);
	}
	@Override
	public void update(String str) {
		// TODO Auto-generated method stub

	}
}
