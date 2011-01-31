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
public class ScoreAction extends PhysicFxElement {	
	protected int mScore;
	protected ArrayList<Blok> scoredBlok;
	protected float mScoreAngle;
	
	public ScoreAction(World world, float renderRatio) {
		super(world, renderRatio);
		mScore = 0;
		this.scoredBlok = new ArrayList<Blok>();
	}
	
	public void Reset() {
		this.mScore = 0;
	}
	
	public void addScore(int scoreToAdd) {
		this.mScore += scoreToAdd;
	}
	
	public int tryToScore(Blok selectedBlok, ArrayList<Blok> bloks) {
		int score = 0;
		
		if (selectedBlok != null) {
			this.scoredBlok.clear();
			this.mScoreAngle = selectedBlok.getAngle();
			int deleteCount = this.score(selectedBlok, bloks);
			
			
			if (deleteCount >= 3 ) {
				for(Blok blok : this.scoredBlok) {
					for(Blok contactBlok : blok.getContacts()) {
						contactBlok.removeContact(blok);
					}
					
					bloks.remove(blok);
					BlokFactory.deleteBlok(this.mWorld, blok);
				}
				
				score = deleteCount * deleteCount;
				mScore += score;
			}
		}
		
		return score;
	}
		
	protected int score(Blok selectedBlok, ArrayList<Blok> bloks) {
		int deleteCount = 0;
		
		if (selectedBlok != null) {
			if (!this.scoredBlok.contains(selectedBlok)) {								
				this.scoredBlok.add(selectedBlok);
				deleteCount++;
				for(Blok blok : selectedBlok.getContacts()) {
					if (!this.scoredBlok.contains(blok)) {
						if (Math.abs(blok.getAngle() - mScoreAngle) < 10) {					
							deleteCount += this.score(blok, bloks);
						}
					}
				}				
			}						
		}
		
		return deleteCount;
	}
	
	public int getScore() {
		return this.mScore;
	}
}
