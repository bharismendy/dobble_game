package controller;

import model.personnal_setting;

public class controler_setting extends abstractControler {
	private personnal_setting perso_set;
	public controler_setting(personnal_setting setting_program) {
		perso_set = setting_program;
	}
	public int getTimer(){
		return perso_set.getTimer();
	}
	public int getNbVariante(){
		return perso_set.getNb_variantes();
	}
	public int getNbCarte(){
		return perso_set.getNb_carte();
	}
	public int getNbSymbCarte(){
		return perso_set.getNb_symbole_par_carte();
	}
	public void setTimer(int t){
		perso_set.setTimer(t);
	}
	public void setNbVariante(int t){
		perso_set.setNb_variantes(t);
	}
	public void setNbCarte(int t){
		perso_set.setNb_carte(t);
	}
	public void setNbSymbCarte(int t){
		perso_set.setNb_symbole_par_carte(t);
	}
	public Boolean recreate_card(){
		return perso_set.recreate_card();
	}
	public int getNbSymbole() {
		return perso_set.getNb_symbole();
	}
	public void setNbSymbole(int nb_symbole) {
		perso_set.setNb_symbole(nb_symbole);
	}
	public void default_value (){
		perso_set.default_value();
	}
}
