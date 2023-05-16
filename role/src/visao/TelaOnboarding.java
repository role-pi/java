package visao;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaOnboarding extends JFrame {
	
	public TelaOnboarding() {
		setTitle("rolê");
		setResizable(false);
		setSize(new Dimension(400, 400));
		
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel fundo = new ImagePanel("src/visao/Background.png", new Dimension(400, 400), 0.5);
        fundo.setSize(400, 400);
		contentPane.add(fundo);
	}
	
	public static void main(String[] args) {
		TelaOnboarding tela = new TelaOnboarding();
		tela.setVisible(true);
	}
}
