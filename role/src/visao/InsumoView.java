package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import controle.InsumoDAO;
import modelo.Evento;
import modelo.InsumoModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
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
        setTitle("Cadastro de Insumo");
        setSize(600, 350);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(7, 2, 10, 10));
        setContentPane(contentPane);

        JLabel tipoLabel = new JLabel("Tipo:");
        tipoComboBox = new JComboBox<String>(new String[] { "Tipo 1", "Tipo 2", "Tipo 3" });
        contentPane.add(tipoLabel);
        contentPane.add(tipoComboBox);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeTextField = new JTextField();
        contentPane.add(nomeLabel);
        contentPane.add(nomeTextField);

        JLabel descricaoLabel = new JLabel("Descricao:");
        descricaoTextField = new JTextField();
        contentPane.add(descricaoLabel);
        contentPane.add(descricaoTextField);

        JLabel valorLabel = new JLabel("Valor:");
        valorTextField = new JTextField();
        contentPane.add(valorLabel);
        contentPane.add(valorTextField);

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

        JLabel eventosLabel = new JLabel("Eventos:");
        // eventosComboBox = new JComboBox<String>(new String[] { "Evento 1", "Evento
        // 2", "Evento 3" });
        contentPane.add(eventosLabel);
        contentPane.add(eventosComboBox);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarInsumo();
            }
        });
        contentPane.add(new JLabel());
        contentPane.add(cadastrarButton);

        JButton visualizarButton = new JButton("Visualizar Insumos");

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarInsumos();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(visualizarButton);

        contentPane.add(buttonPanel);
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