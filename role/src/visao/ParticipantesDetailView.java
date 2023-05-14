package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class ParticipantesDetailView extends RoundedPanel {
	public ParticipantesDetailView (String emoji, String titulo, String data) {
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0,0,0,0));
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel("PARTICIPANTES");
		lblNewLabel.setForeground(SystemColor.scrollbar);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.BOLD, 17));
		
		JLabel lblNewLabel_2 = new JLabel(data);
		verticalBox.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(83, 83, 83));
		lblNewLabel_2.setFont(new Font("SF Compact Display", Font.PLAIN, 12));
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
		
		JButton btnNewButton = new JButton("Editar");
		panel.add(btnNewButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
//		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
	}
}
