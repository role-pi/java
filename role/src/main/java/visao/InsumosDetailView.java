package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controle.InsumoDAO;
import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;

public class InsumosDetailView extends RoundedPanel implements ActionListener, CellEditorListener, UpdatableView {
	Evento evento;
	DefaultTableModel model;
	JTable table;
	
	JButton btnRemoveButton;
	JButton btnNewButton;
	
	public InsumosDetailView(Evento event) {
		this.evento = event;
		
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0,0,0,0));
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		JPanel panel_1 = new JPanel();
		verticalBox.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("INSUMOS");
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.scrollbar);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 18));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		btnRemoveButton = new JButton("remover");
		panel_1.add(btnRemoveButton);
		btnRemoveButton.addActionListener(this);
		btnRemoveButton.setFont(new Font("Inter", Font.PLAIN, 16));
		
		btnNewButton = new JButton("adicionar");
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Inter", Font.PLAIN, 16));
		
		model = new DefaultTableModel();
        model.addColumn("TIPO");
        model.addColumn("NOME");
        model.addColumn("DESCRIÇÃO");
        model.addColumn("VALOR");
        
        update();

        table = new JTable(model);
        
        table.getDefaultEditor(String.class).addCellEditorListener(this);
		table.setFont(new Font("Inter", Font.PLAIN, 16));
		table.setRowHeight(30);
		
        TableColumn comboCol1 = table.getColumnModel().getColumn(0);
        
        CustomComboBoxEditor editor = new CustomComboBoxEditor(Insumo.allTipos());
        editor.addCellEditorListener(this);
        comboCol1.setCellEditor(editor);

		table.setSelectionBackground(event.getColor().darker());
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
	}
	
	public void update() {
		model.setRowCount(0);
		evento.setInsumos(InsumoDAO.getInstance().list());
		for (Insumo insumo : evento.getInsumos()) {
			DecimalFormat df = new DecimalFormat("#,00");
			model.addRow(new Object[]{Insumo.allTipos()[insumo.getTipo()], insumo.getNome(), insumo.getDescricao(), "R$ "+df.format(insumo.getTransacao().getValor())});
		}
	}
	
	private void adicionarInsumo() {
		JFrame window = new AddInsumoWindow(evento, this);
		window.setVisible(true);
	}
	
	private void removerInsumos() {
      int[] selectedRows = table.getSelectedRows();
      if (selectedRows.length > 0) {
    	  int confirm;
    	  if (selectedRows.length > 1) {
	          confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover os insumos?", "Remover Insumos",
	                  JOptionPane.YES_NO_OPTION);
    	  } else {
    		  confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o insumo?", "Remover Insumo",
	                  JOptionPane.YES_NO_OPTION);
    	  }
          if (confirm == JOptionPane.YES_OPTION) {
        	  for (int i = 0; i < selectedRows.length; i++){
        		  for (int j = 0; j < selectedRows[i]; j++) {
        			  InsumoDAO.getInstance().delete(evento.getInsumos().get(j));
        		  }
	          }
          }
          update();
      } else {
          JOptionPane.showMessageDialog(null, "Selecione um insumo na tabela para remover.");
      }
  }
	
	public void editarInsumos() {
		for (int i = 0; i <= model.getRowCount() - 1; i++) {
			boolean update = false;
			
			Insumo oldInsumo = evento.getInsumos().get(i);
			Transacao oldTransacao = oldInsumo.getTransacao();

			int tipo = Arrays.asList(Insumo.allTipos()).indexOf(String.valueOf(model.getValueAt(i, 0)));
			if (oldInsumo.getTipo() != tipo)
				update = true;
			
			oldInsumo.setTipo(tipo);
			
			String nome = String.valueOf(model.getValueAt(i, 1));
			if (!nome.isEmpty()) {
				if (oldInsumo.getNome() != nome)
					update = true;
				
				oldInsumo.setNome(nome);
			}
			
			String descricao = String.valueOf(model.getValueAt(i, 2));
			if (!descricao.isEmpty()) {
				if (oldInsumo.getDescricao() != descricao)
					update = true;
				
				oldInsumo.setDescricao(descricao);
			}

			Double valor = Double.valueOf(String.valueOf(model.getValueAt(i, 3)).replaceAll("[^-\\d.]", ""));
			if (oldTransacao.getValor() != valor)
				update = true;
			
			oldTransacao.setValor(valor);
			
			if (update)
				InsumoDAO.getInstance().update(oldInsumo);
        }
		
		update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			adicionarInsumo();
		} else if (e.getSource() == btnRemoveButton) {
			removerInsumos();
		}
	}

	@Override
	public void editingStopped(ChangeEvent e) {
		editarInsumos();
	}

	@Override
	public void editingCanceled(ChangeEvent e) {
		editarInsumos();
	}
}
