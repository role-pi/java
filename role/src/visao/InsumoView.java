package visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;
import modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class InsumoView extends JFrame implements ActionListener {
    private JButton cadastrarButton;
    private JTextField nomeTextField;
    private JTextField descricaoTextField;
    private JTextField valorTextField;
    private JComboBox<String> tipoComboBox;
    
    private Evento event;
    private InsumosDetailView parentWindow;

    public InsumoView(Evento event, InsumosDetailView parentWindow) {
    	this.event = event;
    	this.parentWindow = parentWindow;
    	
        setTitle("adicionar Insumo");
        setSize(600, 350);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panel_4 = new RoundedPanel();
        contentPane.add(panel_4);
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
        panel_4.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel_4.setMaximumSize(new Dimension(600, 250));
        panel_4.setFont(new Font("Inter", Font.BOLD, 13));

        JPanel panel_2 = new JPanel();
        panel_4.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

        JLabel nomeLabel = new JLabel("Nome");
        panel_2.add(nomeLabel);
        nomeTextField = new JTextField();
        panel_2.add(nomeTextField);

        JPanel panel_3 = new JPanel();
        panel_4.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

        JLabel tipoLabel = new JLabel("Tipo");
        panel_3.add(tipoLabel);
        tipoComboBox = new JComboBox<>(new String[] { "Ingresso", "Comida", "Bebida", "Transporte" });
        panel_3.add(tipoComboBox);

        JPanel panel_1 = new JPanel();
        panel_4.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        JLabel descricaoLabel = new JLabel("Descrição");
        panel_1.add(descricaoLabel);
        descricaoTextField = new JTextField();
        panel_1.add(descricaoTextField);

        JPanel panel = new JPanel();
        panel_4.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel valorLabel = new JLabel("Valor");
        panel.add(valorLabel);
        valorTextField = new JTextField();
        panel.add(valorTextField);

        cadastrarButton = new JButton("Adicionar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_4.add(cadastrarButton);

        cadastrarButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carregarEventos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == cadastrarButton) {
	        if (nomeTextField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Nome não pode estar vazio.");
	        } else if (descricaoTextField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Descrição não pode estar vazia.");
	        } else if (valorTextField.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Valor não pode estar vazio.");
	        } else {
	            cadastrarInsumo();
	            parentWindow.update();
	            dispose();
	        }
    	}
    }

    private void cadastrarInsumo() {
        String tipo = (String) tipoComboBox.getSelectedItem();
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        double valor = Double.parseDouble(valorTextField.getText());

        Insumo insumo = new Insumo(tipo, nome, descricao, new Transacao(valor, new Date(), new Usuario("João", "")));
        event.getInsumos().add(insumo);
    }
    
//    private void atualizarInsumos() {
//        int selectedRow = tabela.getSelectedRow();
//        if (selectedRow != -1) {
//            String tipo = (String) tabela.getValueAt(selectedRow, 0);
//            String nome = (String) tabela.getValueAt(selectedRow, 1);
//            String descricao = (String) tabela.getValueAt(selectedRow, 2);
//            double valor = (double) tabela.getValueAt(selectedRow, 3);
//            Evento evento = (Evento) tabela.getValueAt(selectedRow, 4);
//
//            tipoComboBox.setSelectedItem(tipo);
//            nomeTextField.setText(nome);
//            descricaoTextField.setText(descricao);
//            valorTextField.setText(String.valueOf(valor));
//            eventosComboBox.setSelectedItem(evento);
//        } else {
//            JOptionPane.showMessageDialog(null, "Selecione um insumo na tabela para atualizar.");
//        }
//    }

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                InsumoView insumoView = new InsumoView();
//                insumoView.setVisible(true);
//            }
//        });
    }
}
