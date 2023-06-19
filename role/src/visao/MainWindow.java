package visao;

import modelo.Evento;
import controle.EventoDAO;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame implements ActionListener {
	private JTextField textField;
	private JPanel contentPanel;
	private JPanel eventsPanel;
	
	private JButton newEventButton;
	
	public MainWindow() {
        setTitle("rolê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        
        JPanel headerPanel = new HeaderView();
        contentPane.add(headerPanel, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "cell 0 1,grow");
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		contentPanel = new JPanel();
		scrollPane.setViewportView(contentPanel);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel titlePanel = new JPanel();
		contentPanel.add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.setBackground(new Color(0, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("próximos rolês");
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 24));
		titlePanel.add(lblNewLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		titlePanel.add(horizontalGlue);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		contentPanel.add(verticalStrut);
		
		eventsPanel = new JPanel();
		eventsPanel.setBackground(Color.WHITE);
		eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
		update();
		contentPanel.add(eventsPanel);
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_3);
		
		JPanel newEvent = new RoundedPanel();
		contentPanel.add(newEvent);
		newEvent.setLayout(new BoxLayout(newEvent, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		textField.setToolTipText("...digite um evento");
		newEvent.add(textField);
		textField.setColumns(10);
		
		newEventButton = new JButton("Adicionar");
		newEventButton.addActionListener(this);
		newEventButton.setEnabled(!textField.getText().isEmpty());
		
		textField.addCaretListener(e -> {
			newEventButton.setEnabled(!textField.getText().isEmpty());
		});
		
		newEvent.add(newEventButton);
		
		newEvent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		newEvent.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		MainWindow tela = new MainWindow();
	}
	
	public void update() {
		List<Evento> events = EventoDAO.getInstance().list();
		eventsPanel.removeAll();
		for (Evento event : events) {
			JPanel eventListItem = new EventoItemView(event);
			eventsPanel.add(eventListItem);
			Component verticalStrut = Box.createVerticalStrut(5);
			eventsPanel.add(verticalStrut);
		}

		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newEventButton) {
			if (textField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, digite um nome para o evento.");
			} else {
				Evento event = new Evento("", new Color(200, 200, 200), textField.getText(), "", "");
				EventoDAO.getInstance().insert(event);
				System.out.print("TEST");
				update();
			}
		}
	}
}
