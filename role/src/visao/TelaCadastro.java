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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class TelaCadastro extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JTextField nometxt;
    private JLabel inserirNome;
    private JButton botaoEntrar;
    private JPanel painel;

    public TelaCadastro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("TelaCadastro");
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("window"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[grow][][]"));

        painel = new JPanel();
        painel.setBackground(UIManager.getColor("window"));
        painel.setLayout(new MigLayout("", "[500px,grow]", "[][372px,grow][]"));
        contentPane.add(painel, "cell 0 0,grow");

        JLabel Cadastrotxt = new JLabel("Tela de Cadastro");
        Cadastrotxt.setBackground(UIManager.getColor("window"));
        Cadastrotxt.setFont(new Font("Inter", Font.BOLD, 30));
        painel.add(Cadastrotxt, "cell 0 0,alignx center");

        inserirNome = new JLabel("Como podemos te chamar?");
        inserirNome.setBackground(UIManager.getColor("window"));
        inserirNome.setHorizontalAlignment(SwingConstants.CENTER);
        inserirNome.setFont(new Font("Inter", Font.BOLD, 18));
        painel.add(inserirNome, "cell 0 1,alignx center,aligny center");

        nometxt = new JTextField();
        nometxt.setBackground(UIManager.getColor("menu"));
        nometxt.setHorizontalAlignment(SwingConstants.CENTER);
        nometxt.setFont(new Font("Inter", Font.PLAIN, 15));
        nometxt.setColumns(10);
        painel.add(nometxt, "cell 0 1,alignx center,aligny center");

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setForeground(UIManager.getColor("window"));
        botaoEntrar.setBackground(UIManager.getColor("textHighlight"));
        botaoEntrar.setFont(new Font("Inter", Font.BOLD, 14));
        painel.add(botaoEntrar, "cell 0 2,alignx center,aligny center");

        botaoEntrar.addActionListener(this);
    }

    public static void main(String[] args) {
        TelaCadastro cadastro = new TelaCadastro();
        cadastro.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false); 
   
        TelaCadastro cadastro = new TelaCadastro();
        cadastro.setVisible(true);
        
    	if (e.getSource() == botaoEntrar) {
			if (nometxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, digite um nome de usuário para entrar!");
			}
			
		else {
				MainWindow tela = new MainWindow();
        tela.setVisible(true);
			}
        }
    }
}