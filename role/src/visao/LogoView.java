package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LogoView extends JPanel {

	public LogoView() {
		
		setBackground(Color.BLACK);
		setMinimumSize(new Dimension(Integer.MIN_VALUE, 75));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setBorder(new EmptyBorder(0, 20, 0, 20));
		
		JPanel imagem = new ImageView("src/visao/Logo.png", new Dimension(83, 33), 0.0);
		imagem.setAlignmentX(Component.LEFT_ALIGNMENT);
		imagem.setBackground(new Color(0, 0, 0, 0));
		add(imagem);
	}
}
