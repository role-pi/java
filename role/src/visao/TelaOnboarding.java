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
import javax.swing.UIManager;
import java.awt.SystemColor;

public class TelaOnboarding extends JFrame implements ActionListener {
	public TelaOnboarding() {
		setTitle("rolê");
		setResizable(false);
		setSize(new Dimension(400, 400));
		
        JPanel contentPane = new JPanel();
    	setContentPane(contentPane);
    	contentPane.setLayout(null);
    	
    	JLabel texto = new JLabel("boas-vindas ao");
    	texto.setLocation(60, 40);
    	texto.setSize(new Dimension(400, 100));
    	texto.setForeground(Color.WHITE);
    	texto.setFont(new Font("Inter", Font.BOLD, 36));
    	contentPane.add(texto);
    	
    	JPanel logo = new ImagePanel("src/visao/Logo.png", new Dimension(83, 33), 0.0);
    	logo.setLocation (150, 110);
    	logo.setSize(new Dimension(83, 33));
    	logo.setBackground(new Color(0, 0, 0, 0));
    	contentPane.add(logo);
   	
    	JButton nextCadastro = new JButton("prosseguir");
    	nextCadastro.setForeground(Color.BLACK);
    	nextCadastro.setBackground(Color.WHITE);
    	nextCadastro.setFont(new Font("Inter", Font.BOLD, 14));
    	nextCadastro.setLocation(114, 175);
    	nextCadastro.setSize(new Dimension(154, 37));
    	contentPane.add(nextCadastro);
    	
    	nextCadastro.addActionListener(this);
        
        JPanel fundo = new ImagePanel("src/visao/Background.png", new Dimension(400, 400), 0.5);
        fundo.setSize(400, 400);
		contentPane.add(fundo);
	}
	
	public static void main(String[] args) {
		TelaOnboarding tela = new TelaOnboarding();
		tela.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		
		TelaCadastro cadastro = new TelaCadastro();
		cadastro.setVisible(true);
	}
}
