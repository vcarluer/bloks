package gamers.associate.bloks;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;

public class ContactManager implements ContactListener {
	
	@Override
	public void beginContact(Contact contact) {
		if (contact.getFixtureA() != null && contact.getFixtureB() != null) {
			Object oA = contact.getFixtureA().getBody().getUserData();
			Object oB = contact.getFixtureB().getBody().getUserData();
			if (oA instanceof BlokChicken && oB instanceof BlokChicken) {
				Blok bA = (Blok)oA;
				Blok bB = (Blok)oB;
				bA.addContact(bB);
				bB.addContact(bA);
			}
			
			if (oA instanceof GameOverSensor) {
				((GameOverSensor)oA).Activate();
			}
			
			if (oB instanceof GameOverSensor) {
				((GameOverSensor)oB).Activate();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {		
		if (contact.getFixtureA() != null && contact.getFixtureB() != null) {
			Object oA = contact.getFixtureA().getBody().getUserData();
			Object oB = contact.getFixtureB().getBody().getUserData();
			if (oA instanceof BlokChicken && oB instanceof BlokChicken) {
				Blok bA = (Blok)oA;
				Blok bB = (Blok)oB;
				bA.removeContact(bB);
				bB.removeContact(bA);
			}
		}
	}

}
