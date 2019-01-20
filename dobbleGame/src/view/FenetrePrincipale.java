package view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JFrame;

import controller.controller_game;

public class FenetrePrincipale extends JFrame implements observer.Observer{
	private Hashtable<String,Component> panels = new Hashtable<String,Component>();
	private String currentJpanel;

	private Accueil affichage_accueil;
	private about_us affichage_about_us;
	private rules affichage_rules;
	private gaming_area affichage_gaming_area;
	private controller_game controlerJeu;
	private defaite affichage_defaite;
	private victoire affichage_victoire;
	public FenetrePrincipale(controller_game controlerJeu) {
		this.controlerJeu = controlerJeu;
		
		//set title
		this.setTitle("The Dobble Game");
		//définition du panel par défaut
		currentJpanel = "accueil";

		//instanciation des panels
		affichage_accueil = new Accueil();
		affichage_about_us = new about_us();
		affichage_rules = new rules();
		affichage_gaming_area = new gaming_area(this.controlerJeu);
		affichage_defaite = new defaite();
		affichage_victoire = new victoire();
		
		//ajout des panels à une liste
		panels.put("accueil", affichage_accueil);
		panels.put("about_us", affichage_about_us);
		panels.put("rules", affichage_rules);
		panels.put("gaming_area", affichage_gaming_area);
		panels.put("victoire", affichage_victoire);
		panels.put("defaite", affichage_defaite);
		
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
		if (nomPanel.equals("gaming_area")) {
			affichage_gaming_area.start_timer();
		}
	}
	@Override
	public void update(String str) {
		// TODO Auto-generated method stub

	}
}
