/**
 * 
 */
package gamers.associate.framework;

import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Vince
 * Physic effect element
 */
public abstract class PhysicFxElement extends GameElement {
	protected World mWorld;
	protected float mRenderRatio;

	/**
	 * @param world
	 * @param worldRatio
	 */
	public PhysicFxElement(World world, float renderRatio) {
		this.mWorld = world;
		this.mRenderRatio = renderRatio;
	}
	
	/**
	 * @return the World
	 */
	public World getWorld() {
		return mWorld;
	}

	/* (non-Javadoc)
	 * @see gamers.associate.framework.GameElement#getRenderScale()
	 */
	@Override
	protected float getRenderScale() {
		return this.mRenderRatio;
	}
}
