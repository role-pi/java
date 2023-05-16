package visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import controle.InsumoDAO;
import modelo.Evento;
import modelo.InsumoModel;

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
	
public class InsumoView extends JFrame {
    private InsumoDAO insumoDAO = new InsumoDAO();

    private JTextField nomeTextField;
    private JTextField descricaoTextField;
    private JTextField valorTextField;
    private JComboBox<String> tipoComboBox;
    private JComboBox<String> eventosComboBox;
    private JTable table;
    private JTable tabela;
    private JFrame tabelaFrame;

    public InsumoView() {
        setTitle("adicionar insumo");
	    setSize(600, 350);
	    setLocationRelativeTo(null);
	
	    JPanel contentPane = new JPanel();
	    setContentPane(contentPane);
	    contentPane.setBackground(Color.WHITE);
	    contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
	    
	    String[] tipos = { "Ingresso", "Comida", "Bebida", "Transporte"};
	    DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(tipos);
	    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	    
	    Component verticalGlue = Box.createVerticalGlue();
	    contentPane.add(verticalGlue);
	    
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
	    
	    Component verticalStrut_1_2 = Box.createVerticalStrut(5);
	    panel_4.add(verticalStrut_1_2);
	    
	    JPanel panel_3 = new JPanel();
	    panel_4.add(panel_3);
	    panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
	    
	    JLabel tipoLabel = new JLabel("Tipo");
	    panel_3.add(tipoLabel);
	    tipoComboBox = new JComboBox<String>(comboModel);
	    panel_3.add(tipoComboBox);
	    
	    Component verticalStrut_1_1 = Box.createVerticalStrut(5);
	    panel_4.add(verticalStrut_1_1);
	    
	    JPanel panel_1 = new JPanel();
	    panel_4.add(panel_1);
	    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
	
	    JLabel descricaoLabel = new JLabel("Descrição");
	    panel_1.add(descricaoLabel);
	    descricaoTextField = new JTextField();
	    panel_1.add(descricaoTextField);
	    
	    Component verticalStrut_1 = Box.createVerticalStrut(5);
	    panel_4.add(verticalStrut_1);
	    
	    JPanel panel = new JPanel();
	    panel_4.add(panel);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	
	    JLabel valorLabel = new JLabel("Valor");
	    panel.add(valorLabel);
	    valorTextField = new JTextField();
	    panel.add(valorTextField);
	        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel_4.add(verticalStrut);
    
        JButton cadastrarButton = new JButton("Adicionar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_4.add(cadastrarButton);
        
        Component verticalGlue_1 = Box.createVerticalGlue();
        contentPane.add(verticalGlue_1);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarInsumo();
            }
        });
	
	    EventoDAO eventoDAO = new EventoDAO();
	    ArrayList<Evento> eventos = eventoDAO.listaEventos();
	
	    if (eventos != null && !eventos.isEmpty()) {
	        String[] nomesEventos = new String[eventos.size()];
	        for (int i = 0; i < eventos.size(); i++) {
	            nomesEventos[i] = eventos.get(i).getNome();
	        }
	
	        eventosComboBox = new JComboBox<>(nomesEventos);
	    } else {
	        eventosComboBox = new JComboBox<>();
	    }
	}
	
	private void removerInsumo(int rowIndex) {
	    insumoDAO.delete(rowIndex);
	}
	
	private void cadastrarInsumo() {
	    String tipo = (String) tipoComboBox.getSelectedItem();
	    String nome = nomeTextField.getText();
	    String descricao = descricaoTextField.getText();
	    double valor = Double.parseDouble(valorTextField.getText());
	    Evento evento = (Evento) eventosComboBox.getSelectedItem();
	
	    InsumoModel insumo = new InsumoModel(tipo, nome, descricao, valor, evento);
	    boolean cadastrado = insumoDAO.insert(insumo);
	
	    if (cadastrado) {
	        JOptionPane.showMessageDialog(null, "Insumo Cadastrado!");
	
	        nomeTextField.setText("");
	        descricaoTextField.setText("");
	        valorTextField.setText("");
	    } else {
	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar insumo.");
	    }
	}
	
	private void visualizarInsumos() {
	
	    if (tabelaFrame != null && tabelaFrame.isVisible()) {
	        tabelaFrame.dispose();
	    }
	
	    tabelaFrame = new JFrame("Tabela de Insumos");
	    tabelaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    tabelaFrame.setSize(700, 600);
	
	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Tipo");
	    model.addColumn("Nome");
	    model.addColumn("Descrição");
	    model.addColumn("Valor");
	    model.addColumn("Eventos");
	
	    ArrayList<InsumoModel> insumos = insumoDAO.listaInsumos();
	
	    for (InsumoModel insumo : insumos) {
	        String tipo = insumo.getTipo();
	        String nome = insumo.getNome();
	        String descricao = insumo.getDescricao();
	        double valor = insumo.getValor();
	        Evento eventos = insumo.getEventos();
	
	        model.addRow(new Object[] { tipo, nome, descricao, valor, eventos });
	    }
	
	    tabela = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(tabela);
	    tabelaFrame.getContentPane().add(scrollPane);
	
	    tabelaFrame.setVisible(true);
	
	    JButton removerInsumoButton = new JButton("Remover Insumo");
	    removerInsumoButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            removerInsumo();
	        }
	    });
	
	    JButton atualizarInsumosButton = new JButton("Atualizar Insumos");
	    atualizarInsumosButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            atualizarInsumos();
	        }
	    });
	
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new FlowLayout());
	    buttonPanel.add(removerInsumoButton);
	    buttonPanel.add(atualizarInsumosButton);
	
	    tabelaFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	
	    tabelaFrame.setVisible(true);
	}
	
	private void removerInsumo() {
	    int selectedRow = tabela.getSelectedRow();
	    if (selectedRow != -1) {
	        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o insumo?", "Remover Insumo",
	                JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
	            model.removeRow(selectedRow);
	            JOptionPane.showMessageDialog(null, "Insumo removido com sucesso!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Selecione um insumo na tabela para remover.");
	    }
	}
	
	private void atualizarInsumos() {
	    int selectedRow = tabela.getSelectedRow();
	    if (selectedRow != -1) {
	        String tipo = (String) tabela.getValueAt(selectedRow, 0);
	        String nome = (String) tabela.getValueAt(selectedRow, 1);
	        String descricao = (String) tabela.getValueAt(selectedRow, 2);
	        double valor = (double) tabela.getValueAt(selectedRow, 3);
	        Evento evento = (Evento) tabela.getValueAt(selectedRow, 4);
	
	        tipoComboBox.setSelectedItem(tipo);
	        nomeTextField.setText(nome);
	        descricaoTextField.setText(descricao);
	        valorTextField.setText(String.valueOf(valor));
	        eventosComboBox.setSelectedItem(evento);
	    } else {
	        JOptionPane.showMessageDialog(null, "Selecione um insumo na tabela para atualizar.");
	        }
	    }
	
	    public static void main(String[] args) {
	        InsumoView insumoView = new InsumoView();
	        insumoView.setVisible(true);
    }
}