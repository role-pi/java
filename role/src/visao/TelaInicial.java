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
        contentPane.setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel();
        panelBotoes.setOpaque(false);
        contentPane.add(panelBotoes, BorderLayout.CENTER);

		JButton btnCadastrarInsumo = new JButton("Cadastrar Insumo");
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TelaInicial();
			}
		});
	}
}
