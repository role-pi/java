package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaOnboarding extends JFrame implements ActionListener {
	static TelaOnboarding tela;
	
	public TelaOnboarding() {
		setTitle("rolê");
		setResizable(false);
		setSize(new Dimension(400, 400));
		
        JPanel contentPane = new JPanel();
    	setContentPane(contentPane);
    	contentPane.setLayout(null);
    	
    	JLabel texto = new JLabel("boas-vindas ao");
    	texto.setLocation(80, 60);
    	texto.setSize(new Dimension(400, 100));
    	texto.setForeground(Color.WHITE);
    	texto.setFont(new Font("SF Pro Display", Font.BOLD, 30));
    	contentPane.add(texto);
    	
    	JPanel logo = new ImagePanel("src/visao/Logo.png", new Dimension(83, 33), 0.0);
    	logo.setLocation(80, 120);
    	logo.setSize(new Dimension(83, 33));
    	logo.setBackground(new Color(0, 0, 0, 0));
    	contentPane.add(logo);
    	
    	JButton next = new JButton("prosseguir");
    	next.setLocation(80, 160);
    	next.setSize(new Dimension(83, 33));
    	contentPane.add(next);
    	
    	next.addActionListener(this);
        
        JPanel fundo = new ImagePanel("src/visao/Background.png", new Dimension(400, 400), 0.5);
        fundo.setSize(400, 400);
		contentPane.add(fundo);
	}
	
	public static void main(String[] args) {
		tela = new TelaOnboarding();
		tela.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tela.setVisible(false);
		
		TelaCadastro cadastro = new TelaCadastro();
		cadastro.setVisible(true);
	}
}
