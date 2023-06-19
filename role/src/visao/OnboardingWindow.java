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
import javax.swing.SwingConstants;

public class OnboardingWindow extends JFrame implements ActionListener {
    public OnboardingWindow() {
        setTitle("Rolê");
        setResizable(false);
        setSize(new Dimension(400, 400));

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon logoIcon = new ImageIcon("role/src/visao/Logo.png");
        JLabel logo = new JLabel(logoIcon);
        logo.setBounds(20, 32, 163, 71);
        contentPane.add(logo);

        JButton nextCadastro = new JButton("Prosseguir");
        nextCadastro.setForeground(Color.BLACK);
        nextCadastro.setBackground(Color.WHITE);
        nextCadastro.setFont(new Font("Inter", Font.BOLD, 14));
        nextCadastro.setBounds(109, 196, 154, 37);
        nextCadastro.addActionListener(this);
        contentPane.add(nextCadastro);

        ImageIcon fundoIcon = new ImageIcon("role/src/visao/Background.png");
        
        JLabel lblNewLabel = new JLabel("Boas-vindas ao seu novo aplicativo de eventos!");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Inter", Font.BOLD, 15));
        lblNewLabel.setBounds(10, 114, 408, 37);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Clique em prosseguir para ir a nossa tela de cadastro.");
        lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
        lblNewLabel_1.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 13));
        lblNewLabel_1.setBounds(20, 151, 374, 14);
        contentPane.add(lblNewLabel_1);
    }

    public static void main(String[] args) {
        OnboardingWindow tela = new OnboardingWindow();
        tela.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        TelaCadastro cadastro = new TelaCadastro();
        cadastro.setVisible(true);
    }
}
