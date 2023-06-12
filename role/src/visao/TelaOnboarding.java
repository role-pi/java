package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaOnboarding extends JFrame implements ActionListener {
    public TelaOnboarding() {
        setTitle("Rolê");
        setResizable(false);
        setSize(new Dimension(400, 400));

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel texto = new JLabel("Boas-vindas ao");
        texto.setBounds(60, 40, 400, 100);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Inter", Font.BOLD, 36));
        contentPane.add(texto);

        ImageIcon logoIcon = new ImageIcon("role/src/visao/Logo.png");
        JLabel logo = new JLabel(logoIcon);
        logo.setBounds(150, 110, 83, 33);
        contentPane.add(logo);

        JButton nextCadastro = new JButton("Prosseguir");
        nextCadastro.setForeground(Color.BLACK);
        nextCadastro.setBackground(Color.WHITE);
        nextCadastro.setFont(new Font("Inter", Font.BOLD, 14));
        nextCadastro.setBounds(114, 175, 154, 37);
        nextCadastro.addActionListener(this);
        contentPane.add(nextCadastro);

        ImageIcon fundoIcon = new ImageIcon("role/src/visao/Background.png");
        JLabel fundo = new JLabel(fundoIcon);
        fundo.setBounds(0, 0, 400, 400);
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
