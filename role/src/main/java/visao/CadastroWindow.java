package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controle.UsuarioManager;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;

public class CadastroWindow extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JTextField nometxt;
    private JLabel inserirNome;
    private JButton botaoEntrar;
    private JPanel painel;
    private JPanel cadastroPanel;

    public CadastroWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Cadastro");
        setSize(new Dimension(500, 300));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(Color.black);
        contentPane.setLayout(null);
        
        cadastroPanel = new JPanel();
        cadastroPanel.setBounds(0, 80, 500, 192);
        cadastroPanel.setBackground(new Color(0, 0, 0, 0));
        contentPane.add(cadastroPanel);
        cadastroPanel.setLayout(null);
        
        inserirNome = new JLabel("Como podemos te chamar?");
        inserirNome.setBounds(127, 54, 250, 23);
        cadastroPanel.add(inserirNome);
        inserirNome.setForeground(Color.WHITE);
        inserirNome.setHorizontalAlignment(SwingConstants.LEFT);
        inserirNome.setFont(new Font("Inter", Font.BOLD, 18));

        nometxt = new JTextField();
        nometxt.setBounds(95, 88, 304, 25);
        cadastroPanel.add(nometxt);
        nometxt.setHorizontalAlignment(SwingConstants.LEFT);
        nometxt.setFont(new Font("Inter", Font.PLAIN, 15));
        nometxt.setColumns(10);

        botaoEntrar = new JButton("Prosseguir");
        botaoEntrar.setBounds(176, 124, 129, 27);
        botaoEntrar.setBackground(new Color(240, 255, 240));
        botaoEntrar.setForeground(Color.BLACK);
        cadastroPanel.add(botaoEntrar);
        botaoEntrar.setFont(new Font("Inter", Font.BOLD | Font.ITALIC, 16));
        
        JTextArea inserirNome_1 = new JTextArea("Para entrar no sistema, precisamos que você insira um nome de usuário");
        inserirNome_1.setWrapStyleWord(true);
        inserirNome_1.setEditable(false);
        inserirNome_1.setBounds(62, -1, 390, 44);
        inserirNome_1.setLineWrap(true);
        inserirNome_1.setForeground(Color.WHITE);
        inserirNome_1.setFont(new Font("Inter", Font.PLAIN, 14));
        inserirNome_1.setBackground(Color.BLACK);
        cadastroPanel.add(inserirNome_1);
        
        HeaderView cadastroHeaderPanel = new HeaderView("src/main/java/visao/Usuário.png");
        cadastroHeaderPanel.setBounds(0, 0, 500, 80);
        contentPane.add(cadastroHeaderPanel);

        botaoEntrar.addActionListener(this);
    }

    public static void main(String[] args) {
        CadastroWindow cadastro = new CadastroWindow();
        cadastro.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == botaoEntrar) {
			if (nometxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, digite um nome de usuário para entrar no sistema");
			} else {
		        setVisible(false);
		        UsuarioManager.getInstance().setUsuarioCadastrado(new Usuario(nometxt.getText(), ""));
		        
				MainWindow tela = new MainWindow();
				tela.setVisible(true);
			}
        }
    }
}