package visao;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class EventoItemView extends RoundedPanel {
	public EventoItemView (String emoji, String titulo, String descricao) {
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel(emoji);
		add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("SF Pro", Font.BOLD, 35));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		add(horizontalStrut_1);
		
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		JLabel lblNewLabel = new JLabel(titulo);
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel(descricao);
		verticalBox.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(83, 83, 83));
		lblNewLabel_2.setFont(new Font("SF Compact Display", Font.PLAIN, 12));
		
		Component horizontalStrut = Box.createHorizontalGlue();
		add(horizontalStrut);
		
		JButton btnNewButton = new JButton("Editar");
		add(btnNewButton);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
	}
}
