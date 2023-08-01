package visao;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class CustomComboBoxEditor extends DefaultCellEditor {

	  // Declare a model that is used for adding the elements to the `Combo box`
	  private DefaultComboBoxModel model;
	  private String[] elements;

	  public CustomComboBoxEditor(String[] elements) {
	      super(new JComboBox());
	      this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
	      this.elements = elements;
	  }

	  @Override
	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	      // Add the elements which you want to the model.
	      // Here I am adding elements from the orderList(say) which you can pass via constructor to this class.
//	      model.addElement(orderList.get(i));
		  model.removeAllElements();
		  for (String element: elements) {
			  model.addElement(element);
		  }

	      //finally return the component.
	      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	  } 
	}