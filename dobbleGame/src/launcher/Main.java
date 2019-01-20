package launcher;

import model.engine;
import model.paquet_carte;

import java.util.ArrayList;
import java.util.Hashtable;

import controller.abstractControler;
import controller.controller_game;
import view.Accueil;
import view.FenetrePrincipale;

public class Main {
	private static Hashtable<Integer, String> ressourceJeu = new Hashtable<>();
	public static void main(String[] args) {
		ressourceJeu.put(1, "1.png");
		ressourceJeu.put(2, "2.png");
		ressourceJeu.put(3, "3.png");
		ressourceJeu.put(4, "4.png");
		ressourceJeu.put(5, "5.png");
		ressourceJeu.put(6, "6.png");
		ressourceJeu.put(7, "7.png");
		ressourceJeu.put(8, "8.png");
		ressourceJeu.put(9, "9.png");
		ressourceJeu.put(10, "10.png");
		ressourceJeu.put(11, "11.png");
		ressourceJeu.put(12, "12.png");
		ressourceJeu.put(13, "13.png");
		ressourceJeu.put(14, "14.png");
		ressourceJeu.put(15, "15.png");
		ressourceJeu.put(16, "16.png");
		ressourceJeu.put(17, "17.png");
		ressourceJeu.put(18, "18.png");
		ressourceJeu.put(19, "19.png");
		ressourceJeu.put(20, "20.png");
		ressourceJeu.put(21, "21.png");
		ressourceJeu.put(22, "22.png");
		ressourceJeu.put(23, "23.png");
		ressourceJeu.put(24, "24.png");
		ressourceJeu.put(25, "25.png");
		ressourceJeu.put(26, "26.png");
		ressourceJeu.put(27, "27.png");
		ressourceJeu.put(28, "28.png");
		ressourceJeu.put(29, "29.png");
		ressourceJeu.put(30, "30.png");
		ressourceJeu.put(31, "31.png");
		ressourceJeu.put(32, "32.png");
		engine game_engine = new engine();
		/*----------------------------------------------------*/

		// Initialisation de la superclasse Controler
		abstractControler.setModel(game_engine);
		abstractControler.setRessourcesImagesPath("ressources/icon/");
		// Création et initialisations des contrôleurs
		
		controller_game controlerJeu = new controller_game(ressourceJeu,game_engine);
		
		// Création de notre fenêtre avec le contrôleur en paramètre
		FenetrePrincipale MainWindows = new FenetrePrincipale(controlerJeu);
		
		// Ajout de la fenêtre comme observer de notre modèle
		game_engine.addObserver(MainWindows);		
	}
}
