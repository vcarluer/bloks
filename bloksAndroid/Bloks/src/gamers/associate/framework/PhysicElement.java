/**
 * 
 */
package gamers.associate.framework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Vince
 *
 */
public abstract class PhysicElement extends GameElement {
	protected Body mBody;
	protected World mWorld;
	protected float mRenderRatio;

	public PhysicElement(World world, float renderRatio, float width, float height) {
		super(width, height);
		mWorld = world;
		mRenderRatio = renderRatio;
	}
	
	/**
	 * @return the Body
	 */
	public Body getBody() {
		return mBody;
	}
	
	/* (non-Javadoc)
	 * @see gamers.associate.framework.GameElement#getPos()
	 */
	@Override public Vector2 getPos(){
		if (mBody != null){
			return mBody.getPosition();
		}
		else {
			return super.getPos();
		}
	}
	
	/* (non-Javadoc)
	 * @see gamers.associate.framework.GameElement#getAngle()
	 */
	@Override public float getAngle() {
		if (mBody != null){
			float rad = (float) Util.normalRelativeAngle(this.mBody.getAngle());
			return (float) Math.toDegrees(rad);
		}
		else {
			return super.getAngle();
		}
	}
	
	/**
	 * @param initialX
	 * @param initialY
	 */
	protected abstract void createBody(float initialX, float initialY);
	
	/**
	 * 
	 */
	protected abstract void createAnimationList();
	
	/**
	 * 
	 */
	protected abstract void createSoundList();
	
	/* (non-Javadoc)
	 * @see gamers.associate.framework.GameElement#getRenderScale()
	 */
	@Override
	protected float getRenderScale() {
		return this.mRenderRatio;
	}
}
