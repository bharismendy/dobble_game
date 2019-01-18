package launcher;

import model.paquet_carte;
import controller.abstractControler;
import view.Accueil;
import view.FenetrePrincipale;

public class Main {
	public static void main(String[] args) {
		paquet_carte test = new paquet_carte();
		abstractControler.setModel(test);

		FenetrePrincipale fenetre = new FenetrePrincipale();
		test.addObserver(fenetre);
	}
}
