package view;

public class Field {
	
	private String[] labels;
	private String[] values;
	private int[] lengths;
	
	public Field(String[] labels, String[] values, int[] lengths) {
		if(labels.length != values.length || labels.length != lengths.length) {
			System.err.println("Les tableaux doivent avoir la même longueur.");
			System.exit(0);
		}
		this.labels = labels;
		this.values = values;
		this.lengths = lengths;
	}
	
	public String[] getLabels() {
		return labels;
	}
	
	public String[] getValues() {
		return values;
	}
	
	public String getLabel(int i) {
		return labels[i];
	}
	
	public String getValue(int i) {
		return values[i];
	}
	
	public int getLength(int i) {
		return lengths[i];
	}

}
