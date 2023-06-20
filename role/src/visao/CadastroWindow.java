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
    private JPanel panel;

    public CadastroWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Cadastro");
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        contentPane.setBackground(Color.black);
        JPanel cadastroHeaderPanel = new CadastroHeaderView();
        contentPane.add(cadastroHeaderPanel, "cell 0 0,grow");
        
        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        contentPane.add(panel, "cell 0 1,grow");
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        inserirNome = new JLabel("Como podemos te chamar?");
        panel.add(inserirNome);
        inserirNome.setForeground(Color.WHITE);
        inserirNome.setHorizontalAlignment(SwingConstants.CENTER);
        inserirNome.setFont(new Font("Inter", Font.BOLD, 18));

        nometxt = new JTextField();
        panel.add(nometxt);
        nometxt.setHorizontalAlignment(SwingConstants.CENTER);
        nometxt.setFont(new Font("Inter", Font.PLAIN, 15));
        nometxt.setColumns(10);

        botaoEntrar = new JButton("Prosseguir");
        botaoEntrar.setForeground(Color.BLACK);
        panel.add(botaoEntrar);
        botaoEntrar.setFont(new Font("Inter", Font.BOLD, 14));

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