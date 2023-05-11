package visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import controle.InsumoDAO;
import modelo.Evento;
import modelo.InsumoModel;

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

    private Component verticalStrutVizuInsumos;

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
        JButton removerInsumoButton = new JButton("Remover Insumo");

        removerInsumoButton.setEnabled(false); 

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarInsumos();
                removerInsumoButton.setEnabled(true);
            }
        });

        removerInsumoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerInsumo();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(visualizarButton);
        buttonPanel.add(removerInsumoButton);

        contentPane.add(buttonPanel);
    }

    private void removerInsumo(int rowIndex) {
        insumoDAO.delete(rowIndex);
    }

    private void removerInsumo() {
        int selectedRow = insumoDAO.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o insumo?", "Remover Insumo",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                removerInsumo(selectedRow);
                JOptionPane.showMessageDialog(null, "Insumo removido com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um insumo na tabela para remover.");
        }
    }

    private void cadastrarInsumo() {
        String tipo = (String) tipoComboBox.getSelectedItem();
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        double valor = Double.parseDouble(valorTextField.getText());
        String evento = (String) eventosComboBox.getSelectedItem();

        System.out.println("Insumo cadastrado:");
        System.out.println("Tipo: " + tipo);
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Valor: " + valor);
        System.out.println("Evento: " + evento);
    }

    private void visualizarInsumos() {

        // TABELA DE INUSMOS
        JFrame tabelaFrame = new JFrame("Tabela de Insumos");
        tabelaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabelaFrame.setSize(700, 600);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tipo");
        model.addColumn("Nome");
        model.addColumn("Descricao");
        model.addColumn("Valor");
        model.addColumn("Eventos");

        table = new JTable(model);

        ArrayList<InsumoModel> insumos = insumoDAO.listaInsumos();

        model.setNumRows(0);

        for (InsumoModel insumo : insumos) {
            String tipo = insumo.getNome();
            String nome = insumo.getNome();
            String descricao = insumo.getNome();
            double valor = insumo.getValor();
            Evento eventos = insumo.getEventos();

            model.addRow(new Object[] { tipo, nome, descricao, valor, eventos });
        }

        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        tabelaFrame.getContentPane().add(scrollPane);

        tabelaFrame.setVisible(true);
    }

    public static void main(String[] args) {
        InsumoView insumoView = new InsumoView();
        insumoView.setVisible(true);
    }
}