package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import modelo.Evento;
import modelo.Usuario;

public class ParticipantesDetailView extends RoundedPanel implements ActionListener, CellEditorListener {
	Evento evento;
	DefaultTableModel model;
	JTable table;
	
	JButton btnRemoveButton;
	JButton btnNewButton;
	
	public ParticipantesDetailView(Evento event) {
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
		
		JLabel lblNewLabel = new JLabel("PARTICIPANTES");
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
        model.addColumn("NOME");
        model.addColumn("EMAIL");
        
        update();

        table = new JTable(model);
        
		table.setFont(new Font("Inter", Font.PLAIN, 16));
		table.setRowHeight(30);
		
        TableColumn comboCol1 = table.getColumnModel().getColumn(0);

		table.setSelectionBackground(event.getColor().darker());
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
	}
	
	public void update() {
		model.setRowCount(0);
		for (Usuario usuario : evento.getUsuarios()) {
			model.addRow(new Object[]{usuario.getNome(), usuario.getEmail()});
		}
	}
	
	private void adicionar() {
		JFrame window = new AddParticipanteWindow(evento, this);
		window.setVisible(true);
	}
	
	private void remover() {
      int[] selectedRows = table.getSelectedRows();
      if (selectedRows.length > 0) {
    	  int confirm;
    	  if (selectedRows.length > 1) {
	          confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover os usuários?", "Remover Usuários",
	                  JOptionPane.YES_NO_OPTION);
    	  } else {
    		  confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o usuário?", "Remover Usuário",
	                  JOptionPane.YES_NO_OPTION);
    	  }
          if (confirm == JOptionPane.YES_OPTION) {
        	  for (int i = 0; i < selectedRows.length; i++){
	              evento.getUsuarios().remove(selectedRows[selectedRows.length-i-1]);
	          }
          }
          update();
      } else {
          JOptionPane.showMessageDialog(null, "Selecione um insumo na tabela para remover.");
      }
  }
	
	public void editar() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		for (int i = 0; i <= model.getRowCount() - 1; i++) {
			Usuario oldUsuario = evento.getUsuarios().get(i);
			
			String nome = String.valueOf(model.getValueAt(i, 0));
			if (nome.isEmpty()) {
				nome = oldUsuario.getNome();
			}
			
			Usuario usuario = new Usuario(0, nome, String.valueOf(model.getValueAt(i, 1)));
			usuarios.add(usuario);
        }
		evento.setUsuarios(usuarios);
		update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			adicionar();
		} else if (e.getSource() == btnRemoveButton) {
			remover();
		}
	}

	@Override
	public void editingStopped(ChangeEvent e) {
		editar();
	}

	@Override
	public void editingCanceled(ChangeEvent e) {
		editar();
	}
}
