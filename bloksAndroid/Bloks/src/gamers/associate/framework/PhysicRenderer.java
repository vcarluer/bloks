/**
 * 
 */
package gamers.associate.framework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Vince
 *
 */
public class PhysicRenderer {
	private long gameTime;
	private int velocityIterations = 6;
	private int positionIterations = 2;
	private Vector2 mGravityVector;
	private World world;
	private float mTargetFPS;
	private float dt; 
	private float accumulator = 0.0f;	
	
	/**
	 * @param gravityVector
	 * @param targetFPS
	 */
	public PhysicRenderer(Vector2 gravityVector, float targetFPS) {
		this.mGravityVector = gravityVector;
		this.mTargetFPS = targetFPS;
		this.dt = 1000f / this.mTargetFPS;
		
		boolean doSleep = true;
		this.world = new World(this.mGravityVector, doSleep);
		this.gameTime = System.currentTimeMillis();
	}
	
	public void computeNext() {
		long newTime = System.currentTimeMillis();
		long frameTime = newTime - this.gameTime;
		this.gameTime = newTime;
		
		accumulator += frameTime;
		
		int loopCount = 0;
		while (accumulator >= dt && loopCount < 4) {																
			this.world.step(1.0f / this.mTargetFPS, velocityIterations, positionIterations);
			accumulator -= dt;
			loopCount++;
		}
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public long getGameTime() {
		return this.gameTime;
	}
}
