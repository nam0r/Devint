package view;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;

@SuppressWarnings("serial")
public class Form_old extends JPanel {

	private boolean editable;
	private boolean valuesSet;
	
	private String[] values;
	private String[] labels;
	
	private JTextField[] textFields;
	
	public Form_old(String[] labels, String[] values, boolean editable) {
		
		this.labels = labels;
		this.values = values;
		
		this.textFields = new JTextField[labels.length];
		
		this.editable = editable;
		this.valuesSet = ! (values == null || values.length == 0 || values.length != labels.length);
		
		int numPairs = labels.length;
		
		this.setLayout(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			this.add(l);
			if(editable) {
				JTextField textField;
				if(valuesSet) {
					textField = new JTextField(values[i],10);
				}
				else {
					textField = new JTextField(10);
				}
				l.setLabelFor(textField);
				textFields[i] = textField;
				this.add(textField);
			}
			else {
				JLabel label;
				if(valuesSet) {
					label = new JLabel(values[i]);
				}
				else {
					label = new JLabel();
				}
				l.setLabelFor(label);
				this.add(label);
			}
		}

		int cols = 2;
		int rows = labels.length;
		int initialX = 6;
		int initialY = 6;
		int xPad = 6;
		int yPad = 6;
		Container parent = this;
		Spring x = Spring.constant(initialX);
		for (int c = 0; c < cols; c++) {
			Spring width = Spring.constant(0);
			for (int r = 0; r < rows; r++) {
				width = Spring.max(width,
						getConstraintsForCell(r, c, parent, cols).getWidth());
			}
			for (int r = 0; r < rows; r++) {
				SpringLayout.Constraints constraints = getConstraintsForCell(r,
						c, parent, cols);
				constraints.setX(x);
				constraints.setWidth(width);
			}
			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		Spring y = Spring.constant(initialY);
		for (int r = 0; r < rows; r++) {
			Spring height = Spring.constant(0);
			for (int c = 0; c < cols; c++) {
				height = Spring.max(height,
						getConstraintsForCell(r, c, parent, cols).getHeight());
			}
			for (int c = 0; c < cols; c++) {
				SpringLayout.Constraints constraints = getConstraintsForCell(r,
						c, parent, cols);
				constraints.setY(y);
				constraints.setHeight(height);
			}
			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

	}

	private Constraints getConstraintsForCell(int row, int col,
			Container parent, int cols) {
		SpringLayout layout = (SpringLayout) parent.getLayout();
		Component c = parent.getComponent(row * cols + col);
		return layout.getConstraints(c);
	}
	
	public String get(String label) {
		int index = indexOf(label, labels);
		if(index == -1) {
			System.err.println("No label found: " + label);
			return "";
		}
		
		if(editable) {
			return textFields[index].getText();
		}
		else {
			if(valuesSet) {
				return values[index];
			}
			else {
				return "";
			}
		}
	}
	
	private int indexOf(String s, String[] t) {
		for(int i = 0; i < t.length; i++)
			if(t[i].equals(s))
				return i;
		return -1;
	}

}
