package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.abstractControler;

public class victoire extends JPanel implements observer.Observer{
	private GridBagLayout center_layout;
	private JPanel content_panel;

	private JLabel content_to_show;
	private JButton go_back_to_menu;

	private GridLayout content_layout;

	public victoire() {
		menuListenner menusetter = new menuListenner();

		content_layout = new GridLayout(2,1);
		center_layout = new GridBagLayout();

		content_panel = new JPanel();
		content_panel.setLayout(content_layout);
		this.setLayout(center_layout);
		content_to_show = new JLabel();
		content_to_show.setText("<html><hr><br>" +
				"<h1>Vous avez gagné !!!</h1><br>" +
				"<hr></html>");

		go_back_to_menu = new JButton();
		go_back_to_menu.setName("go_back_to_menu");
		go_back_to_menu.setText("Main menu");
		go_back_to_menu.addActionListener(menusetter);

		content_panel.add(content_to_show);
		content_panel.add(go_back_to_menu);

		this.add(content_panel);

	}
	class menuListenner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		String str = ((JButton)e.getSource()).getName();
		if(str == "go_back_to_menu")	abstractControler.changePan("accueil");
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

