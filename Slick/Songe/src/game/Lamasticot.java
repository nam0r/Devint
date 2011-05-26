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
 * The Lamasticot player
 * 
 * @author namor
 * @author Afnarel
 */
public class Lamasticot extends MainPlayer {
	/** The Y offset for walking sprite sheet */
	private final int Y_OFFSET_WALK = 0;
	/** The amount of sheets for the walking sprite sheet */
	private final int NB_SPRITES_WALK = 7;
	/** The amount of sheets for the jumping sprite sheet */
	private final int NB_SPRITES_JUMP = 6;
	/** jump beginning offset */
	private int jumpTimer;
	/** Indicates if a jump has been initiated */
	private boolean beginJumpTimer;
	/** the step rate constant for the step sound */
	private final float STEPRATE = 90f;
	/** the path to the walking sound */
	private final String SOUND_WALK_PATH = Conf.SND_DEPLACEMENT_PATH + "crawl.ogg";
	
	
	/*private static final int walkingTime = 1000;
	private int walkingTimer;
	private boolean moveUp;*/
	
	public Lamasticot(int n){
		this(n, "lama.png", "lama_jump.png");
	}
	
	public Lamasticot(int n, String walkingSprite, String jumpingSprite) {
		super(n, Conf.IMG_SPRITES_PATH+walkingSprite, 200, 500, 10f, 125, 92);
		
		walk = new SpriteSheet(image,125,92);
		
		try {
			Image im = new Image(Conf.IMG_SPRITES_PATH+jumpingSprite);
			jump = new SpriteSheet(im,125,92);
		} catch (SlickException e) {
			System.err.println("Image sprites de Tux pas trouvées.");
		}
		
		moveForce = 700;
		jumpForce = 70000;
		MAX_JUMP_VEL = 125;
		body.setMaxVelocity(35, 125);
		jumpTime = 200;
		
		jumpTimer = 0;
		beginJumpTimer = false;
		
		/*walkingTimer = 200;
		moveUp = false;*/
		
		LoadingList.setDeferredLoading(false);
		try {
			painSound = new Sound2(Conf.SND_PERSOS_PATH+"douleur_lama.ogg");
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
		}
		else if(beginJumpTimer){
			sheet = jump;
			sx = frame % NB_SPRITES_JUMP;
			sy = 0;
			jumpTimer += 1;
		}
		 /*else if(waiting()) {
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
		Image theImage = sheet.getSprite(sx,sy);
	
		// if we're facing the other direction, flip the sprite over
		if (!facingRight()) {
			theImage = theImage.getFlippedCopy(true, false);
		}
		if(!Globals.invulnerable || Globals.invulnerableTimer%2==1)
			theImage.draw(getX()-width/2, getY()-height/2, width, height);
	}
	
	@Override
	public void jump() {
		beginJumpTimer = true;
	}
	
	@Override
	public void update(int delta){
		super.update(delta);
		
		if(beginJumpTimer){
			//jumpTimer += delta;
		}
		if (jumpTimer > 4) {
			beginJumpTimer = false;
			applyForce(0, -jumpForce);
			jumpTimer = 0;
		}
	}
	
	public float stepRate(){
		return STEPRATE;
	}
	
	public String getSoundWalk(){
		return SOUND_WALK_PATH;
	}
	
	/*@Override
	public void update(int delta) {
		super.update(delta);
		//if(!jumping()){
			if(walkingTimer > 200 || ((on) && walkingTimer < 0)){
				if(moveUp) walkingTimer = 200;
				else walkingTimer = 0;
				moveUp = !moveUp;
			}
			if (!moveUp){
				body.addForce(new Vector2f(0, - 100 - walkingTimer/1.5f));
				walkingTimer -= delta/3;
			}
			else{
				body.addForce(new Vector2f(0, -400 + walkingTimer));
				walkingTimer += delta/3;
			}
		//}
	}
	
	@Override
	public void jump() {
		applyForce(0, -jumpForce);
	}*/

}