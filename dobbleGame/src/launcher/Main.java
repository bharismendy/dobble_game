package launcher;

import model.engine;
import model.paquet_carte;
import model.personnal_setting;

import java.util.ArrayList;
import java.util.Hashtable;

import controller.abstractControler;
import controller.controler_setting;
import controller.controller_game;
import view.Accueil;
import view.FenetrePrincipale;

public class Main {
	private static Hashtable<Integer, String> ressourceJeu = new Hashtable<>();
	public static void main(String[] args) {
		for (int i = 1; i <= 64; i++) {
			ressourceJeu.put(i, Integer.toString(i)+".png");
		}
		// récupération des préférences perso
		personnal_setting conf = new personnal_setting();

		engine game_engine = new engine();
		/*----------------------------------------------------*/

		// Initialisation de la superclasse Controler
		abstractControler.setModel(game_engine);
		abstractControler.setRessourcesImagesPath("ressources/icon/");
		// Création et initialisations des contrôleurs

		controller_game controlerJeu = new controller_game(ressourceJeu,game_engine,conf);

		controler_setting controlerSettingd = new controler_setting(conf);
		// Création de notre fenêtre avec le contrôleur en paramètre
		FenetrePrincipale MainWindows = new FenetrePrincipale(controlerJeu,controlerSettingd);

		// Ajout de la fenêtre comme observer de notre modèle
		game_engine.addObserver(MainWindows);

		personnal_setting test = new personnal_setting();
	}
}
