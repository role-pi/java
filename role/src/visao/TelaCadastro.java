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
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField nometxt;
	private JLabel InserirNome;
	private JButton BotaoEntrar;
	
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
		setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[grow][][grow][][372px,grow][]"));
        
        JPanel bordaLogo = new HeaderView();
        contentPane.add(bordaLogo, "cell 0 0,grow");
		
		JLabel BemVindotxt = new JLabel("Bem Vindo!");
		contentPane.add(BemVindotxt, "cell 0 3,alignx center");
		BemVindotxt.setFont(new Font("Dialog", Font.BOLD, 20));
		
		InserirNome = new JLabel("Insira o seu nome:");
		InserirNome.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(InserirNome, "flowx,cell 0 4,alignx center,aligny center");
		
		BotaoEntrar = new JButton("Entrar");
		BotaoEntrar.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(BotaoEntrar, "cell 0 5,alignx center,aligny center");
		
		nometxt = new JTextField();
		nometxt.setFont(new Font("Dialog", Font.PLAIN, 15));
		contentPane.add(nometxt, "cell 0 4,alignx center,aligny center");
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
