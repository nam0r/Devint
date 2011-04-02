package salle;

import java.awt.Color;
import java.io.Serializable;

public class Salle implements Serializable {
	
	private String description;
	private Color fontColor;
	private Color backColor;
	
	public Salle() {
		description = "";
	}
	
	public Salle(String description, Color fontColor,Color backColor) {
		this.description = description;
		this.fontColor = fontColor;
		this.backColor = backColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Color getFontColor() {
		return fontColor;
	}
	
	public Color getBackColor() {
		return backColor;
	}
}
