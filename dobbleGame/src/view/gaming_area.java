package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.abstractControler;
import controller.controller_game;
import model.carte;

public class gaming_area extends JPanel implements observer.Observer{
	private JPanel main_panel;
	private JPanel center_panel;
	private JPanel carte_gauche_panel;
	private JPanel carte_droite_panel;
	private JPanel top_panel;
	
	private BorderLayout divider_layout;
	private GridLayout center_layout;
	private GridLayout top_layout;
	private GridLayout carte_droite_layout;
	private GridLayout carte_gauche_layout;

	private timer_shedule shedule;
	private int count = 0;
	private JLabel timer_label;
	private Timer timer;
	private JButton go_back_main_menu;
	private JLabel nb_carte_restante;
	private menuListenner menusetter;
	private clique_image action_image;
	private controller_game controlerJeu;
	
	public gaming_area(controller_game controlerJeu) {
		this.controlerJeu = controlerJeu;
		this.action_image = new clique_image();
		this.shedule = new timer_shedule();
		menusetter = new menuListenner();

		center_panel = new JPanel();
		top_panel = new JPanel();
		carte_droite_panel = new JPanel();
		carte_gauche_panel = new JPanel();
		
		//adding some border 
		carte_droite_panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
		carte_gauche_panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
		
		//defining layout
		divider_layout = new BorderLayout();
		center_layout = new GridLayout(1,2,0,0);//(rows,columns,hgaps,vgaps)
		carte_droite_layout = new GridLayout(3,4,0,0);//(rows,columns,hgaps,vgaps) -> 3 lignes de 4 images
		carte_gauche_layout = new GridLayout(3,4,0,0);//(rows,columns,hgaps,vgaps) -> 3 lignes de 4 images (différent de celui de droite car on pourra plus tard les différencier)
		top_layout = new GridLayout(0,3,0,0);
		
		//setting layout
		this.setLayout(divider_layout);
		center_panel.setLayout(center_layout);
		top_panel.setLayout(top_layout);
		carte_droite_panel.setLayout(carte_droite_layout);
		carte_gauche_panel.setLayout(carte_gauche_layout);

		//set some component for timer
		timer = new Timer(1000, this.shedule);
		timer_label = new JLabel("15 secondes");
		timer_label.setHorizontalAlignment(JLabel.CENTER);
		
		go_back_main_menu = new JButton();
		go_back_main_menu.setName("go_back_to_menu");
		go_back_main_menu.setText("Main menu");
		go_back_main_menu.addActionListener(menusetter);

		nb_carte_restante = new JLabel("15 cartes");
		nb_carte_restante.setHorizontalAlignment(JLabel.CENTER);
		
		/*call function to modify*/
		carte_droite_panel = construct_card_panel(carte_droite_panel, controller_game.getCurrentCard(),"d");
		carte_gauche_panel = construct_card_panel(carte_gauche_panel, controller_game.getPreviousCard(),"g");

		top_panel.add(timer_label);
		top_panel.add(go_back_main_menu);
		top_panel.add(nb_carte_restante);
		
		center_panel.add(carte_gauche_panel);
		center_panel.add(carte_droite_panel);
		
		
		this.add(top_panel, divider_layout.PAGE_START);
		this.add(center_panel);
	}
	
	/*-----affichage du nombre de carte restante------*/
	private JPanel construct_card_panel(JPanel panel_to_modify, carte temp_carte, String name_panel) {
		int[] temp_id = temp_carte.getId_image();
		for(int i=0;i<temp_id.length;i++) {
			Image img = null;
			try {
				img = ImageIO.read(new File(controller_game.getRessourcesImagesPath()+controller_game.getImagePath(temp_id[i])));
			} catch (IOException e) {
				e.printStackTrace();
			}
			JButton temp_button = new JButton();
			temp_button.setName(Integer.toString(temp_id[i])+"_"+name_panel);
			temp_button.addActionListener(action_image);
			temp_button.setIcon(new ImageIcon(img));
			panel_to_modify.add(temp_button);
		}
		this.nb_carte_restante.setText(Integer.toString(controller_game.get_number_of_card()+2));
		return panel_to_modify;
	}

	/*------------------------------------------------*/
	@Override
	public void update(String str) {
	}

	@Override
	public void changePanel(String nomPanel) {
		// TODO Auto-generated method stub
		
	}
	class clique_image implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton)e.getSource()).getName();
			String[] parts = str.split("_");
			int id_cliqued = Integer.parseInt(parts[0]);
			int[] temp_id=null;
			if(parts[1].equals("g")) {
				temp_id = controller_game.getCurrentCard().getId_image();
			}else {
				temp_id = controller_game.getPreviousCard().getId_image();
			}
			boolean a_trouve = false;
			for(int i=0; i<temp_id.length;i++) {
				if (id_cliqued == temp_id[i]) {
					a_trouve = true;
				}
			}
			if(a_trouve){
				if(controller_game.get_number_of_card()==0) {
					controller_game.reset_engine();
					carte_droite_panel.removeAll();
					carte_droite_panel = construct_card_panel(carte_droite_panel, controller_game.getCurrentCard(),"d");
					carte_gauche_panel.removeAll();
					carte_gauche_panel = construct_card_panel(carte_gauche_panel, controller_game.getPreviousCard(),"g");
					timer.stop();
					count = 0;
			    	reset_timer();
					abstractControler.changePan("victoire");
				}else {
					controller_game.pas_avant();
					carte_droite_panel.removeAll();
					carte_gauche_panel.removeAll();
					carte_droite_panel = construct_card_panel(carte_droite_panel, controller_game.getCurrentCard(),"d");
					carte_gauche_panel = construct_card_panel(carte_gauche_panel, controller_game.getPreviousCard(),"g");
					timer.stop();
			    	reset_timer();
			    	start_timer();
				}
			}else {
				controller_game.reset_engine();
				carte_droite_panel.removeAll();
				carte_gauche_panel.removeAll();
				carte_droite_panel = construct_card_panel(carte_droite_panel, controller_game.getCurrentCard(),"d");
				carte_gauche_panel = construct_card_panel(carte_gauche_panel, controller_game.getPreviousCard(),"g");
				timer.stop();
		    	reset_timer();
				abstractControler.changePan("defaite");
			}
		}
	}
	class timer_shedule implements ActionListener {
	      public void actionPerformed(ActionEvent evt) {
	    	  count++;
	    	  if (count<= 15) {
		    	  timer_label.setText(Integer.toString(15-count)+" secondes");
	    	  }else {
				controller_game.reset_engine();
				carte_droite_panel.removeAll();
				carte_gauche_panel.removeAll();
				carte_droite_panel = construct_card_panel(carte_droite_panel, controller_game.getCurrentCard(),"d");
				carte_gauche_panel = construct_card_panel(carte_gauche_panel, controller_game.getPreviousCard(),"g");
		    	timer.stop();
		    	reset_timer();
				abstractControler.changePan("defaite");
	    	  }
	      }
	  }
	 public void start_timer() {
		 timer.start();
		 timer_label.setText("15 secondes");
	 }
	 public void reset_timer() {
		 count = 0;
		 timer = new Timer(1000, this.shedule);
	 }
	class menuListenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		String str = ((JButton)e.getSource()).getName();
		if(str == "go_back_to_menu") {	
			controller_game.reset_engine();
			timer.stop();
			count = 0;
	    	reset_timer();
	    	abstractControler.changePan("accueil");
			}
		}
	}
}