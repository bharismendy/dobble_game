package model;

public class carte {
	private int id_image[];
	private int variante[];

	public carte(int list_of_id[],int tab_variante[]) {
		id_image = list_of_id;
		variante = tab_variante;
	}
	public int[] getId_image() {
		return id_image;
	}
	public void setId_image(int[] id_image) {
		this.id_image = id_image;
	}
	public int[] getVariante() {
		return variante;
	}
	public void setVariante(int[] variante) {
		this.variante = variante;
	}
}
