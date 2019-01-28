package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.EventListener;

import javax.security.auth.login.Configuration;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.DocumentEvent;

import controller.abstractControler;
import controller.controler_setting;
import controller.controller_game;

import observer.Observer;

import view.rules.menuListenner;

public class personnalize extends JPanel implements Observer{
	private JPanel panel_content;
	private GridLayout contentLayout;
	private GridBagLayout centerContentLayout;// only to center te JPanel
	private menuListenner menusetter;
	private apply_change_listenner apply_listenner;

	private JButton go_main_menu;
	private JButton apply;

	private JLabel timer_label;
	private JLabel nb_carte_label;
	private JLabel nb_symbole_par_carte_label;
	private JLabel nb_variante_label;
	private JLabel nb_symbole_label;

	private TextField timer_field;
	private TextField nb_carte_field;
	private TextField nb_symbole_par_carte_field;
	private TextField nb_variante_field;
	private TextField nb_symbole_field;

	private controler_setting my_conf;
	private Boolean redo_gen = false;
	private change_text_listenner text_list;
	private JLabel end_gen;
	private Boolean need_recreate_card = false;
	private JButton restore_default;
	private controller_game cont_game;
	private JLabel error_message;

	public personnalize(controler_setting conf, controller_game ctgame) {
		my_conf = conf;
		cont_game = ctgame;
		menusetter = new menuListenner();
		text_list = new change_text_listenner();
		apply_listenner = new apply_change_listenner();
		panel_content = new JPanel();

		//init layout
		contentLayout = new GridLayout(7,2,10,20);
		centerContentLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

		//set layout
		this.setLayout(centerContentLayout);
		panel_content.setLayout(contentLayout);

		error_message = end_gen = new JLabel();
		error_message.setText("");
		error_message.setVisible(false);
		c.gridy = GridBagConstraints.CENTER;
		centerContentLayout.setConstraints(error_message, c);

		end_gen = new JLabel();
		end_gen.setText("a new deck of card have been generated");
		end_gen.setVisible(false);
		c.gridy = GridBagConstraints.CENTER;
		centerContentLayout.setConstraints(end_gen, c);

		//init componenent
		timer_label = new JLabel();
		timer_label.setText("Nombre de seconde du timer : ");

		nb_carte_label = new JLabel();
		nb_carte_label.setText("Nombre de carte dans le jeu : ");

		nb_symbole_label = new JLabel();
		nb_symbole_label.setText("Nombre de symbole : ");

		nb_symbole_par_carte_label = new JLabel();
		nb_symbole_par_carte_label.setText("Nombre de symbole par carte : ");

		nb_variante_label = new JLabel();
		nb_variante_label.setText("Nombre de variante de taille : ");

		timer_field = new TextField();
		timer_field.setName("timer_field");
		timer_field.setText(Integer.toString(my_conf.getTimer()));
		timer_field.addTextListener(text_list);

		nb_carte_field = new TextField();
		nb_carte_field.setText(Integer.toString(my_conf.getNbCarte()));
		nb_carte_field.addTextListener(text_list);

		nb_symbole_par_carte_field = new TextField();
		nb_symbole_par_carte_field.setText(Integer.toString(my_conf.getNbSymbCarte()));
		nb_symbole_par_carte_field.addTextListener(text_list);

		nb_symbole_field = new TextField();
		nb_symbole_field.setText(Integer.toString(my_conf.getNbSymbole()));
		nb_symbole_field.addTextListener(text_list);

		nb_variante_field = new TextField();
		nb_variante_field.setText(Integer.toString(my_conf.getNbVariante()));
		nb_variante_field.addTextListener(text_list);

		apply = new JButton();
		apply.setName("apply");
		apply.setText("Apply change");
		apply.setEnabled(false);
		apply.addActionListener(apply_listenner);

		restore_default = new JButton();
		restore_default.setName("restore_default");
		restore_default.setText("Restore this default");
		restore_default.addActionListener(apply_listenner);

		go_main_menu = new JButton();
		go_main_menu.setName("go_back_to_menu");
		go_main_menu.setText("Main menu");
		go_main_menu.addActionListener(menusetter);

		panel_content.add(timer_label);
		panel_content.add(timer_field);
		panel_content.add(nb_carte_label);
		panel_content.add(nb_carte_field);
		panel_content.add(nb_symbole_par_carte_label);
		panel_content.add(nb_symbole_par_carte_field);
		panel_content.add(nb_variante_label);
		panel_content.add(nb_variante_field);
		panel_content.add(nb_symbole_label);
		panel_content.add(nb_symbole_field);
		panel_content.add(apply);
		panel_content.add(go_main_menu);
		panel_content.add(restore_default);

		this.add(panel_content);
		this.add(end_gen,c);
		this.add(error_message,c);
	}

	class menuListenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		String str = ((JButton)e.getSource()).getName();
		if(str == "go_back_to_menu")	abstractControler.changePan("accueil");
		}
	}
	class change_text_listenner implements EventListener, TextListener{
		@Override
		public void textValueChanged(TextEvent e) {
			if(Integer.parseInt(nb_variante_field.getText())>5 || Integer.parseInt(nb_variante_field.getText()) < 0){
				error_message.setVisible(true);
				error_message.setText("Error : The number of variante must be between 0 and 5");
			}else if (Integer.parseInt(nb_carte_field.getText())< 2){
				error_message.setVisible(true);
				error_message.setText("Error : You must have at least 2 cards");
			}else if (Integer.parseInt(nb_symbole_field.getText())<1 || Integer.parseInt(nb_symbole_field.getText())>32){
				error_message.setVisible(true);
				error_message.setText("Error : You must have between 1 and 32 symbole");
			}else if (Integer.parseInt(nb_symbole_par_carte_field.getText())<1){
				error_message.setVisible(true);
				error_message.setText("Error : You must have at least 1 symbole by cards");
			}else{
				if(error_message.isVisible()){
					error_message.setVisible(false);
				}
			}

			if(apply.isEnabled() == false && error_message.isVisible()==false){
				apply.setEnabled(true);
			}
			if(((TextField)e.getSource()).getName()!="timer_field" && apply.getText().contains("recreate card")==false && error_message.isVisible()==false){
				apply.setText(apply.getText()+" recreate card");
				need_recreate_card = true;
			}
		}
	}

	class apply_change_listenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		String str = ((JButton)e.getSource()).getName();
		if(str == "apply"){
			try {
				my_conf.setTimer(Integer.parseInt(timer_field.getText()));
				my_conf.setNbCarte(Integer.parseInt(nb_carte_field.getText()));
				my_conf.setNbVariante(Integer.parseInt(nb_variante_field.getText()));
				my_conf.setNbSymbCarte(Integer.parseInt(nb_symbole_par_carte_field.getText()));
				my_conf.setNbSymbole(Integer.parseInt(nb_symbole_field.getText()));
				apply.setEnabled(false);
				if(need_recreate_card){
					end_gen.setVisible(true);
					if (my_conf.recreate_card()){
						end_gen.setText("Generation went good new card are ready");
					}
					else{
						end_gen.setText("Error during the generation");
					}
					end_gen.setVisible(true);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(str == "restore_default"){
			try {
				my_conf.default_value();
				timer_field.setText(Integer.toString(my_conf.getTimer()));
				nb_carte_field.setText(Integer.toString(my_conf.getNbCarte()));
				nb_symbole_field.setText(Integer.toString(my_conf.getNbSymbole()));
				nb_symbole_par_carte_field.setText(Integer.toString(my_conf.getNbSymbCarte()));
				nb_variante_field.setText(Integer.toString(my_conf.getNbVariante()));
				my_conf.recreate_card();
				apply.setEnabled(false);
				if(need_recreate_card){
					end_gen.setVisible(true);
					if (my_conf.recreate_card()){
						end_gen.setText("Generation went good new card are ready");
						cont_game.reset_engine();
					}
					else{
						end_gen.setText("Error during the generation");
					}
					end_gen.setVisible(true);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
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

