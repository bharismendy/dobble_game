package model;

public class carte {
	private int id_image[];
	public carte(int list_of_id[]) {
		id_image = list_of_id;
	}
	public int[] getId_image() {
		return id_image;
	}
	public void setId_image(int[] id_image) {
		this.id_image = id_image;
	}
}
