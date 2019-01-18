package controller;

import model.abstractModel;


public abstract class abstractControler {
	static protected abstractModel aff;

	public abstractControler() {
	}
	/*
	static protected String ressourcesImg;
	static protected String logoFileName;
	static protected String banniereFileName;
	static protected abstractModel aff;

	public abstractControler() {
		try {
			if(aff==null) throw new Exception("L'objet d'accès au données n'est pas définis !\nPour ce faire, utilisez abstractControler.setModel(abstractModel objet_model);");
			if(ressourcesImg==null || ressourcesImg.compareTo("")==0 || banniereFileName==null || banniereFileName.compareTo("")==0) throw new Exception("Les chemins des images n'ont pas été spécifié !\nPour ce faire, utilisez abstractControler.setRessourcesImagesPath(String \"chemin/du/dossier/d'images\", String \"nom/fichier/image/logo\", String \"nom/fichier/image/bannière\");");
		} catch (Exception e) {	e.printStackTrace(); }
	}
	*/
	static public void setModel(abstractModel aff) {
		abstractControler.aff = aff;
	}
	static public void reset(){
		abstractControler.aff.reset();
	}
/*
	static public void setRessourcesImagesPath(String ressourcesImg, String logoFileName, String banniereFileName) {
		abstractControler.ressourcesImg=ressourcesImg;
		abstractControler.logoFileName=logoFileName;
		abstractControler.banniereFileName=banniereFileName;
	}
	static public String getRessourcesImagesPath() {
		try {
			if(ressourcesImg==null || ressourcesImg.compareTo("")==0) throw new Exception("Les chemins des images n'ont pas été spécifié !\nPour ce faire, utilisez abstractControler.setRessourcesImagesPath(String \"chemin/du/dossier/d'images\", String \"nom/fichier/image/logo\", String \"nom/fichier/image/bannière\");");
		} catch (Exception e) { e.printStackTrace(); }
		return ressourcesImg;
	}
	static public String getLogoPath() {
		return abstractControler.getRessourcesImagesPath()+logoFileName;
	}
	static public String getBannierePath() {
		return abstractControler.getRessourcesImagesPath()+banniereFileName;
	}
*/
	static public void changePan(String pan) {
		switch(pan) {
			case "accueil" :
				aff.changepan(pan);
				break;
			case "about_us" :
				aff.changepan(pan);
				break;
			default:
			break;
		}
	}
}