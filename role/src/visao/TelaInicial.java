package visao;

import javax.swing.JFrame;

import java.awt.event.ActionListener;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;

public class TelaInicial extends JFrame {

	public TelaInicial() {
        setTitle("Tela Inicial");
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
        contentPane.setLayout(new MigLayout("ins 0, wrap 1", "[500px,grow]", "[grow][grow][372px,grow]"));
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setVgap(10);
        flowLayout.setHgap(20);
        contentPane.add(panel, "cell 0 0,grow");
        
		panel.setBackground(Color.BLACK);
		panel.setMinimumSize(new Dimension(Integer.MIN_VALUE, 75));
		
		JLabel lblNewLabel = new JLabel("rolê");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 38));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, "cell 0 1 1 2,grow");
		scrollPane.setViewportBorder(null);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setBackground(Color.WHITE);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{477, 0};
		gbl_panel_1.rowHeights = new int[]{63, 63, 63, 63, 63, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new EventoItemView("✨", "Rolê na fac", "23 de maio • 7 participantes");
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		
		JPanel panel_3 = new EventoItemView("🌱", "Churrasco vegano", "06 de junho • 15 participantes");
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_1.add(panel_3, gbc_panel_3);
		
		JPanel panel_4 = new EventoItemView("🎡", "Parque de diversões", "12 de junho • 3 participantes");
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panel_1.add(panel_4, gbc_panel_4);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setOpaque(false);
//        contentPane.add(panelBotoes, "cell 0 2,grow");

		JButton btnCadastrarInsumo = new JButton("Cadastrar Insumo 🪩");
		btnCadastrarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadastroInsumo();
			}
		});
		panelBotoes.add(btnCadastrarInsumo);

		JButton btnCadastrarEvento = new JButton("Cadastrar Evento");
		btnCadastrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaCadastroEvento();
			}
		});
		panelBotoes.add(btnCadastrarEvento);

		setVisible(true);
	}

	private void abrirTelaCadastroInsumo() {
		InsumoView telaDeInsumo = new InsumoView();
		telaDeInsumo.setVisible(true);
	}

	private void abrirTelaCadastroEvento() {
		TelaEventos telaDeEventos = new TelaEventos();
		telaDeEventos.setVisible(true);
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));
		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);
	}
}
