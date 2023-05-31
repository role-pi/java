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
	private JLabel inserirNome;
	private JButton BotaoEntrar;
	private JPanel painel;
	
	public TelaCadastro() {
		
		setBackground(SystemColor.window);
		setTitle("TelaCadastro");
		setResizable(false);
		setSize(new Dimension(400, 400));;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 322);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[grow][][]"));
        
      // JPanel logoLogo = new LogoView();
       // contentPane.add(logoLogo, "cell 0 0,grow");
		
		painel = new JPanel();
		painel.setBackground(SystemColor.window);
		contentPane.add(painel, "cell 0 1,grow");
		painel.setLayout(new MigLayout("", "[500px,grow]", "[][372px,grow][]"));
		
		JLabel Cadastrotxt = new JLabel("Tela de Cadastro");
		Cadastrotxt.setBackground(SystemColor.window);
		painel.add(Cadastrotxt, "cell 0 0,alignx center");
		Cadastrotxt.setFont(new Font("Inter", Font.BOLD, 30));
		
		BotaoEntrar = new JButton("Entrar");
		BotaoEntrar.setForeground(SystemColor.window);
		BotaoEntrar.setBackground(SystemColor.textHighlight);
		painel.add(BotaoEntrar, "cell 0 2,alignx center,aligny center");
		BotaoEntrar.setFont(new Font("Inter", Font.BOLD, 14));
		
		inserirNome = new JLabel("Como podemos te chamar?");
		painel.add(inserirNome, "flowx,cell 0 1,alignx center,aligny center");
		inserirNome.setBackground(SystemColor.window);
		inserirNome.setAlignmentY(Component.TOP_ALIGNMENT);
		inserirNome.setAlignmentX(Component.RIGHT_ALIGNMENT);
		inserirNome.setHorizontalAlignment(SwingConstants.CENTER);
		inserirNome.setFont(new Font("Inter", Font.BOLD, 18));
		
		nometxt = new JTextField();
		nometxt.setIgnoreRepaint(true);
		nometxt.setInheritsPopupMenu(true);
		nometxt.setDisabledTextColor(Color.LIGHT_GRAY);
		nometxt.setSelectionColor(Color.WHITE);
		nometxt.setToolTipText("");
		painel.add(nometxt, "cell 0 1,alignx center,aligny center");
		nometxt.setBackground(SystemColor.menu);
		nometxt.setHorizontalAlignment(SwingConstants.CENTER);
		nometxt.setFont(new Font("Inter", Font.PLAIN, 15));
		nometxt.setColumns(10); 
		
		contentPane.getRootPane().add(painel);
      	contentPane.setVisible(true);	
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
