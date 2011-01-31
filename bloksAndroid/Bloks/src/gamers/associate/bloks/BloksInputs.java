/**
 * 
 */
package gamers.associate.bloks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Vince
 *
 */
public class BloksInputs implements InputProcessor {
	private BloksGame mGame;
	private float mRenderRatio;
		
	private Vector2 lastTouchDown;
	private long timeTouchDown;
	private Vector2 lastTouchUp;
	private Vector2 lastTouch;
	private Boolean hasDragged;
	
	private long timeForHasDragged = 0;
	
	public BloksInputs(BloksGame game, float renderRatio) {
		this.mGame = game;
		this.mRenderRatio = renderRatio;
	}
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int fingerId) {		
		this.timeTouchDown = System.currentTimeMillis();
		Vector2 pos = this.getPos(screenX, screenY);		
		
		this.mGame.getSelecter().select(pos.x, pos.y, this.mGame.getBloks());
		
		this.lastTouchDown = pos;
		this.lastTouch = pos;
		this.hasDragged = false;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int fingerId) {
		Vector2 pos = this.getPos(screenX, screenY);
				
		if (System.currentTimeMillis() - this.timeTouchDown >= this.timeForHasDragged) {
			this.hasDragged = true;
		}
		
		this.lastTouch = pos;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int fingerId) {
		Vector2 pos = this.getPos(screenX, screenY);
		
		if (this.hasDragged) {
			this.mGame.getPushAction().push(this.mGame.getSelecter().getSelected(), this.lastTouchDown, pos);
		}
		else {
			this.mGame.getScoreAction().tryToScore(this.mGame.getSelecter().getSelected(), this.mGame.getBloks());
		}
			
		
		this.mGame.getSelecter().deselect();
		this.lastTouchUp = pos;
		this.hasDragged = false;
		return true;		
	}
	
	private Vector2 getPos(int screenX, int screenY) {
		return new Vector2(screenX / this.mRenderRatio, (Gdx.graphics.getHeight() - screenY) / this.mRenderRatio);
	}

}
