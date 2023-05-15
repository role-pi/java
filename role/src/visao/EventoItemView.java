package visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class EventoItemView extends RoundedPanel implements ActionListener {
	
	public EventoItemView (String emoji,
			String titulo,
			String descricao,
			Color color) {
		super(color);
		setBackground(new Color(245, 245, 245));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel(emoji);
		add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("SF Pro Display", Font.BOLD, 35));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(8);
		add(horizontalStrut_1);
		
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		JLabel lblNewLabel = new JLabel(titulo);
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("SF Pro Display", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel(descricao);
		verticalBox.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(83, 83, 83));
		lblNewLabel_2.setFont(new Font("SF Pro Display", Font.PLAIN, 13));
		
		Component horizontalStrut = Box.createHorizontalGlue();
		add(horizontalStrut);
		
		JButton btnNewButton = new JButton("Visualizar");
		add(btnNewButton);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
		btnNewButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame tela = new EventoView();
		tela.setVisible(true);
	}
}
