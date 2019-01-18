package model;

import java.util.ArrayList;
import observer.Observer;
import observer.Observable;

public abstract class abstractModel implements Observable{
	protected int num = 0;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	//efface
	public abstract void reset();

	//Implementation du pattern observer
	public void addObserver (Observer obs) {
		this.listObserver.add(obs);
	}
	public void notifyObserver(String str) {
		for (Observer obs : listObserver) obs.update(str);
	}
	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}
	public void changepan(String str){
		for (Observer obs : listObserver) {
			obs.changePanel(str);
		}
	}
}