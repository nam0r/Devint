package environment;

import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import actors.PhysicalEntity;

/**
 * The common bits of all environments. Holds a physics world, allows addition
 * of entities and their physical representation.
 * 
 * @author kevin
 */
public abstract class Environment implements EnvironmentInterface {
	/** The physical world the environment provides to it's entities */
	protected World world = new World(new Vector2f(0,10), 20); 

	/** The entities list */
	protected ArrayList<PhysicalEntity> entities = new ArrayList<PhysicalEntity>();
	/** The amount time in ms passed since last update */
	private int totalDelta;
	/** The amount of time to pass before updating the physics world */
	private int stepSize = 5;

	/**
	 * Add an entity to the environment. This will include it's physical
	 * body in the world.
	 * 
	 * @param entity The entity to be added
	 */
	public void addEntity(PhysicalEntity entity) {
		if (entity.getBody() != null) {
			System.out.println(entity.getBody());
			world.add(entity.getBody());
		}
		
		entities.add(entity);
		entity.setWorld(world);
	}
	
	public void removeEntity(PhysicalEntity entity) {
		world.remove(entity.getBody());
		entities.remove(entity);
	}
	
	public PhysicalEntity getEntityByBody(Body body) {
		for(PhysicalEntity entity : entities) {
			if(entity.getBody().equals(body)) {
				return entity;
			}
		}
		return null;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public void update(int delta) {
		boolean first = true;
		
		totalDelta += delta;
		while (totalDelta > stepSize) {
			world.step(stepSize * 0.01f);
			totalDelta -= stepSize;

			if (first) {
				first = false;
				for (int i=0;i<entities.size();i++) {
					entities.get(i).preUpdate(delta);
				}
			}
			
			for (int i=0;i<entities.size();i++) {
				entities.get(i).update(stepSize);
			}
		}
	}
}
