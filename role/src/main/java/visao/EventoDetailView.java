package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import modelo.Evento;

public class EventoDetailView extends RoundedPanel implements ActionListener, UpdatableView {
	Evento evento;

	JLabel lblEmoji;
	JLabel lblNome;
	JLabel lblLocal;
	JLabel lblData;
	
	UpdatableView parentWindow;
	
	public EventoDetailView (Evento evento, UpdatableView parentWindow) {
		super(evento.getColor());
		this.evento = evento;
		this.parentWindow = parentWindow;
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0, 0, 0, 0));
		
		lblEmoji = new JLabel(evento.getEmoji());
		panel.add(lblEmoji);
		lblEmoji.setFont(new Font("Inter", Font.BOLD, 50));
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		lblNome = new JLabel(evento.getNome());
		verticalBox.add(lblNome);
		lblNome.setFont(new Font("Inter", Font.BOLD, 24));

		lblLocal = new JLabel(evento.getLocal());
		verticalBox.add(lblLocal);
		lblLocal.setForeground(new Color(0, 0, 0, 150));
		lblLocal.setFont(new Font("Inter", Font.PLAIN, 18));
		
		lblData = new JLabel();
		verticalBox.add(lblData);
		lblData.setForeground(new Color(0, 0, 0, 150));
		lblData.setFont(new Font("Inter", Font.PLAIN, 18));
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
		
		JButton btnNewButton = new JButton("editar");
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Inter", Font.PLAIN, 16));
		btnNewButton.addActionListener(this);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
		
		update();
	}
	
	public void update() {
		lblEmoji.setText(evento.getEmoji());
		lblNome.setText(evento.getNome());
		lblLocal.setText(evento.getLocal());
		lblData.setText(evento.dataCompleta());
		this.gradient = evento.getColor();
		parentWindow.update();
		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EditEventoWindow window = new EditEventoWindow(evento, this);
		window.setVisible(true);
	}
}
