package controller;

import java.util.Hashtable;

import model.carte;
import model.engine;

public class controller_game extends abstractControler{
	private static engine Engine;
	private static Hashtable<Integer, String> ressourceJeu;
	
	public controller_game(Hashtable<Integer, String> ressourceJeu, engine Engine) {
		super();
		this.Engine = Engine;
		this.ressourceJeu = ressourceJeu;
	}
	public static void setEngine(engine engine) {
		Engine = engine;
	}
	public static String getImagePath(int id) {
		String image_path = controller_game.ressourceJeu.get(id);
		return image_path;
	}
	public static carte getCurrentCard() {
		return controller_game.Engine.getCurrent_card();
	}
	public static carte getPreviousCard() {
		return controller_game.Engine.getPrevious_card();
	}
	public static Integer get_number_of_card() {
		return controller_game.Engine.getList_of_card_pack().getList_of_carte().size();
	}
	public static void pas_avant() {
		controller_game.Engine.pas_avant();
	}
	public static void reset_engine() {
		engine Engine = new engine();
		controller_game.setEngine(Engine);
	}
}
