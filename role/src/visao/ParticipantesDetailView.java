package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParticipantesDetailView extends RoundedPanel implements ActionListener {
	public ParticipantesDetailView (Color cor) {
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("PARTICIPANTES");
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.scrollbar);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 17));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		JButton btnNewButton = new JButton("Adicionar");
		panel_1.add(btnNewButton);
		btnNewButton.setFont(new Font("SF Pro Display", Font.PLAIN, 14));
		btnNewButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(0,0,0,0));
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Email");

        model.addRow(new Object[]{"João Gabriel", "joao@role.com"});
        model.addRow(new Object[]{"Ana Clara", "ana@role.com"});
        model.addRow(new Object[]{"Maiara", "maiara@role.com"});

		JTable table = new JTable(model);
		table.setFont(new Font("SF Pro Display", Font.PLAIN, 13));
		
		table.setSelectionBackground(cor.darker());
		
		JScrollPane scrollPane = new  JScrollPane(table);
		panel.add(scrollPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame insumo = new ParticipanteView();
		insumo.setVisible(true);
	}
}
