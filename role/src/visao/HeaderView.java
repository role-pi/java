package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Component;

public class HeaderView extends JPanel {
	public HeaderView() {
        
		setBackground(Color.BLACK);
		setMinimumSize(new Dimension(Integer.MIN_VALUE, 75));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setBorder(new EmptyBorder(0, 30, 10, 0));
		
//		JLabel lblNewLabel = new JLabel("rolê");
//		lblNewLabel.setFont(new Font("SF Pro Display", Font.BOLD, 38));
//		lblNewLabel.setForeground(new Color(255, 255, 255));
//		add(lblNewLabel);
		
		JPanel imagem = new ImagePanel();
		imagem.setAlignmentX(Component.LEFT_ALIGNMENT);
		imagem.setMaximumSize(new Dimension(83, 57));
		imagem.setBackground(Color.BLACK);
		add(imagem);
	}
}
