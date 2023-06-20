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
    private JPanel Cadastro;

    public CadastroWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Cadastro");
        setSize(new Dimension(500, 300));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        contentPane.setBackground(Color.black);
        JPanel cadastroHeaderPanel = new CadastroHeaderView();
        contentPane.add(cadastroHeaderPanel, "cell 0 0,grow");
        
        Cadastro = new JPanel();
        Cadastro.setBackground(new Color(0, 0, 0));
        contentPane.add(Cadastro, "cell 0 1,grow");
        Cadastro.setLayout(null);
        
        inserirNome = new JLabel("Como podemos te chamar?");
        inserirNome.setBounds(150, 66, 250, 23);
        Cadastro.add(inserirNome);
        inserirNome.setForeground(Color.WHITE);
        inserirNome.setHorizontalAlignment(SwingConstants.CENTER);
        inserirNome.setFont(new Font("Inter", Font.BOLD, 18));

        nometxt = new JTextField();
        nometxt.setBounds(96, 100, 304, 25);
        Cadastro.add(nometxt);
        nometxt.setHorizontalAlignment(SwingConstants.CENTER);
        nometxt.setFont(new Font("Inter", Font.PLAIN, 15));
        nometxt.setColumns(10);

        botaoEntrar = new JButton("Prosseguir");
        botaoEntrar.setBounds(150, 136, 105, 27);
        botaoEntrar.setBackground(new Color(240, 255, 240));
        botaoEntrar.setForeground(Color.BLACK);
        Cadastro.add(botaoEntrar);
        botaoEntrar.setFont(new Font("Inter", Font.ITALIC, 14));
        
        JTextArea inserirNome_1 = new JTextArea("Para entrar no sistema, precisamos que você insira um nome de usuário...");
        inserirNome_1.setEditable(false);
        inserirNome_1.setBounds(62, 11, 356, 44);
        inserirNome_1.setWrapStyleWord(true);
        inserirNome_1.setLineWrap(true);
        inserirNome_1.setForeground(Color.WHITE);
        inserirNome_1.setFont(new Font("Inter", Font.PLAIN, 14));
        inserirNome_1.setBackground(Color.BLACK);
        Cadastro.add(inserirNome_1);

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