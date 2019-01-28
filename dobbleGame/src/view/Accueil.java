package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import observer.Observer;

import controller.abstractControler;

public class Accueil extends JPanel implements Observer{
	private JPanel content_panel;

	private GridLayout content_layout;
	private GridBagLayout center_layout;

	private JButton debut_partie;
	private	JButton about_rules;
	private JButton about_us;
	private JButton personnalize;

	public Accueil() {
		menuListenner menusetter = new menuListenner();
		//define layout
		content_layout = new GridLayout(4,1,0,10);
		center_layout = new GridBagLayout();

		content_panel = new JPanel();
		content_panel.setLayout(content_layout);

		this.setLayout(center_layout);
		//definition des bouttons
		debut_partie = new JButton();
		debut_partie.setName("gaming_area");
		debut_partie.setText("New Game");
		debut_partie.addActionListener(menusetter);


		about_rules = new JButton();
		about_rules.setName("rules");
		about_rules.setText("Rules");
		about_rules.addActionListener(menusetter);


		about_us = new JButton();
		about_us.setName("about_us");
		about_us.setText("About us");
		about_us.addActionListener(menusetter);

		personnalize = new JButton();
		personnalize.setName("personnalize");
		personnalize.setText("Personnalize");
		personnalize.addActionListener(menusetter);

		//ajout des bouttons
		content_panel.add(debut_partie);
		content_panel.add(about_rules);
		content_panel.add(about_us);
		content_panel.add(personnalize);

		//content_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		this.add(content_panel);
	}
	class menuListenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		String str = ((JButton)e.getSource()).getName();
			abstractControler.changePan(str);
		}
	}
	@Override
	public void update(String str) {
		// TODO Auto-generated method stub

	}
	@Override
	public void changePanel(String nomPanel) {
		// TODO Auto-generated method stub

	}
}
