/**
 * 
 */
package gamers.associate.bloks;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;

import gamers.associate.framework.PhysicFxElement;

/**
 * @author Vince
 *
 */
public class BlokSelecter extends PhysicFxElement {	
	protected Blok mSelectedBlok;
	
	/**
	 * @param world
	 * @param renderRatio
	 */
	public BlokSelecter(World world, float renderRatio) {
		super(world, renderRatio);		
	}
		
	/**
	 * @param selectX
	 * @param selectY
	 * @param bloks
	 */
	public void select(float selectX, float selectY, ArrayList<Blok> bloks) {
		this.deselect();
		
		for(Blok blok : bloks) {
			float w2 = blok.getWidth() / 2;
			float h2 = blok.getHeight() / 2;
			float posX = blok.getPos().x;
			float posY = blok.getPos().y;
			
			if (selectX <= posX + w2 &&
				selectX >= posX - w2 &&				
				selectY <= posY + h2 &&
				selectY >= posY - h2) {
				
				this.mSelectedBlok = blok;
				this.mSelectedBlok.select();
				break;
			}
		}
	}
	
	/**
	 * 
	 */
	public void deselect() {
		if (this.mSelectedBlok != null) {		
			this.mSelectedBlok.deselect();
			this.mSelectedBlok = null;
		}
	}
	
	public Blok getSelected() {
		return this.mSelectedBlok;
	}
	
}
