/**
 * 
 */
package gamers.associate.bloks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import gamers.associate.framework.PhysicElement;
import gamers.associate.framework.SpriteAnimation;

/**
 * @author Vince
 * Bloks level
 */
public class BloksBorder extends PhysicElement {	
	private static Integer ANIM_DEFAULT = 0;
	
	/**
	 * @param world
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public BloksBorder(World world, float renderRatio, float width, float height, float x, float y) {
		super(world, renderRatio, width, height);
		this.mWidth = width;
		this.mHeight = height;
		this.createBody(x, y);
		this.createAnimationList();
	}
	
	/**
	 * @param initialX
	 * @param initialY
	 */
	protected void createBody(float initialX, float initialY) {
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.type = BodyType.StaticBody;
		groundBodyDef.position.set(new Vector2(initialX, initialY));		
		this.mBody = this.mWorld.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(this.mWidth / 2, this.mHeight / 2);
		
		FixtureDef groundFixture = new FixtureDef();
		groundFixture.shape = groundBox;		
		
		this.mBody.createFixture(groundFixture);
	}

	@Override
	protected void createAnimationList() {
		this.mAnimationList.put(ANIM_DEFAULT, new SpriteAnimation("data/wood.png", 32, 32));
		this.mCurrentAnimation = this.mAnimationList.get(ANIM_DEFAULT);
	}

	@Override
	protected void createSoundList() {
		// TODO Auto-generated method stub
		
	}

}
