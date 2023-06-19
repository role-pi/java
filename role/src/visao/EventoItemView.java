package visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Evento;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class EventoItemView extends RoundedPanel implements ActionListener {
	Evento event;
	
	public EventoItemView (Evento event) {
		super(event.getColor());
		this.event = event;
		
		setBackground(new Color(245, 245, 245));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel(event.getEmoji());
		add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 35));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(8);
		add(horizontalStrut_1);
		
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		JLabel lblNewLabel = new JLabel(event.getNome());
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 14));
		
		JLabel lblNewLabel_2 = new JLabel(event.descricaoSimples());
		verticalBox.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(83, 83, 83));
		lblNewLabel_2.setFont(new Font("Inter", Font.PLAIN, 13));
		
		Component horizontalStrut = Box.createHorizontalGlue();
		add(horizontalStrut);
		
		JButton btnNewButton = new JButton("Visualizar");
		add(btnNewButton);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
		btnNewButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame tela = new EventoWindow(event);
		tela.setVisible(true);
	}
}
