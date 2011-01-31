package gamers.associate.bloks;

import java.util.ArrayList;

import gamers.associate.framework.SpriteAnimation;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BlokCock extends Blok {
	private static Integer ANIM_DEFAULT = 0;
	private MagnetAction magnet;
	
	public BlokCock(World world, float renderRatio, float width, float height, float initialX, float initialY) {
		super(world, renderRatio, width, height);
		
		this.createBody(initialX, initialY);
		this.createAnimationList();
		this.magnet = new MagnetAction(world, renderRatio);
		this.magnet.activate();
	}
	
	public void pullChickens(ArrayList<Blok> chickens) {
		this.magnet.DoMagnet(this.getPos().x, this.getPos().y, chickens);
	}

	@Override
	public void deselect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createAnimationList() {
		this.mAnimationList.put(ANIM_DEFAULT, new SpriteAnimation("data/cockR.png", 32, 32));
		this.mCurrentAnimation = this.mAnimationList.get(ANIM_DEFAULT);		
	}

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
	protected void createSoundList() {
		// TODO Auto-generated method stub
		
	}

}
