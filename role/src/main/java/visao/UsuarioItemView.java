package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.UsuarioDAO;
import modelo.Usuario;

public class UsuarioItemView extends RoundedPanel implements ActionListener {
	Usuario usuario;
	
	JButton btnSelecionar;
	JButton btnEditar;
	JButton btnRemover;
	
	UpdatableView parentWindow;
	
	public UsuarioItemView(Usuario usuario, UpdatableView parentWindow) {
		this.usuario = usuario;
		this.parentWindow = parentWindow;
		
		setBackground(new Color(100, 100, 100));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(8);
		add(horizontalStrut_1);
		
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		JLabel lblNome = new JLabel(usuario.getNome());
		lblNome.setForeground(Color.white);
		verticalBox.add(lblNome);
		lblNome.setFont(new Font("Inter", Font.BOLD, 14));
		
		JLabel lblEmail = new JLabel(usuario.getEmail());
		lblEmail.setForeground(Color.LIGHT_GRAY);
		verticalBox.add(lblEmail);
		lblEmail.setFont(new Font("Inter", Font.BOLD, 12));
		
		Component horizontalStrut = Box.createHorizontalGlue();
		add(horizontalStrut);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnSelecionar = new JButton("selecionar");
		btnSelecionar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnSelecionar);
		btnSelecionar.addActionListener(this);
		
		btnEditar = new JButton("editar");
		btnEditar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnEditar);
		btnEditar.addActionListener(this);
		
		btnRemover = new JButton("remover");
		btnRemover.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnRemover);
		btnRemover.addActionListener(this);
		
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 125));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSelecionar) {
			UsuarioDAO.getInstance().setUsuarioCadastrado(usuario);
			JFrame tela = new MainWindow();
			setVisible(false);
			tela.setVisible(true);
		} else if (e.getSource() == btnEditar) {
			JFrame tela = new EditUsuarioWindow(usuario, parentWindow);
			setVisible(false);
			tela.setVisible(true);
		} else if (e.getSource() == btnRemover) {
			UsuarioDAO.getInstance().delete(usuario);
			parentWindow.update();
		}
	}
}
