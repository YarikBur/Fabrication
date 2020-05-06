package ru.asfick.fabrication;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.asfick.fabrication.game.Statistics;

public class Main extends Game {

	private SpriteBatch batch;
	public static float WIDTH_BOX_2D;
	public static float HEIGHT_BOX_2D;
	public final boolean DEBUG = true;
    public static final Statistics ENERGY = new Statistics();
    public static final Statistics MONEY = new Statistics();

	private final ru.asfick.fabrication.game.Game GAME = new ru.asfick.fabrication.game.Game(this);

	@Override
	public void create () {
		WIDTH_BOX_2D = 32f;
		HEIGHT_BOX_2D = WIDTH_BOX_2D * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

		batch = new SpriteBatch();

		GAME.setBatch(batch);

		setScreen(GAME);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
