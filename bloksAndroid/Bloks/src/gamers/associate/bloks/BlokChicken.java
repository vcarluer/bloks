/**
 * 
 */
package gamers.associate.bloks;

import com.badlogic.gdx.graphics.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import gamers.associate.framework.SpriteAnimation;

/**
 * @author Vince
 * Test block
 */
public class BlokChicken extends Blok {
	private static Integer ANIM_SELECT = 0;
	private static Integer ANIM_UNSELECT = 1;
	
	/**
	 * @param world
	 */
	public BlokChicken(World world, float renderRatio, float width, float height, float initialX, float initialY) {
		super(world, renderRatio, width, height);		
		this.createBody(initialX, initialY);
		this.createAnimationList();
	}

	/* (non-Javadoc)
	 * @see gamers.associate.framework.PhysicElement#createBody(float, float)
	 */
	@Override
	protected void createBody(float initialX, float initialY) {
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.DynamicBody;
		boxDef.position.set(initialX, initialY);		
		this.mBody = this.mWorld.createBody(boxDef);
		
		PolygonShape dynamicBox = new PolygonShape();				
		dynamicBox.setAsBox(mWidth / 2, mHeight / 2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;
		fixtureDef.restitution = 0.2f;

		this.mBody.createFixture(fixtureDef);
		this.mBody.setUserData(this);
	}

	@Override
	protected void createAnimationList() {
		this.mAnimationList.put(ANIM_SELECT, new SpriteAnimation("data/chickenSelectR.png", 32, 32));
		this.mAnimationList.put(ANIM_UNSELECT, new SpriteAnimation("data/chickenR.png", 32, 32));		
		this.mCurrentAnimation = this.mAnimationList.get(ANIM_UNSELECT);
	}

	@Override
	protected void createSoundList() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void select() {
		this.mCurrentAnimation = this.mAnimationList.get(ANIM_SELECT);
		
	}

	@Override
	public void deselect() {
		this.mCurrentAnimation = this.mAnimationList.get(ANIM_UNSELECT);		
	}
	
	@Override
	public void render(long gameTime, SpriteBatch batch) {
		super.render(gameTime, batch);
	}	
}
