/**
 * 
 */
package gamers.associate.bloks;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import gamers.associate.framework.PhysicFxElement;

/**
 * @author Vince
 *
 */
public class MagnetAction extends PhysicFxElement {
	protected Boolean isEnable;
	protected float mStrength;
	protected float mRadius;
	
	/**
	 * @param world
	 * @param renderRatio
	 */
	public MagnetAction(World world, float renderRatio) {
		super(world, renderRatio);
		this.mStrength = 3.0f;
		this.mRadius = 3.0f;
		this.isEnable = false;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param bloks
	 */
	public void DoMagnet(float x, float y, ArrayList<Blok> bloks) {
		if (this.isEnable) {
			for(Blok blok : bloks) {			
				Vector2 pos = blok.getPos();				
				Vector2 force = new Vector2(
						x - pos.x,
						y - pos.y);
				if (force.len() <= this.mRadius) {
					force.nor().mul(this.mStrength);
					blok.getBody().applyLinearImpulse(force, blok.getPos());
				}
			}
		}
	}
	
	/**
	 * @param bloks
	 */
	public void DoMagnet(ArrayList<Blok> bloks) {
		Vector2 pos = this.getPos();
		if (pos != null) {
			this.DoMagnet(pos.x, pos.y, bloks);
		}
	}
	
	/**
	 * 
	 */
	public void activate() {
		this.isEnable = true;
	}
	
	/**
	 * 
	 */
	public void desactivate() {
		this.isEnable = false;
	}
	
	public void setStrength(float strength) {
		this.mStrength = strength;
	}
}
