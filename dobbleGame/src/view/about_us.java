package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.abstractControler;

public class about_us extends JPanel implements observer.Observer{
	private BorderLayout content_layout;
	private JPanel content_panel;

	private JLabel content_to_show;
	private JButton go_back_to_menu;

	public about_us() {
		menuListenner menusetter = new menuListenner();

		content_layout = new BorderLayout();

		content_panel = new JPanel();
		content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.Y_AXIS));

		content_to_show = new JLabel();
		content_to_show.setText("<html><hr>Nous somme deux étudiants de master 2 informatique,<br>" +
				"ce projet a pour but d'utiliser le solveur minizinc pour reproduire le jeux dobble<br>" +
				"<hr></html>");

		go_back_to_menu = new JButton();
		go_back_to_menu.setName("go_back_to_menu");
		go_back_to_menu.setText("Main menu");
		go_back_to_menu.addActionListener(menusetter);

		content_panel.add(content_to_show);
		content_panel.add(go_back_to_menu);

		this.add(content_panel,content_layout.CENTER);

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
