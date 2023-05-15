package visao;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class EventoDetailView extends RoundedPanel {
	public EventoDetailView (String emoji, String titulo, String data, Color color) {
		super(color);
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel(emoji);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("SF Pro", Font.BOLD, 50));
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel(titulo);
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.BOLD, 24));
		
		JLabel lblNewLabel_2 = new JLabel(data);
		verticalBox.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(83, 83, 83));
		lblNewLabel_2.setFont(new Font("SF Pro Display", Font.PLAIN, 15));
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
		
		JButton btnNewButton = new JButton("Editar");
		panel.add(btnNewButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
//		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
	}
}
