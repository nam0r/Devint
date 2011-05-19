package view;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SpringLayout.Constraints;

@SuppressWarnings("serial")
public class Form extends JPanel {

	private boolean editable;
	
	private Field[] fields;
	
	private ArrayList<String> labels;
	private ArrayList<String> values;
	
	private ArrayList<JTextField> textFields;
	
	private int maxWidth = 0;
	
	public Form(Field[] fields, boolean editable) {
		
		this.fields = fields;
		
		labels = new ArrayList<String>();
		values = new ArrayList<String>();
		
		for(Field f : this.fields) {
			for(String s : f.getLabels())
				labels.add(s);
			for(String s : f.getValues())
				values.add(s);
			maxWidth = Math.max(maxWidth, f.getLabels().length);
		}
		
		this.textFields = new ArrayList<JTextField>();
		
		this.editable = editable;
		
		this.setLayout(new SpringLayout());
		for (int i = 0; i < fields.length; i++) {
			for(int j = 0; j < maxWidth; j++) {
				JLabel l;
				if(j < fields[i].getLabels().length) {
					l = new JLabel(fields[i].getLabel(j), JLabel.TRAILING); // TODO A revoir getLabel(0)
				}
				else {
					l = new JLabel();
				}
				
				
				this.add(l);
				if(editable) {
					
					if(j < fields[i].getLabels().length) {
						JTextField textField = new JTextField(fields[i].getValue(j),fields[i].getLength(j)); // TODO A revoir getValue(0)
						textFields.add(textField);
						l.setLabelFor(textField);
						this.add(textField);
					}
					else { 
						JLabel label = new JLabel();
						l.setLabelFor(label);
						this.add(label);
					}
				}
				else {
					JLabel label;
					if(j < fields[i].getLabels().length) {
						label = new JLabel(fields[i].getValue(j)); // TODO A revoir getValue(0)
					}
					else {
						label = new JLabel();
					}
					
					l.setLabelFor(label);
					this.add(label);
				}
			}
		}

		int cols = maxWidth * 2; // TODO A revoir !!!
		int rows = fields.length;
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
	
	public String getExact(String label) {
		//int index = indexOf(label, labels);
		int index = labels.indexOf(label);
		if(index == -1) {
			System.err.println("No label found: " + label);
			return "";
		}
		
		if(editable) {
			return textFields.get(index).getText();
		}
		else {
			return values.get(index);
		}
	}
	
	public ArrayList<String> get(String label) {
		ArrayList<String> tab = new ArrayList<String>();
		for(String l : labels) {
			if(l.contains(label)) {
				tab.add(getExact(l));
			}
		}
		return tab;
	}
	
	public void clear() {
		for(JTextField jtf : textFields) {
			jtf.setText("");
		}
	}
	
	/*
	private int indexOf(String s, String[] t) {
		for(int i = 0; i < t.length; i++)
			if(t[i].equals(s))
				return i;
		return -1;
	}
	*/

}
