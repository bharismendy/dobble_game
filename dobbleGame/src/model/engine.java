package model;

import java.util.Arrays;
import java.util.Vector;

import org.omg.CORBA.PRIVATE_MEMBER;

public class engine extends abstractModel{
	private paquet_carte list_of_card_pack;
	private carte current_card = null;
	private carte previous_card = null;
	private boolean gagnant;
	private boolean perdant;
	
	public engine() {
		list_of_card_pack = new paquet_carte();
		list_of_card_pack.set_list_of_carte();		
		previous_card = list_of_card_pack.sommet();
		list_of_card_pack.depiler();
		current_card = list_of_card_pack.sommet();
		list_of_card_pack.depiler();
		gagnant = false;
		perdant = false;
	}
	public void pas_avant() {
		if(list_of_card_pack.estVide()){
			this.perdant = true;
		}
		this.previous_card = this.current_card;
		this.current_card = list_of_card_pack.sommet();
		this.list_of_card_pack.depiler();
	}
	
	public carte getPrevious_card() {
		return previous_card;
	}
	public carte getCurrent_card() {
		return current_card;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	public boolean isGagnant() {
		return gagnant;
	}
	public paquet_carte getList_of_card_pack() {
		return list_of_card_pack;
	}
	public void setGagnant(boolean gagnant) {
		this.gagnant = gagnant;
	}
	public boolean isPerdant() {
		return perdant;
	}
	public void setPerdant(boolean perdant) {
		this.perdant = perdant;
	}
}
