package gamers.associate.bloks;

import gamers.associate.bloks.BloksGame;

import com.badlogic.gdx.backends.android.AndroidApplication;
import android.os.Bundle;

/**
 * @author Vince
 *
 */
public class Bloks extends AndroidApplication {
    /** Called when the activity is first created. */
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initialize(new BloksGame(), false);
    }
}