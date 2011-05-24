package nodes;

import java.util.ArrayList;

import main.Songe;

import utils.Conf;

/**
 * A generic abstract question
 * 
 * @author Afnarel
 */
public abstract class Question<T extends Choice> implements Event {
	
	protected String text;
	protected String sound;
	protected ArrayList<T> choices;
	
	public Question(String text, String sound) {
		this.text = text;
		this.sound = sound;
		choices = new ArrayList<T>();
	}
	
	public ArrayList<T> getChoices() {
		return this.choices;
	}
	
	public void addChoice(T c) {
		this.choices.add(c);
	}
	
	public String[] getChoicesWordings() {
		String[] choicesWordings = new String[choices.size()];
		for(int i = 0; i < choices.size(); i++) {
			choicesWordings[i] = choices.get(i).getWording();
		}
		return choicesWordings;
	}
	
	public String[] getChoicesVoices() {
		String[] choicesVoices = new String[choices.size()];
		for(int i = 0; i < choices.size(); i++) {
			choicesVoices[i] = Conf.getVoice(choices.get(i).getVoice());
		}
		return choicesVoices;
	}
	
	public String getText() {
		return this.text;
	}
	
	public String getSound() {
		return Conf.getVoice(this.sound);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(text);
		sb.append("\n");
		sb.append(sound);
		sb.append("\n");
		return sb.toString();
	}
	
	@Override
	public int getStateID() {
		return Songe.QUESTIONSTATE;
	}

}
