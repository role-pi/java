package visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.httprpc.sierra.DatePicker;
import org.httprpc.sierra.TimePicker;
import org.httprpc.sierra.VerticalAlignment;

import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;
import modelo.Usuario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
	
public class AddParticipanteWindow extends JFrame implements ActionListener {
    private JTextField nomeTextField;
    private JTextField emailTextField;
    
    private Evento event;
    private ParticipantesDetailView parentWindow;

    public AddParticipanteWindow(Evento event, ParticipantesDetailView parentWindow) {
    	setTitle("Adicionar Participante");
	    setSize(418, 303);
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
	    
	    JPanel panel_2 = new JPanel();
	    panel_4.add(panel_2);
	    panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
	    
        JLabel nomeLabel = new JLabel("Nome");
        panel_2.add(nomeLabel);
        nomeTextField = new JTextField();
        panel_2.add(nomeTextField);
	    
	    JPanel panel_1 = new JPanel();
	    panel_4.add(panel_1);
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
	
	    JLabel localLabel = new JLabel("Email");
	    panel_1.add(localLabel);
	    emailTextField = new JTextField();
	    panel_1.add(emailTextField);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);
    
        JButton cadastrarButton = new JButton("Atualizar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarButton.addActionListener(this);
        panel_4.add(cadastrarButton);
	}
    
    @Override
	public void actionPerformed(ActionEvent e) {
		if (nomeTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome não pode estar vazio."); 
		} else if (emailTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Email não pode estar vazio."); 
		} else {
			cadastrarParticipante();
			parentWindow.update();
			dispose();
		}
	}
    
    private void cadastrarParticipante() {
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();

        Usuario usuario = new Usuario(nome, email);
        event.getUsuarios().add(usuario);
    }
}