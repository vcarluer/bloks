/**
 * Basic sprite animation
 */
package gamers.associate.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.graphics.Sprite;
import com.badlogic.gdx.graphics.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Vince
 *
 */
public class SpriteAnimation {
	protected Sprite mSprite;
	protected int mFrameWidth;
	protected int mFrameHeight;
	protected int mFPS;
	protected int mNoOfFrames;
	protected int mCurrentFrame;
	protected long mFrameTimer;
	protected float mInitialRotation;
	protected int mWorldRatio;	
	protected Boolean mTileX;
	
	/**
	 * @param sprite
	 * @param frameWidth
	 * @param frameHeight
	 */
	public SpriteAnimation(String spriteFile, int frameWidth, int frameHeight) {
		this(spriteFile, frameWidth, frameHeight, -1, 1);
	}
	
	public SpriteAnimation(String spriteFile, int frameWidth, int frameHeight, Boolean tileX) {
		this(spriteFile, frameWidth, frameHeight, -1, 1, tileX);
	}
	
	/**
	 * @param sprite
	 * @param frameWidth
	 * @param frameHeight
	 * @param theFPS
	 * @param theFrameCount
	 */
	public SpriteAnimation(String spriteFile, int frameWidth, int frameHeight, int theFPS, int theFrameCount) {
		this(spriteFile, frameWidth, frameHeight, theFPS, theFrameCount, false);
	}
	
	public SpriteAnimation(String spriteFile, int frameWidth, int frameHeight, int theFPS, int theFrameCount, Boolean tileX) {
		mFrameTimer =0;
        mCurrentFrame =0;
        mSprite = this.getSprite(spriteFile);
        mFrameWidth = frameWidth;
        mFrameHeight = frameHeight;
        mFPS = theFPS;
        mNoOfFrames = theFrameCount;
        mTileX = tileX;
	}
	
	/**
	 * @return the Sprite
	 */
	public Sprite getSprite() {
		return mSprite;
	}
	/**
	 * @return the FrameWidth
	 */
	public int getFrameWidth() {
		return mFrameWidth;
	}
	/**
	 * @return the FrameHeight
	 */
	public int getFrameHeight() {
		return mFrameHeight;
	}
	/**
	 * @return the mFPS
	 */
	public int getFPS() {
		return mFPS;
	}
	/**
	 * @return the mNoOfFrames
	 */
	public int getNoOfFrames() {
		return mNoOfFrames;
	}
	/**
	 * @return the mCurrentFrame
	 */
	public int getCurrentFrame() {
		return mCurrentFrame;
	}
	/**
	 * @return the mFrameTimer
	 */
	public long getFrameTimer() {
		return mFrameTimer;
	}
	/**
	 * @return the mInitialRotation
	 */
	public float getInitialRotation() {
		return mInitialRotation;
	}
		
	/**
	 * @param gameTime
	 * @param batch
	 * @param pos
	 * @param angle
	 * @param width
	 * @param height
	 */
	public void updateAndDraw(long gameTime, SpriteBatch batch, Vector2 pos, float angle, float width, float height) {
		this.update(gameTime);
		this.draw(batch, pos, angle, width, height);
	}
	
	/**
	 * @param spriteFile
	 * @return
	 */
	protected Sprite getSprite(String spriteFile) {
		Texture texture = Gdx.graphics.newTexture(
				Gdx.files.getFileHandle(spriteFile, FileType.Internal), 
				TextureFilter.MipMap, 
				TextureFilter.Linear, 
				TextureWrap.ClampToEdge, 
				TextureWrap.ClampToEdge);
		Sprite sprite = new Sprite(texture);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		return sprite;
	}
	
	 /**
	 * @param gameTime
	 */
	protected void update(long gameTime) {
        if (mFPS != -1) {
			if(gameTime > mFrameTimer + mFPS ) {
	            mFrameTimer = gameTime;
	            mCurrentFrame +=1;
	            if(mCurrentFrame >= mNoOfFrames) {
	            	this.mSprite.setTextureRegion(0, 0, mFrameWidth, mFrameHeight);
	            	mCurrentFrame = 0;
	            }
	            else
	            {
	            	this.mSprite.setTextureRegion(mCurrentFrame * mFrameWidth, 0, mFrameWidth, mFrameHeight);
	            }
	        }
        }
        else
        {
        	this.mSprite.setTextureRegion(0, 0, mFrameWidth, mFrameHeight);
        }
    }
	
	/**
	 * @param batch
	 * @param pos
	 * @param angle
	 * @param width
	 * @param height
	 */
	protected void draw(SpriteBatch batch, Vector2 pos, float angle, float width, float height) {
		if (batch != null && pos != null) {
			//this.mSprite.rotate(mInitialRotation);
			this.mSprite.setSize(width, height);
			this.mSprite.setOrigin(width / 2, height / 2);
			float posX = pos.x;
			float posY = pos.y;
			this.mSprite.setPosition(posX, posY);
			this.mSprite.setRotation(angle);
			this.mSprite.draw(batch);	        	        
		}
	}	
}
