package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.SystemColor;
import javax.swing.UIManager;

public class InsumosDetailView extends RoundedPanel {
	public InsumosDetailView () {
		setBackground(new Color(236, 236, 236));
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
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
		lblNewLabel.setFont(new Font("SF Pro Display", Font.BOLD, 17));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		JLabel lblX = new JLabel("+");
		lblX.setForeground(UIManager.getColor("Button.darkShadow"));
		lblX.setFont(new Font("SF Pro Display", Font.BOLD, 17));
		lblX.setBackground(Color.WHITE);
		panel_1.add(lblX);
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Data de início");
        model.addColumn("Data de fim");
        

        model.addRow(new Object[]{"Teste", "", ""});

		JTable table = new JTable(model);
		panel.add(table);
		
		Component verticalGlue = Box.createVerticalGlue();
		panel.add(verticalGlue);
	}
}
