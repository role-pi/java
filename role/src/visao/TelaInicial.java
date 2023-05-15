package visao;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
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

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("role/src/visao/Logo.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        
        JPanel panel = new HeaderView();
        contentPane.add(panel, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "cell 0 1,grow");
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
//		scrollPane.setViewportBorder(null);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel panel_2 = new EventoItemView("✨", "Rolê na fac", "23 de maio • 7 participantes", new Color(249, 236, 170));
		JPanel panel_3 = new EventoItemView("🌱", "Churrasco vegano", "06 de junho • 15 participantes", new Color(212, 229, 195));
		JPanel panel_4 = new EventoItemView("🎡", "Parque de diversões", "12 de junho • 3 participantes", new Color(216, 197, 243));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		panel_5.setBackground(new Color(0, 0, 0, 0));
		JLabel lblNewLabel = new JLabel("próximos rolês");
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 24));
		panel_5.add(lblNewLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut);
		
		panel_1.add(panel_2);
		panel_1.add(panel_3);
		panel_1.add(panel_4);
		
		JPanel newEventPanel = new RoundedPanel();
		panel_1.add(newEventPanel);
		newEventPanel.setLayout(new BoxLayout(newEventPanel, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		textField.setToolTipText("");
		newEventPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar");
		newEventPanel.add(btnNewButton);
		
		newEventPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		newEventPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

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
