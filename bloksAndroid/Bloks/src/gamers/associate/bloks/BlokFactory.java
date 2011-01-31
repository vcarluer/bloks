/**
 * 
 */
package gamers.associate.bloks;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Vince
 *
 */
public class BlokFactory {
	private static Random randomGenerator = new Random();
	public static float blockSize = 2f;
	
	/**
	 * @param world
	 * @param renderRatio
	 * @param x
	 * @param y
	 * @return blok test
	 */
	public static BlokChicken createBChicken(World world, float renderRatio, float x, float y) {
		return new BlokChicken(
				world, 
				renderRatio, 
				blockSize, blockSize, 
				x, 
				y);
	}
	
	public static BlokChicken createBChickenRandomX(World world, float renderRatio, float minX, float maxX, float minY, float maxY) {		
		
		return new BlokChicken(
				world, 
				renderRatio, 
				blockSize, blockSize, 
				getRandom(minX, maxX), 
				getRandom(minY, maxY));
	}
	
	public static ArrayList<BlokChicken> createBChickenRandomX(World world, float renderRatio, float minX, float maxX, float minY, float maxY, int count) {
		ArrayList<BlokChicken> bloks = new ArrayList<BlokChicken>();
		for(int i = 0; i < count; i++) {
			bloks.add(createBChickenRandomX(world, renderRatio, minX, maxX, minY, maxY));
		}
		
		return bloks;
	}
	
	public static BlokCock createBCock(World world, float renderRatio, float x, float y) {
		return new BlokCock(
				world, 
				renderRatio, 
				blockSize, blockSize, 
				x, 
				y);
	}
	
	public static void deleteBlok(World world, Blok blok) {
		if (blok != null) {
			world.destroyBody(blok.getBody());
		}
	}
	
	public static float getRandom(float min, float max) {
		float random = randomGenerator.nextFloat();
		float x = max * random;
		if (x < min) {
			x = min;
		}
		if (x > max) {
			x = max;
		}
		
		return x;
	}
}
