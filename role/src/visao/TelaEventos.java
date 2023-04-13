package visao;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import modelo.Evento;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;

public class TelaEventos extends JFrame implements ActionListener {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnInserir, btnListar, btnDispose;
	private JTable table;
	
	private EventoDAO eventoDAO = new EventoDAO();
	private JPanel panel;
	
	public TelaEventos() {
		getContentPane().setBackground(new Color(231, 235, 255));
		setResizable(false);
		getContentPane().setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(18, 11, 61, 16);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(69, 6, 375, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(117, 39, 327, 26);
		getContentPane().add(textField_1);
		
		JLabel lblDataDeIncio = new JLabel("Data de início");
		lblDataDeIncio.setBounds(18, 44, 93, 16);
		getContentPane().add(lblDataDeIncio);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 79, 327, 26);
		getContentPane().add(textField_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Data de fim");
		lblNewLabel_1_1.setBounds(18, 84, 93, 16);
		getContentPane().add(lblNewLabel_1_1);
		
		panel = new JPanel();
		panel.setBounds(16, 116, 337, 30);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(0, 0, 117, 29);
		panel.add(btnInserir);
		btnInserir.setForeground(new Color(51, 0, 102));
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(111, 1, 117, 29);
		panel.add(btnListar);
		btnListar.setForeground(new Color(0, 102, 0));
		btnListar.setBackground(new Color(204, 255, 204));
		
		btnDispose = new JButton("Dispose");
		btnDispose.setBounds(220, 0, 117, 29);
		panel.add(btnDispose);
		btnDispose.addActionListener(this);
		btnListar.addActionListener(this);
		btnInserir.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 161, 426, 243);
		getContentPane().add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Data de início");
        model.addColumn("Data de fim");
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
	}
	
	public static void main(String[] args) {
		TelaEventos tela = new TelaEventos();
		tela.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInserir) {
			inserir();
		}
		
		if (e.getSource() == btnListar) {
			listar();
		}
		
		if (e.getSource() == btnDispose) {
			dispose();
		}
	}
	
	public void inserir() {
		String nome = textField.getText();
		String dataInicio = textField_1.getText();
		String dataFim = textField_2.getText();
		
		Evento evento = new Evento(nome, dataInicio, dataFim);
		eventoDAO.insert(evento);
	}
	
	public void listar() {
		ArrayList<Evento> eventos = eventoDAO.listaEventos();
		
		for (Evento evento: eventos) {
			String nome = evento.getNome();
			String dataInicio = evento.getDataInicio();
			String dataFim = evento.getDataFim();
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.addRow(new Object[]{nome, dataInicio, dataFim});
		}
	}
}
