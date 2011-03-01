package menu;

import actors.AbstractMovingEntity;

public class MenuButton extends AbstractMovingEntity {

	private float x;
	private float y;
	
	public MenuButton(String path, float x, float y) {
		super(path);
		
		this.minScale = 1.0f;
		this.maxScale = 1.05f;
		this.scaleIncr = 0.0001f;
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

}
