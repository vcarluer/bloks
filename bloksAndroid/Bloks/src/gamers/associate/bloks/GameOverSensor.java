package gamers.associate.bloks;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import gamers.associate.framework.PhysicElement;

public class GameOverSensor extends PhysicElement {
	protected static Integer ANIM_ZONE;
	protected Boolean isGameOver;
	
	public GameOverSensor(World world, float renderRatio, float width,
			float height, float initialX, float initialY) {
		super(world, renderRatio, width, height);
		this.createBody(initialX, initialY);
		isGameOver = false;
	}
	
	public void Activate() {
		isGameOver = true;	
	}
	
	public void Desactivate() {
		isGameOver = false;	
	}
	
	public Boolean IsGameOver() {
		return isGameOver;
	}
	
	@Override
	protected void createBody(float initialX, float initialY) {
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.StaticBody;
		boxDef.position.set(initialX, initialY);		
		this.mBody = this.mWorld.createBody(boxDef);
		
		PolygonShape dynamicBox = new PolygonShape();				
		dynamicBox.setAsBox(mWidth / 2, mHeight / 2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 0.0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.isSensor = true;

		this.mBody.createFixture(fixtureDef);
		this.mBody.setUserData(this);
	}

	@Override
	protected void createAnimationList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createSoundList() {
		// TODO Auto-generated method stub
		
	}

}
