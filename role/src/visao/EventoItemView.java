package visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.EventoDAO;
import modelo.Evento;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class EventoItemView extends RoundedPanel implements ActionListener {
	Evento event;
	
	JButton btnVisualizar;
	JButton btnRemover;
	
	MainWindow parentWindow;
	
	public EventoItemView (Evento event, MainWindow parentWindow) {
		super(event.getColor());
		this.event = event;
		this.parentWindow = parentWindow;
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnVisualizar);
		btnVisualizar.addActionListener(this);
		
		btnRemover = new JButton("Remover");
		btnRemover.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnRemover);
		btnRemover.addActionListener(this);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVisualizar) {
			JFrame tela = new EventoWindow(event, parentWindow);
			tela.setVisible(true);
		} else if (e.getSource() == btnRemover) {
			EventoDAO.getInstance().delete(event);
			parentWindow.update();
		}
	}
}
