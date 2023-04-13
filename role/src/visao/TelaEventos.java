package visao;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import modelo.Evento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class TelaEventos extends JFrame implements ActionListener {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnInserir, btnListar, btnDispose;
	private JTable table;
	
	private EventoDAO eventoDAO = new EventoDAO();
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	
	public TelaEventos() {
		getContentPane().setBackground(new Color(231, 235, 255));
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		panel_1 = new JPanel();
		panel_4.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		
		JLabel lblNewLabel = new JLabel("Nome");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		verticalStrut = Box.createVerticalStrut(5);
		panel_4.add(verticalStrut);
		
		panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));
		
		JLabel lblDataDeIncio = new JLabel("Data de início");
		panel_2.add(lblDataDeIncio);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		verticalStrut_1 = Box.createVerticalStrut(5);
		panel_4.add(verticalStrut_1);
		
		panel_3 = new JPanel();
		panel_4.add(panel_3);
		FlowLayout fl_panel_3 = new FlowLayout(FlowLayout.LEADING, 5, 0);
		panel_3.setLayout(fl_panel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Data de fim");
		panel_3.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		verticalStrut_2 = Box.createVerticalStrut(5);
		panel_4.add(verticalStrut_2);
		
		panel = new JPanel();
		panel_4.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		btnInserir = new JButton("Inserir");
		panel.add(btnInserir);
		btnInserir.setForeground(new Color(51, 0, 102));
		
		btnListar = new JButton("Listar");
		panel.add(btnListar);
		btnListar.setForeground(new Color(0, 102, 0));
		btnListar.setBackground(new Color(204, 255, 204));
		
		btnDispose = new JButton("Dispose");
		panel.add(btnDispose);
		
		verticalStrut_3 = Box.createVerticalStrut(5);
		panel_4.add(verticalStrut_3);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);
		
		btnDispose.addActionListener(this);
		btnListar.addActionListener(this);
		btnInserir.addActionListener(this);
		
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
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.setNumRows(0);
		
		for (Evento evento: eventos) {
			String nome = evento.getNome();
			String dataInicio = evento.getDataInicio();
			String dataFim = evento.getDataFim();
	        model.addRow(new Object[]{nome, dataInicio, dataFim});
		}
	}
}
