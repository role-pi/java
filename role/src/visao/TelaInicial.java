package visao;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class TelaInicial extends JFrame {
	private JTextField textField;

	public TelaInicial() {
        setTitle("rolê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		
		try {
			Font inter = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("visao/Inter.ttf"));
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(inter);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        
        JPanel headerPanel = new HeaderView();
        contentPane.add(headerPanel, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "cell 0 1,grow");
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
//		scrollPane.setViewportBorder(null);
		
		JPanel contentPanel = new JPanel();
		scrollPane.setViewportView(contentPanel);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel event1 = new EventoItemView("✨", "Rolê na fac", "23 de maio • 7 participantes", new Color(249, 236, 170));
		JPanel event2 = new EventoItemView("🌱", "Churrasco vegano", "06 de junho • 15 participantes", new Color(212, 229, 195));
		JPanel event3 = new EventoItemView("🎡", "Parque de diversões", "12 de junho • 3 participantes", new Color(216, 197, 243));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
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
		
		contentPanel.add(event1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_1);
		contentPanel.add(event2);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_2);
		contentPanel.add(event3);
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		contentPanel.add(verticalStrut_3);
		
		JPanel newEvent = new RoundedPanel();
		contentPanel.add(newEvent);
		newEvent.setLayout(new BoxLayout(newEvent, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		textField.setToolTipText("");
		newEvent.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar");
		newEvent.add(btnNewButton);
		
		newEvent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		newEvent.setBorder(new EmptyBorder(10, 10, 10, 10));

		setVisible(true);
	}

	private void abrirTelaCadastroInsumo() {
		InsumoView telaDeInsumo = new InsumoView();
		telaDeInsumo.setVisible(true);
	}

	private void abrirTelaCadastroEvento() {
		TelaEventosTeste telaDeEventos = new TelaEventosTeste();
		telaDeEventos.setVisible(true);
	}

	public static void main(String[] args) {
		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);
	}
}
