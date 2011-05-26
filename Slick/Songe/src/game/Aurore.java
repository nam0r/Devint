package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.loading.LoadingList;

import sound.Sound2;
import utils.Conf;
import utils.Globals;
import actors.MainPlayer;

/**
 * The Aurore player
 * 
 * @author namor
 * @author Afnarel
 */
public class Aurore extends MainPlayer {
	/** The Y offset for walking sprite sheet */
	private final int Y_OFFSET_WALK = 0;
	/** The amount of sheets for the walking sprite sheet */
	private final int NB_SPRITES_WALK = 3;
	/** the step rate constant for the step sound */
	private final float STEPRATE = 13f;
	
	public Aurore(int n){
		this(n, "aurore_walk.png", "aurore_jmp.png");
	}
	
	public Aurore(int n, String walkingSprite, String jumpingSprite) {
		super(n, Conf.IMG_SPRITES_PATH+walkingSprite, 100, 450, 2f, 40, 62);
		
		walk = new SpriteSheet(image,40,74);
		
		try {
			Image im = new Image(Conf.IMG_SPRITES_PATH+jumpingSprite);
			jump = new SpriteSheet(im,40,74);
		} catch (SlickException e) {
			System.err.println("Image aurore pas trouvée.");
		}
		
		moveForce = 150;
		jumpForce = 40000;
		MAX_JUMP_VEL = 120;
		body.setMaxVelocity(40, 120);
		
		LoadingList.setDeferredLoading(false);
		try {
			painSound = new Sound2(Conf.SND_PERSOS_PATH+"douleur_femme.ogg");
		} catch (SlickException e) {
			System.err.println("Sound "+ Conf.SND_PERSOS_PATH+"douleur_homme.ogg" +" not found");
		}
		LoadingList.setDeferredLoading(true);
	}

	public void render(Graphics g) {
		// work out which animation we're showing based 
		// on the state of the actor
		SpriteSheet sheet = jump;
		int sx = 0;
		int sy = 0;
		
		if (jumping()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} else if (falling()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} /*else if(waiting()) {
			sheet = jump;
			sx = 0;
			sy = 0;
		} */else if (moving() && onGround()) {
			sheet = walk;
			sx = frame % NB_SPRITES_WALK;
			sy = Y_OFFSET_WALK;
 		} else if (onGround() || waiting()) {
			sheet = walk;
			sx = 0;
			sy = Y_OFFSET_WALK;
		}
		
		// get the appropriate sprite 
		Image image = sheet.getSprite(sx,sy);
		image = image.getFlippedCopy(true, false);
	
		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}
		
		//image.drawCentered(getX(), getY()-12);
		if(!Globals.invulnerable || Globals.invulnerableTimer%2==1)
			image.draw(getX()-width/2, getY()-height/2, width, height+2);
	}
	
	@Override
	public float stepRate(){
		return STEPRATE;
	}

}