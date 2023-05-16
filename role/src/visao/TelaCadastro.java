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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField nometxt;
	private JLabel InserirNome;
	private JButton BotaoEntrar;
	private JPanel panel_1;
	
	public TelaCadastro() {
		setBackground(SystemColor.window);
		setTitle("rolê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 322);
		
		contentPane = new JPanel() {
		  @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Image image = new ImageIcon("role/src/visao/Logo.png").getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		  }
		};
		contentPane.setBackground(SystemColor.window);
		setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[grow][]"));
        
       JPanel logoLogo = new LogoView();
        contentPane.add(logoLogo, "cell 0 0,grow");
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		contentPane.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[500px,grow]", "[][372px,grow][]"));
		
		JLabel BemVindotxt = new JLabel("Bem-vindo ao Rolê!");
		BemVindotxt.setBackground(SystemColor.window);
		panel_1.add(BemVindotxt, "cell 0 0,alignx center");
		BemVindotxt.setFont(new Font("Inter", Font.BOLD, 34));
		
		BotaoEntrar = new JButton("Entrar");
		panel_1.add(BotaoEntrar, "cell 0 2,alignx center,aligny center");
		BotaoEntrar.setFont(new Font("Inter", Font.BOLD, 14));
		
		InserirNome = new JLabel("como você se chama?");
		panel_1.add(InserirNome, "flowx,cell 0 1,alignx center,aligny center");
		InserirNome.setBackground(SystemColor.window);
		InserirNome.setAlignmentY(Component.TOP_ALIGNMENT);
		InserirNome.setAlignmentX(Component.CENTER_ALIGNMENT);
		InserirNome.setHorizontalAlignment(SwingConstants.CENTER);
		InserirNome.setFont(new Font("Inter", Font.BOLD, 20));
		
		nometxt = new JTextField();
		nometxt.setToolTipText("");
		panel_1.add(nometxt, "cell 0 1,alignx center,aligny center");
		nometxt.setBackground(SystemColor.menu);
		nometxt.setHorizontalAlignment(SwingConstants.CENTER);
		nometxt.setFont(new Font("Inter", Font.PLAIN, 20));
		nometxt.setColumns(10);
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	}
