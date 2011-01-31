/**
 * Gamers associate android framework
 */
package gamers.associate.framework;

import java.util.Hashtable;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Vince
 * Universal game element
 */
public abstract class GameElement implements IDrawable {
		
	protected Vector2 mPos;
	protected float mAngle;
	protected float mWidth;
	protected float mHeight;
	protected int mZOrder;
	protected Sound mCurrentSound;
	protected SpriteAnimation mCurrentAnimation;
	protected Hashtable<Integer, Sound> mSoundList;
	protected Hashtable<Integer, SpriteAnimation> mAnimationList;	
	
	/**
	 * Game Element constructor
	 */
	public GameElement() {
		this.mSoundList = new Hashtable<Integer, Sound>();
		this.mAnimationList = new Hashtable<Integer, SpriteAnimation>();		
		// this.mPos = new Vector2();
	}
	
	/**
	 * @param width
	 * @param height
	 */
	public GameElement(float width, float height) {
		this();
		this.mWidth = width;
		this.mHeight = height;
	}
	
	/**
	 * @return the Pos
	 */
	public Vector2 getPos() {
		return mPos;
	}

	/**
	 * @param pos the Pos to set
	 */
	public void setPos(Vector2 pos) {
		this.mPos = pos;
	}

	/**
	 * @return the Angle
	 */
	public float getAngle() {
		return mAngle;
	}

	/**
	 * @param angle the Angle to set
	 */
	public void setAngle(float angle) {
		this.mAngle = angle;
	}
	
	/**
	 * @return the Width
	 */
	public float getWidth() {
		return mWidth;
	}

	/**
	 * @param mWidth the Width to set
	 */
	public void setWidth(float width) {
		this.mWidth = width;
	}

	/**
	 * @return the Height
	 */
	public float getHeight() {
		return mHeight;
	}

	/**
	 * @param mHeight the Height to set
	 */
	public void setHeight(float height) {
		this.mHeight = height;
	}
	
	/**
	 * @return the Current Sound
	 */
	public Sound getCurrentSound() {
		return mCurrentSound;
	}
	
	/**
	 * @return the Current Animation
	 */
	public SpriteAnimation getCurrentAnimation() {
		return mCurrentAnimation;
	}

	/**
	 * @return the Sound List
	 */
	public Hashtable<Integer, Sound> getSoundList() {
		return mSoundList;
	}
	
	/**
	 * @return the Animation List
	 */
	public Hashtable<Integer, SpriteAnimation> getAnimationList() {
		return mAnimationList;
	}

	/**
	 * @return the ZOrder
	 */
	public int getZOrder() {
		return mZOrder;
	}

	/**
	 * @param zOrder the ZOrder to set
	 */
	public void setZOrder(int zOrder) {
		this.mZOrder = zOrder;
	}
	
	/**
	 * @param batch
	 */
	public void render(long gameTime, SpriteBatch batch) {
		Vector2 pos = new Vector2(this.getPos());
		if (this.mCurrentAnimation != null &&  pos != null){
			this.mCurrentAnimation.updateAndDraw(
					gameTime, 
					batch, 
					this.getDrawPos(), 
					this.getAngle(), 
					this.getRenderWidth(), 
					this.getRenderHeight());
		}
	}
	
	/**
	 * Play current sound
	 */
	public void playSound() {
		if (this.mCurrentSound != null){
			this.mCurrentSound.play();
		}
	}
	
	protected float getRenderWidth() {
		return this.mWidth * this.getRenderScale();
	}
	
	protected float getRenderHeight() {
		return this.mHeight * this.getRenderScale();
	}
	
	/**
	 * @return
	 */	
	protected abstract float getRenderScale();
	
	protected Vector2 getDrawPos() {
		Vector2 pos = new Vector2(this.getPos());
		pos.x = pos.x - this.mWidth / 2;
		pos.y = pos.y - this.mHeight / 2;
		pos.mul(this.getRenderScale());
		return pos;
	}
}
