/**
 * 
 */
package gamers.associate.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.SpriteBatch;

/**
 * @author Vince
 *
 */
public class VideoRenderer {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	public VideoRenderer() {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.font.setColor(Color.RED);
	}
	
	public void beginDraw() {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.spriteBatch.begin();
	}
	
	public void endDraw() {
		this.spriteBatch.end();
	}
	
	/**
	 * @param gameTime
	 * @param drawables
	 */
	public void drawVideo(long gameTime, IDrawable drawable) {	
		drawable.render(gameTime, spriteBatch);		
	}
	
	public void drawText(String text, float x, float y) {
		spriteBatch.setBlendFunction(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		this.font.draw(spriteBatch, text, x, y);
	}
}
