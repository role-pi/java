package visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.EventoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Usuario;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class UsuarioItemView extends RoundedPanel implements ActionListener {
	Usuario usuario;
	
	JButton btnSelecionar;
	JButton btnRemover;
	
	CadastroWindow parentWindow;
	
	public UsuarioItemView(Usuario usuario, CadastroWindow parentWindow) {
		this.usuario = usuario;
		this.parentWindow = parentWindow;
		
		setBackground(new Color(100, 100, 100));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(8);
		add(horizontalStrut_1);
		
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		JLabel lblNewLabel = new JLabel(usuario.getNome());
		lblNewLabel.setForeground(Color.white);
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 14));
		
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
		
		btnRemover = new JButton("remover");
		btnRemover.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnRemover);
		btnRemover.addActionListener(this);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSelecionar) {
//			JFrame tela = new EventoWindow(event, parentWindow);
//			tela.setVisible(true);
		} else if (e.getSource() == btnRemover) {
			UsuarioDAO.getInstance().delete(usuario);
			parentWindow.update();
		}
	}
}
