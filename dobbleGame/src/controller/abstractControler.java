package controller;

import model.abstractModel;


public abstract class abstractControler {
	static protected abstractModel aff;
	static protected String ressourcesImg;

	public abstractControler() {
		try {
			if(aff==null) throw new Exception("L'objet d'accès au données n'est pas définis !\nPour ce faire, utilisez abstractControler.setModel(abstractModel objet_model);");
			if(ressourcesImg==null || ressourcesImg.compareTo("")==0) throw new Exception("Les chemins des images n'ont pas été spécifié !\nPour ce faire, utilisez abstractControler.setRessourcesImagesPath(String \"chemin/du/dossier/d'images\");");
		} catch (Exception e) {	e.printStackTrace(); }
	}
	static public void setModel(abstractModel aff) {
		abstractControler.aff = aff;
	}
	static public void setRessourcesImagesPath(String ressourcesImg) {
		abstractControler.ressourcesImg=ressourcesImg;
	}
	static public String getRessourcesImagesPath() {
		try {
			if(ressourcesImg==null || ressourcesImg.compareTo("")==0) throw new Exception("Les chemins des images n'ont pas été spécifié !\nPour ce faire, utilisez abstractControler.setRessourcesImagesPath(String \"chemin/du/dossier/d'images\");");
		} catch (Exception e) { e.printStackTrace(); }
		return ressourcesImg;
	}
	public static void reset(){
		abstractControler.aff.reset();
	}
	static public void changePan(String pan) {
		switch(pan) {
			case "accueil" :
				aff.changepan(pan);
				break;
			case "about_us" :
				aff.changepan(pan);
				break;
			case "rules":
				aff.changepan(pan);
			case "gaming_area":
				aff.changepan(pan);
				break;
			case "defaite":
				aff.changepan(pan);
				break;
			case "victoire":
				aff.changepan(pan);
				break;
			default:
			break;
		}
	}
}