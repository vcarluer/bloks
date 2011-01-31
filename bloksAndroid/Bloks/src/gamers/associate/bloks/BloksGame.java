package gamers.associate.bloks;

import java.util.ArrayList;
import java.util.Random;

import gamers.associate.framework.IDrawable;
import gamers.associate.framework.PhysicRenderer;
import gamers.associate.framework.VideoRenderer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class BloksGame implements ApplicationListener {	
	// Game Model
	private static int blokCountPerWidth = 9;
	private static int blokCountPerHeight = 15;
	private static int maxBlok = 1;
	private static int spawnTime = 15000;
	private BloksLevel level;
	private ArrayList<Blok> blokList;
	private BlokCock blokCock;
	private Random randomGenerator;	
	private BlokSelecter blokSelecter;
	private PushAction pushAction;
	private ScoreAction scoreAction;
	
	// Inputs
	private BloksInputs inputs;
	
	// Physic
	private PhysicRenderer physicRenderer;
	private ContactManager contactManager;
	private float targetFPS = 120.0f;
	
	// Video
	private VideoRenderer videoRenderer;
	private float renderRatio;
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {			
		this.randomGenerator = new Random();
		
		// renderRatio = (float)Gdx.graphics.getWidth() / (blokCountPerWidth) / BlokFactory.blockSize;
		renderRatio = 24.0f;
		// renderRatio = 8.0f;
		maxBlok = Math.round(Gdx.graphics.getHeight() / renderRatio * (blokCountPerWidth * 1.03f));
		
		// Inputs
		this.inputs = new BloksInputs(this, this.renderRatio);
		Gdx.input.setInputProcessor(this.inputs);
		
		// Video renderer
		this.videoRenderer = new VideoRenderer();					
		
		// Physic renderer
		Vector2 gravityVector = new Vector2(0.0f, -10.0f);		
		this.physicRenderer = new PhysicRenderer(gravityVector, targetFPS);
		this.contactManager = new ContactManager();
		this.physicRenderer.getWorld().setContactListener(this.contactManager);
		
		// Game Model
		this.level = new BloksLevel(
				this.physicRenderer.getWorld(), 
				renderRatio, 
				blokCountPerWidth * BlokFactory.blockSize, 
				blokCountPerWidth * BlokFactory.blockSize);
		
		this.blokList = new ArrayList<Blok>();
		
		this.blokSelecter = new BlokSelecter(this.physicRenderer.getWorld(), renderRatio);
		this.pushAction = new PushAction(this.physicRenderer.getWorld(), renderRatio);
		this.scoreAction = new ScoreAction(this.physicRenderer.getWorld(), renderRatio);		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#render()
	 */
	@Override
	public void render() {				
		
		// State
		if (this.level.IsGameOver()) {
			for(Blok blok : this.blokList) {
				BlokFactory.deleteBlok(this.physicRenderer.getWorld(), blok);				
			}
			
			BlokFactory.deleteBlok(this.physicRenderer.getWorld(), blokCock);
			this.blokCock = null;
			this.blokList.clear();
			this.level.restart();
			this.scoreAction.Reset();
		}
		
		this.blokEngineRender(this.physicRenderer.getGameTime());
		this.physicRenderer.computeNext();		
		
		this.videoRender(this.physicRenderer.getGameTime());
	}
		
	private void videoRender(long gameTime) {
		this.videoRenderer.beginDraw();	
		
		this.videoRenderer.drawVideo(this.physicRenderer.getGameTime(), this.level);
		if (blokCock != null) {
			this.videoRenderer.drawVideo(gameTime, blokCock);
		}
		
		for(IDrawable drawable : this.blokList) {
			this.videoRenderer.drawVideo(gameTime, drawable);
		}
		
		this.videoRenderer.drawText("Score: " + String.valueOf(this.scoreAction.getScore()), 10, Gdx.graphics.getHeight() - 10);
		
		this.videoRenderer.endDraw();
	}
	
	private long lastAddedTime;
	private void blokEngineRender(long gameTime) {
		if (this.blokList.size() < maxBlok && gameTime - lastAddedTime > spawnTime) {
			Boolean isCockSpawn = false;
			float x = this.getRandomX();
			if (this.blokCock == null) {
				float randomCock = randomGenerator.nextFloat();
				if (randomCock > 0.66f && this.blokList.size() > blokCountPerWidth * 2) {
					this.blokCock = BlokFactory.createBCock(this.physicRenderer.getWorld(), renderRatio, x, this.level.getHeight() - 2);
					isCockSpawn = true;
				}
			}
			else
			{
				if (this.blokCock.getPos().y <= this.blokCock.getHeight()) {
					BlokFactory.deleteBlok(this.physicRenderer.getWorld(), blokCock);
					this.blokCock = null;
					this.scoreAction.addScore(100);
				}
				
				if (this.blokCock != null) {
					this.blokCock.pullChickens(blokList);
				}
			}
			
			if (!isCockSpawn) {
				// this.blokList.add(BlokFactory.createBChicken(this.physicRenderer.getWorld(), renderRatio, x, this.level.getHeight() - 2));
				this.blokList.addAll(BlokFactory.createBChickenRandomX(this.physicRenderer.getWorld(), renderRatio, BlokFactory.blockSize, this.level.getWidth() - BlokFactory.blockSize, this.level.getHeight() - (3 * BlokFactory.blockSize), this.level.getHeight() - (2 * BlokFactory.blockSize), 4));				
			}
			
			lastAddedTime = gameTime;
		}
	}
	
	private float getRandomX() {
		float random = randomGenerator.nextFloat();
		float x = this.level.getWidth() * random;
		if (x < 1) {
			x = 1;
		}
		if (x > this.level.getWidth() - 1) {
			x = this.level.getWidth() - 1;
		}
		
		return x;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#resize(int, int)
	 */
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Blok> getBloks() {
		return this.blokList;
	}
	
	public BlokSelecter	getSelecter() {
		return this.blokSelecter;
	}
	
	public PushAction getPushAction() {
		return this.pushAction;
	}
	
	public ScoreAction getScoreAction() {
		return this.scoreAction;
	}
}
