package visao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.EventoDAO;
import controle.InsumoDAO;
import modelo.Evento;
import modelo.Insumo;

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

public class InsumoView extends JFrame implements ActionListener {
    private InsumoDAO insumoDAO;

    private JTextField nomeTextField;
    private JTextField descricaoTextField;
    private JTextField valorTextField;
    private JComboBox<String> tipoComboBox;
    private JComboBox<Evento> eventosComboBox;
    private JTable tabela;
    private JFrame tabelaFrame;

    public InsumoView() {
        setTitle("Adicionar Insumo");
        setSize(600, 350);
        setLocationRelativeTo(null);

        insumoDAO = InsumoDAO.getInstance();

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

        JButton cadastrarButton = new JButton("Adicionar");
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_4.add(cadastrarButton);

        cadastrarButton.addActionListener(this);

        JButton visualizarButton = new JButton("Visualizar Insumos");
        visualizarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_4.add(visualizarButton);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizarInsumos();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nomeTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome não pode estar vazio.");
        } else if (descricaoTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Descrição não pode estar vazia.");
        } else if (valorTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Valor não pode estar vazio.");
        } else {
            cadastrarInsumo();
            JOptionPane.showMessageDialog(this, "Insumo cadastrado com sucesso.");
            nomeTextField.setText("");
            descricaoTextField.setText("");
            valorTextField.setText("");
        }
    }

    private void cadastrarInsumo() {
        String tipo = (String) tipoComboBox.getSelectedItem();
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        double valor = Double.parseDouble(valorTextField.getText());
        Evento evento = (Evento) eventosComboBox.getSelectedItem();

        Insumo insumo = new Insumo(tipo, nome, descricao, valor, evento);
        insumoDAO.insert(insumo);
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

        List<Insumo> insumos = insumoDAO.list();

        for (Insumo insumo : insumos) {
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InsumoView insumoView = new InsumoView();
                insumoView.setVisible(true);
            }
        });
    }
}
