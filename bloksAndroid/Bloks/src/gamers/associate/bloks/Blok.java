/**
 * 
 */
package gamers.associate.bloks;

import java.util.ArrayList;
import com.badlogic.gdx.physics.box2d.World;
import gamers.associate.framework.PhysicElement;

/**
 * @author Vince
 * Base blok class
 */
public abstract class Blok extends PhysicElement {
	
	protected ArrayList<Blok> contactList;
	
	/**
	 * @param world
	 * @param renderRatio
	 * @param width
	 * @param height
	 */
	public Blok(World world, float renderRatio, float width, float height) {
		super(world, renderRatio, width, height);
		this.contactList = new ArrayList<Blok>();
	}
	
	public abstract void select();
	
	public abstract void deselect();
	
	public void addContact(Blok contactBlok) {
		this.contactList.add(contactBlok);
	}
	
	public void removeContact(Blok contactBlok) {
		this.contactList.remove(contactBlok);
	}
	
	public ArrayList<Blok> getContacts() {
		return this.contactList;
	}
}
