package visao;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TelaOnboarding extends JFrame {
	
	public TelaOnboarding() {
		setTitle("rolê");
		setResizable(false);
		setSize(new Dimension(400, 400));
		
        JPanel contentPane = new JPanel();
        	setContentPane(contentPane);
        	contentPane.setLayout(null);
        	
        	
      //  JPanel fundo = new ImagePanel("src/visao/Background.png", new Dimension(400, 400), 0.5);
       // fundo.setSize(400, 400);
	//	contentPane.add(fundo); 
        	
        	JButton EntrarCadastro = new JButton("Entrar");
        	EntrarCadastro.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					TelaCadastro telaCad = new TelaCadastro();
					telaCad.setVisible(true);
				}
        	});
        	EntrarCadastro.setBackground(UIManager.getColor("Button.highlight"));
        	EntrarCadastro.setFont(new Font("Inter", Font.BOLD, 15));
        	EntrarCadastro.setBounds(107, 95, 173, 49);
        	contentPane.add(EntrarCadastro);
        	
        	
		}
      
	
	public static void main(String[] args) {
		TelaOnboarding tela = new TelaOnboarding();
		tela.setVisible(true);
	}
}
