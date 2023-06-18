package visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import modelo.Evento;
import modelo.Insumo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
	
public class EventoView extends JFrame implements ActionListener {
    private JTextField nomeTextField;
    private JTextField localTextField;
    private JTextField dataInicioTextField;
    private JTextField dataFimTextField;

    public EventoView() {
        setTitle("editar evento");
	    setSize(600, 350);
	    setLocationRelativeTo(null);
	
	    JPanel contentPane = new JPanel();
	    setContentPane(contentPane);
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
	    
	    Component verticalGlue = Box.createVerticalGlue();
	    contentPane.add(verticalGlue);
	    
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
	    
	    Component verticalStrut_1_2 = Box.createVerticalStrut(5);
	    panel_4.add(verticalStrut_1_2);
	    
	    JPanel panel_1 = new JPanel();
	    panel_4.add(panel_1);
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
	
	    JLabel descricaoLabel = new JLabel("Local");
	    panel_1.add(descricaoLabel);
	    localTextField = new JTextField();
	    panel_1.add(localTextField);
	    
	    JPanel panel_3 = new JPanel();
	    panel_4.add(panel_3);
	    panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
	
	    JLabel dataInicioLabel = new JLabel("Data de início");
	    panel_3.add(dataInicioLabel);
	    dataInicioTextField = new JTextField();
	    panel_3.add(dataInicioTextField);
	    
	    JPanel panel_5 = new JPanel();
	    panel_4.add(panel_5);
	    panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
	
	    JLabel dataFimLabel = new JLabel("Data de fim");
	    panel_5.add(dataFimLabel);
	    dataFimTextField = new JTextField();
	    panel_5.add(dataFimTextField);
	        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);
    
        JButton cadastrarButton = new JButton("Adicionar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_4.add(cadastrarButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nomeTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nome não pode estar vazio."); 
		} else if (localTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Local não pode estar vazio."); 
		} else if (dataInicioTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Data de início não pode estar vazia."); 
		} else if (dataFimTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Data de fim não pode estar vazia."); 
		} else {
			setVisible(false);
		}
	}
}