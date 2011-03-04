package actors;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;

import org.newdawn.slick.Graphics;

/**
 * Une entite du monde.
 * Une entite peut avoir un corps physique optionnel.
 */
public interface PhysicalEntityInterface {
	/**
	 * Definit le monde auquel cette entite appartient
	 * 
	 * @param world Le monde auquel l'entite appartient
	 */
	public void setWorld(World world);
	
	/**
	 * Retourne le corps qui represente cette entite dans le monde 
	 * 
	 * @return le corps qui represente cette entite dans le monde ou null si elle n'en a pas.
	 */
	public Body getBody();

	/**
	 * Met a jour cette entite.
	 * Cette methode est appelee une et une seule fois avant que le monde
	 * physique soit mis a jour.
	 * 
	 * @param delta Le temps ecoule pendant la mise a jour du monde physique
	 */
	public void preUpdate(int delta);
	
	/**
	 * Met a jour l'entite.
	 * Cette methode est appelee a chaque fois que le monde physique est mis a jour.
	 * 
	 * @param delta Le temps ecoule pendant la mise a jour du monde physique
	 */
	public void update(int delta);
	
	/**
	 * Affiche l'entite a l'ecran
	 * 
	 * @param g Le contexte graphique sur lequel afficher l'entite
	 */
	public void render(Graphics g);
}