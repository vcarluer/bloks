package gamers.associate.bloks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import gamers.associate.framework.PhysicFxElement;

public class PushAction extends PhysicFxElement {
	protected float mStrength;	
	
	public PushAction(World world, float renderRatio) {
		super(world, renderRatio);
		
		this.mStrength = 5.0f * BlokFactory.blockSize;
	}
	
	public void push(Blok blok, Vector2 startDrag, Vector2 stopDrag) {
		if (blok != null) {			
			Vector2 distance = new Vector2(
					blok.getPos().x - startDrag.x,
					blok.getPos().y - startDrag.y);
			if (distance.len() <= 1.0f) {
				Vector2 force = new Vector2(
						stopDrag.x - startDrag.x,
						stopDrag.y - startDrag.y);		
				force.mul(this.mStrength);
				blok.getBody().applyLinearImpulse(force, startDrag);
			}			
		}
	}

}
