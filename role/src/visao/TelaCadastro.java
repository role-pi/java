package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class TelaCadastro extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField nometxt;
	private JLabel InserirNome;
	private JButton BotaoEntrar;
	private JPanel panel;
	private JPanel panel_1;
	
	public TelaCadastro() {
		setBackground(SystemColor.window);
		setTitle("rolê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 322);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[grow][]"));
        
        JPanel bordaLogo = new HeaderView();
        contentPane.add(bordaLogo, "cell 0 0,grow");
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[500px,grow]", "[][372px,grow][]"));
		
		panel_1.setBackground(Color.WHITE);
		
		panel = new JPanel();
		panel_1.add(panel, "cell 0 1,grow");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		
		InserirNome = new JLabel("como você se chama?");
		InserirNome.setAlignmentY(Component.TOP_ALIGNMENT);
		InserirNome.setAlignmentX(Component.CENTER_ALIGNMENT);
		InserirNome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(InserirNome);
		InserirNome.setFont(new Font("Inter", Font.BOLD, 15));
		
		panel.setMaximumSize( new Dimension(200, 60));
		
		nometxt = new JTextField();
		nometxt.setBackground(SystemColor.menu);
		nometxt.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(nometxt);
		nometxt.setFont(new Font("Inter", Font.PLAIN, 15));
		nometxt.setColumns(10);
		
		BotaoEntrar = new JButton("cadastrar");
		panel_1.add(BotaoEntrar, "cell 0 2,alignx center,aligny center");
		BotaoEntrar.setFont(new Font("Inter", Font.BOLD, 14));
		
		BotaoEntrar.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		TelaCadastro cadastro = new TelaCadastro();
		cadastro.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		
		TelaInicial tela = new TelaInicial();
		tela.setVisible(true);
	}
}
