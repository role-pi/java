package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controle.UsuarioDAO;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class CadastroWindow extends JFrame implements ActionListener {
    private JPanel contentPane;
    private JTextField nomeTextField;
    private JButton newUsuarioButton;
    private JLabel inserirNome;
    private JPanel cadastroPanel;
    private JPanel panel;
    private JPanel usuariosPanel;
    private Component verticalStrut;
    private Component verticalStrut_1;
    
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public CadastroWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("login");
		setSize(500, 400);
        setLocationRelativeTo(null);
        setBackground(Color.black);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(0, 0, 0, 0));
        contentPane.setLayout(new MigLayout("gap rel 0, ins 0, wrap 1", "[500px,grow]", "[80px][grow]"));
        
        cadastroPanel = new JPanel();
        cadastroPanel.setBorder(new EmptyBorder(18, 18, 18, 18));
        cadastroPanel.setBounds(0, 80, 500, 192);
        cadastroPanel.setBackground(new Color(0, 0, 0, 0));
        cadastroPanel.setLayout(new BoxLayout(cadastroPanel, BoxLayout.X_AXIS));
        
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(scrollPane, "cell 0 1,grow");
		scrollPane.setViewportView(cadastroPanel);
		
        panel = new RoundedPanel();
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(new EmptyBorder(18, 18, 18, 18));
        cadastroPanel.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        inserirNome = new JLabel("selecione um usuário");
        inserirNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        inserirNome.setForeground(Color.WHITE);
        inserirNome.setHorizontalAlignment(SwingConstants.CENTER);
        inserirNome.setFont(new Font("Inter", Font.BOLD, 18));
        panel.add(inserirNome);
        
        verticalStrut_1 = Box.createVerticalStrut(20);
        panel.add(verticalStrut_1);
        
        usuariosPanel = new JPanel();
        usuariosPanel.setLayout(new BoxLayout(usuariosPanel, BoxLayout.Y_AXIS));
        usuariosPanel.setBackground(new Color(0, 0, 0, 0));
        panel.add(usuariosPanel);
        update();
        
        verticalStrut = Box.createVerticalStrut(20);
        panel.add(verticalStrut);
        
        HeaderView cadastroHeaderPanel = new HeaderView();
        cadastroHeaderPanel.setBounds(0, 0, 500, 80);
        contentPane.add(cadastroHeaderPanel, "cell 0 0,grow");
        
        JPanel newUsuario = new RoundedPanel();
        newUsuario.setBackground(new Color(95, 95, 95));
		panel.add(newUsuario);
		newUsuario.setLayout(new BoxLayout(newUsuario, BoxLayout.X_AXIS));
		
		nomeTextField = new JTextField();
		nomeTextField.setToolTipText("...ou digite um nome");
		newUsuario.add(nomeTextField);
		nomeTextField.setColumns(10);
		
		newUsuarioButton = new JButton("adicionar usuário");
		newUsuarioButton.addActionListener(this);
		newUsuarioButton.setEnabled(!nomeTextField.getText().isEmpty());
		
		nomeTextField.addCaretListener(e -> {
			newUsuarioButton.setEnabled(!nomeTextField.getText().isEmpty());
		});
		
		newUsuario.add(newUsuarioButton);
		
		newUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		newUsuario.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public static void main(String[] args) {
        CadastroWindow cadastro = new CadastroWindow();
        cadastro.setVisible(true);
    }
    
    public void update() {
    	usuarios = UsuarioDAO.getInstance().list();
		
    	usuariosPanel.removeAll();
		for (Usuario usuario : usuarios) {
			JPanel usuarioListItem = new UsuarioItemView(usuario, this);
			usuariosPanel.add(usuarioListItem);
			Component verticalStrut = Box.createVerticalStrut(5);
			usuariosPanel.add(verticalStrut);
		}

		SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == newUsuarioButton) {
			if (nomeTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, digite um nome de usuário");
			} else {
		        Usuario usuario = new Usuario(0, nomeTextField.getText(), "");
		        UsuarioDAO.getInstance().insert(usuario);
		        
		        update();
			}
        }
    }
}