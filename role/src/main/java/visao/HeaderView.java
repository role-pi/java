package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HeaderView extends JPanel {
	public HeaderView(String user) {
		setup(user);
	}
	
	public HeaderView() {
		setup("resources/Usuário.png");
	}
	
	private void setup(String user) {
		setBackground(Color.BLACK);
		setMinimumSize(new Dimension(Integer.MIN_VALUE, 75));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setBorder(new EmptyBorder(0, 20, 0, 20));
		
		JPanel imagem = new ImageView("resources/Logo.png", new Dimension(83, 33), 0.0);
		imagem.setAlignmentX(Component.LEFT_ALIGNMENT);
		imagem.setBackground(new Color(0, 0, 0, 0));
		add(imagem);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
		
		JPanel perfil = new ImageView(user, new Dimension(52, 52), 1.0);
		perfil.setSize(new Dimension(52, 52));
		perfil.setBackground(new Color(0, 0, 0, 0));
		add(perfil);
	}
}
