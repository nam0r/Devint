package game;

import menu.MenuState;
import nodes.Node;
import nodes.Question;
import nodes.QuestionCulture;
import nodes.QuestionScenario;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
import utils.Globals;
 
public class QuestionState extends MenuState {
	/** the question */
	private Question question;
	/** good and bad answer sounds */
	private Sound2 bonneRep, mauvaiseRep;
	/** The music of the scenarii questions */
	private Music musicScen;
	
    public QuestionState(int stateID) throws SlickException {
    	super(stateID);
    	chosen = false;
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		bonneRep = new Sound2(Conf.SND_VOIX_PATH+"bonne_reponse.ogg");
		mauvaiseRep = new Sound2(Conf.SND_VOIX_PATH+"mauvaise_reponse.ogg");
		music = new Music(Conf.SND_MUSIC_PATH + "questioncult.ogg");
		musicScen = new Music(Conf.SND_MUSIC_PATH + "choixscenar.ogg");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		super.render(gc, sbg, gfx);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
		
		Input input = gc.getInput();
		if (!chosen) {
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				if(question instanceof QuestionCulture) {
					QuestionCulture qCulture = (QuestionCulture)question;
					if (qCulture.isAnswer(selected)) {
						Globals.score += qCulture.getPoints();
						
						AlUtils.stopAllSounds();
						bonneRep.play();
					} else {
						AlUtils.stopAllSounds();
						mauvaiseRep.play();
					}
				}
				else if(question instanceof QuestionScenario) {
					QuestionScenario qScenario = (QuestionScenario)question;
					
					Globals.node = new Node(qScenario.getChoices().get(selected).getNodeToGoTo());
					Globals.nodeHasChanged = true;
				}
				
				chosen = true;
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}
		
		if (chosen && !bonneRep.playing() && !mauvaiseRep.playing()) {
			//if there is no more events
			if(Globals.node.getEvents().isEmpty())
				sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			//if there is some events to go to
			else{
				Globals.nextEvent();
				sbg.enterState(Globals.event.getStateID(), new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
				
		}
		if(music.playing()){
			//we set the music volume, depending if voices are playing or not
			if(!AlUtils.anySoundPlaying()){
				if(music.getVolume() < 0.8)
					music.setVolume(music.getVolume()+0.015f);
				else
					music.setVolume(0.8f);
			}
			else
				music.setVolume(0.08f);
		}
		else if(musicScen.playing()){
			//we set the music volume, depending if voices are playing or not
			if(!AlUtils.anySoundPlaying()){
				if(musicScen.getVolume() < 0.8)
					musicScen.setVolume(musicScen.getVolume()+0.015f);
				else
					musicScen.setVolume(0.8f);
			}
			else
				musicScen.setVolume(0.08f);
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		question = Globals.question;
		if(question == null) {
			System.err.println("Il n'y a pas de question a lire !!!");
		}
		options = question.getChoicesWordings(); // the choices
		title = question.getText(); // the question
		// the sound of the question
		titleVoice = question.getSound();
		// The sounds to read for the answers to the question
		//optionsVoices = new String[]{Conf.getVoice("14ans"), Conf.getVoice("80ans"), Conf.getVoice("140ans")};
		optionsVoices = question.getChoicesVoices();
		
		//This is useful because we load here sounds that we didn't know at the beginning of the game, they are not deferred
		LoadingList.setDeferredLoading(false);
		
		optionsSounds = new Sound2[options.length];
		titleSound = new Sound2(titleVoice);
		for(int i=0; i<options.length; i++){
    		optionsSounds[i] = new Sound2(optionsVoices[i]);
    	}
		
		LoadingList.setDeferredLoading(true);
		//the music to be played
		if(question instanceof QuestionCulture)
			music.loop();
		else if(question instanceof QuestionScenario)
			musicScen.loop();
		
		super.enter(gc, sbg); //It will read the options[selected]
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		//we clear variables for future questions
		options = new String[0];
		title = "";
		chosen = false;
		music.stop();
	}

}