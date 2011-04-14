package nodes;

import utils.Conf;

public class Question {
	
	private String wording;
	private String voice;
	private boolean scenario;
	private Choice[] choices;
	private int points;
	
	public Question(String wording, String voice, boolean scenario, Choice[] choices, int points) {
		this.wording = wording;
		this.voice = voice;
		this.scenario = scenario;
		this.choices = choices;
		this.points = points;
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
