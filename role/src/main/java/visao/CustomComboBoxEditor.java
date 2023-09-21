package visao;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CustomComboBoxEditor extends DefaultCellEditor {
	  private DefaultComboBoxModel model;
	  private String[] elements;

	  public CustomComboBoxEditor(String[] elements) {
	      super(new JComboBox());
	      this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
	      this.elements = elements;
	  }

	  @Override
	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		  model.removeAllElements();
		  for (String element: elements) {
			  model.addElement(element);
		  }

	      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	  } 
	}