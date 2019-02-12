package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class paquet_carte{
	private ArrayList <carte> list_of_carte;
	private JSONParser parser = new JSONParser();
	private JSONArray map_id_image;
	private JSONArray map_variante;

	private Vector<carte> a;

	public paquet_carte() {
		list_of_carte = new ArrayList<carte>();
		a = new Vector<carte>();
	}
	public boolean estVide()
	{
		if (a.isEmpty())
			return true;
		else
			return false;
	}
	public void empiler(carte d){
		this.a.add(d);
	}

	public carte sommet(){
		if (this.a.isEmpty()==false) {
			carte d = (carte) this.a.lastElement();
			return d;
		}
		else {
			return null;
		}
	}

	public void depiler(){
		int taille = a.size();
		int taille_array = list_of_carte.size();
		list_of_carte.remove(taille_array-1);
		a.remove(taille-1);
	}

	public void viderLaPile(){
		this.a.removeAll(this.a);
	}

	public ArrayList<carte> getList_of_carte() {
		return list_of_carte;
	}

	public void set_list_of_carte() {

		//getting the card list in json format
		FileReader fr = null;
		try {
			fr = new FileReader(new File("ressources/paquet.json"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			map_id_image = (JSONArray) ((JSONObject) parser.parse(fr)).get("jeu_symboles");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			fr.close();
			fr = new FileReader(new File("ressources/paquet.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			map_variante = (JSONArray) ((JSONObject) parser.parse(fr)).get("jeu_variantes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.list_of_carte.removeAll(this.list_of_carte);
		for (int i=0; i < map_id_image.size(); i++) {
			JSONArray temp_array = (JSONArray) map_id_image.get(i);
			JSONArray temp_array_var = (JSONArray) map_variante.get(i);
			int len = temp_array.size();
			int temp_tab []= new int [len];
			for (int j=0;j<len;j++){
				temp_tab[j] = Integer.parseInt((temp_array.get(j).toString()));
			}
			int temp_tab2 []= new int [len];
			for (int j=0;j<len;j++){
				temp_tab2[j] = Integer.parseInt((temp_array_var.get(j).toString()));
			}
		    carte temp_carte = new carte(temp_tab,temp_tab2);
		    this.list_of_carte.add(temp_carte);
		    this.a.add(temp_carte);
		}
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createPile() {
		for(carte c : this.list_of_carte) {
			this.empiler(c);
		}
	}
}
