package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.EventoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Usuario;

public class AddParticipanteWindow extends JFrame implements ActionListener {
    private JComboBox<Usuario> usuariosComboBox;
    private Evento event;
    private ParticipantesDetailView parentWindow;
    
    private List<Usuario> listaDeUsuarios;

    public AddParticipanteWindow(Evento event, ParticipantesDetailView parentWindow) {
    	this.event = event;
    	event.setUsuarios(UsuarioDAO.getInstance().list(event));
    	this.parentWindow = parentWindow;
    	
    	setTitle("adicionar participante");
	    setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panel_4 = new RoundedPanel();
        contentPane.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
        panel_4.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel_4.setSize(new Dimension(500, 100));
        panel_4.setMaximumSize(new Dimension(600, 250));
        panel_4.setFont(new Font("Inter", Font.BOLD, 13));
        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);

        DefaultComboBoxModel<Usuario> comboBoxModel = new DefaultComboBoxModel<>();
        
        usuariosComboBox = new JComboBox<>(comboBoxModel);
        panel_4.add(usuariosComboBox);
        
        listaDeUsuarios = UsuarioDAO.getInstance().list();

        for (Usuario usuario : listaDeUsuarios) {
            comboBoxModel.addElement(usuario);
        }

        JButton cadastrarButton = new JButton("adicionar");
        cadastrarButton.setAlignmentX(CENTER_ALIGNMENT);
        cadastrarButton.addActionListener(this);
        panel_4.add(cadastrarButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (usuariosComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário.");
        } else {
            int indice = usuariosComboBox.getSelectedIndex();
            Usuario usuario = listaDeUsuarios.get(indice);
            EventoDAO.getInstance().insert(event, usuario);
            parentWindow.update();
            dispose();
        }
    }
}
