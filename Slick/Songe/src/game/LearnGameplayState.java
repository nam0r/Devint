package game;

import main.Songe;
import net.phys2d.raw.CollisionEvent;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Globals;

/**
 * Game play state to learn the game
 */

public class LearnGameplayState extends GameplayState {

	public LearnGameplayState(int id) {
		super(id, "space.jpg", "learn.txt");
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		super.render(gc, sbg, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.leave(gc, sbg);
		// we cancel the character displacement
		Globals.player.setPosition(Globals.player.getX() - 200, Globals.player.getY() + 100);
	}
	
	@Override
	protected void collisions(Enemy enemy, CollisionEvent event){
		//if the enemy is killed, the game can begin
		if ((event.getPoint().getY() < (enemy.getY()
				+ (enemy.getHeight() / 3)))
				&& (event.getPoint().getY() > (Globals.player.getY()
						+ (Globals.player.getHeight() / 3)))) {
			map.removeEntity(enemy);
			((Enemy)enemy).stopSound();
			sbg.enterState(Songe.GAMEPLAYSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		}
		//if the enemy is not killed, the player is hurt
		else{
			Globals.invulnerable = true;
		}
	}
	
}