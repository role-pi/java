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

import javax.swing.UIManager;

public class InsumosDetailView extends RoundedPanel implements ActionListener {
	public InsumosDetailView () {
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
		lblNewLabel.setFont(new Font("Inter", Font.BOLD, 17));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		JButton btnNewButton = new JButton("Adicionar");
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tipo");
        model.addColumn("Nome");
        model.addColumn("Valor");

        model.addRow(new Object[]{"🎟️", "Ingressos", "R$ 40,00"});
        model.addRow(new Object[]{"🚗️", "Uber", "R$ 56,72"});

        JTable table = new JTable(model);
		table.setFont(new Font("Inter", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new  JScrollPane(table);
		panel.add(scrollPane);
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame insumo = new InsumoView();
		insumo.setVisible(true);
	}
}
