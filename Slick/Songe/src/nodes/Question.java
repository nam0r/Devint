package nodes;

import utils.Conf;

public abstract class Question {
	
	protected String text;
	protected String sound;
	protected Choice[] choices;
	
	public Question(String text, String sound) {
		this.text = text;
		this.sound = sound;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public Choice[] getChoices() {
		return this.choices;
	}
	
	public String[] getChoicesWordings() {
		String[] choicesWordings = new String[choices.length];
		for(int i = 0; i < choices.length; i++) {
			choicesWordings[i] = choices[i].getWording();
		}
		return choicesWordings;
	}
	
	public String[] getChoicesVoices() {
		String[] choicesVoices = new String[choices.length];
		for(int i = 0; i < choices.length; i++) {
			choicesVoices[i] = Conf.getVoice(choices[i].getVoice());
		}
		return choicesVoices;
	}
	
	public String getWording() {
		return this.wording;
	}
	
	public String getVoice() {
		return Conf.getVoice(this.voice);
	}
	
	public boolean getScenario() {
		return scenario;
	}
	
	public boolean isOk(int indice) {
		return choices[indice].getIsAnswer();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(wording);
		sb.append("\n");
		sb.append(voice);
		sb.append("\n");
		sb.append(points);
		sb.append("\n");
		for(Choice choice : choices) {
			sb.append("\t");
			sb.append(choice.getWording());
			sb.append("\n");
		}
		return sb.toString();
	}

}
