package gamers.associate.bloks;

import gamers.associate.framework.PhysicElement;
import gamers.associate.framework.SpriteAnimation;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Vince
 *
 */
public class BloksLevel extends PhysicElement {
	private static Integer BACKGROUND = 0;
	
	private BloksBorder topBorder;
	private BloksBorder rightBorder;
	private BloksBorder bottomBorder;
	private BloksBorder leftBorder;
	private GameOverSensor gameOverSensor;
	private Music mMusic;
	private boolean isMusicPlaying;
	
	/**
	 * @param world
	 * @param width
	 * @param height
	 */
	public BloksLevel(World world, float renderRatio, float width, float height) {
		super(world, renderRatio, width, height);
		
		this.mPos = new Vector2(width / 2, height / 2);
		
		float borderSize = 1;
		this.topBorder = new BloksBorder(world, renderRatio, width + borderSize * 2, borderSize, width / 2, height + borderSize / 2);
		this.rightBorder = new BloksBorder(world, renderRatio, borderSize, height + borderSize * 2, width + borderSize / 2, height / 2);
		this.bottomBorder = new BloksBorder(world, renderRatio, width + borderSize * 2, borderSize, width / 2, - borderSize / 2);
		this.leftBorder	 = new BloksBorder(world, renderRatio, borderSize, height + borderSize *2, - borderSize / 2, height /2);
		this.gameOverSensor = new GameOverSensor(world, renderRatio, width, borderSize, width / 2, height - borderSize / 2);
		
		this.createAnimationList();
	}
	
	public Boolean IsGameOver() {
		return this.gameOverSensor.IsGameOver();
	}
	
	public void restart() {
		this.gameOverSensor.Desactivate();
	}
	
	/* (non-Javadoc)
	 * @see gamers.associate.framework.GameElement#render(long, com.badlogic.gdx.graphics.SpriteBatch)
	 */
	@Override
	public void render(long gameTime, SpriteBatch batch) {
		super.render(gameTime, batch);
		
		this.topBorder.render(gameTime, batch);
		this.rightBorder.render(gameTime, batch);
		this.bottomBorder.render(gameTime, batch);
		this.leftBorder.render(gameTime, batch);
	}
	
	/* (non-Javadoc)
	 * @see gamers.associate.framework.GameElement#playSound()
	 */
	@Override
	public void  playSound() {
		if (!this.isMusicPlaying && this.mMusic != null) {
			this.mMusic.setLooping(true);
			this.mMusic.play();
		}
		
		super.playSound();		
	}

	@Override
	protected void createAnimationList() {
		this.mAnimationList.put(BACKGROUND, new SpriteAnimation("data/farm.png", 1674, 954));
		this.mCurrentAnimation = this.mAnimationList.get(BACKGROUND);		
	}

	@Override
	protected void createBody(float initialX, float initialY) {
		// No body
		
	}

	@Override
	protected void createSoundList() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected float getRenderWidth() {
		float height = this.getRenderHeight();
		float whRatio = height / this.mCurrentAnimation.getFrameHeight();
		return this.mCurrentAnimation.getFrameWidth() * whRatio;
	}	
}
