package gamers.associate.bloks;

import com.badlogic.gdx.backends.jogl.JoglApplication;

/**
 * @author Vince
 *
 */
public class BloksDesktop {
	/**
	 * Main java program class
	 * @param argv
	 */
	public static void main(String[] argv) {
		new JoglApplication(new BloksGame(), "Bloks", 480, 800, false);
	}
}
