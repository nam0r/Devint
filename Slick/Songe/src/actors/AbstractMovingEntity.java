package actors;

public abstract class AbstractMovingEntity extends AbstractEntity {
	
	protected float minScale;
	protected float maxScale;
	
	protected float scaleIncr;
	
	/* ************ *
	 * Constructors *
	 * ************ */
	
	public AbstractMovingEntity(String path) {
		super(path);
		initScales();
	}
	
	public AbstractMovingEntity() {
		initScales();
	}
	
	private void initScales() {
		this.minScale = 1.0f;
		this.maxScale = 5.0f;
		this.scaleIncr = 0.1f;
	}
	
	/* **************** *
	 * Public Functions *
	 * **************** */

	/**
	 * @param delta Coeff qui depend du nombre de FPS
	 * > 0 => Rotation droite
	 * < 0 => Rotation gauche
	 */
	public void rotate(int delta, float angle) {
		image.rotate(angle * delta);
	}
	
	/**
	 * @param delta Coeff qui depend du nombre de FPS
	 * > 0 => Avant
	 * < 0 => Arriere
	 */
	public void move(int delta) {
		float hip = 0.4f * delta;

		float rotation = image.getRotation();

		setX(getX() + hip * (float)Math.sin(Math.toRadians(rotation)));
		setY(getY() + hip * (float)Math.cos(Math.toRadians(rotation)));
	}
	
	/**
	 * Reduit l'echelle de l'entite de scaleIncr, seulement si
	 * la taille de l'entite reste superieure a minScale.
	 */
	public void reduce(int delta) {
		scale -= (scale <= minScale) ? 0 : scaleIncr * delta;
		image.setCenterOfRotation(image.getWidth() / 2.0f * scale,
				image.getHeight() / 2.0f * scale);
	}
	
	/**
	 * Augment l'echelle de l'entite de scaleIncr, seulement si
	 * la taille de l'entite reste inferieure a minScale.
	 */
	public void enlarge(int delta) {
		scale += (scale >= maxScale) ? 0 : scaleIncr * delta;
		image.setCenterOfRotation(image.getWidth() / 2.0f * scale,
				image.getHeight() / 2.0f * scale);
	}
	
	/* ******* *
	 * Setters *
	 * ******* */
	
	/*
	@Override
	public void setScale(float scale) {
		if(scale >= minScale && scale <= maxScale)
			super.setScale(scale);
		else
			throw new ScaleOutOfBoundsException();
	}
	
	public void setMinScale(float minScale) {
		this.minScale = minScale;
	}
	
	public void setMaxScale(float maxScale) {
		this.maxScale = maxScale;
	}
	
	public void setScaleIncr(float scaleIncr) {
		this.scaleIncr = scaleIncr;
	}
	*/
	
	/* ********* *
	 * To String *
	 * ********* */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("===== Moving entity =====").append("\n");
		sb.append(super.toString()).append("\n");
		sb.append("Min scale : ").append(minScale).append("\n");
		sb.append("Max scale : ").append(maxScale).append("\n");
		sb.append("Scale increment : ").append(scaleIncr);
		return sb.toString();
	}
	
	@SuppressWarnings("serial")
	public class ScaleOutOfBoundsException extends RuntimeException {
		public ScaleOutOfBoundsException() {
			super("L'échelle n'est pas comprise entre minScale et maxScale !");
		}
	}

}