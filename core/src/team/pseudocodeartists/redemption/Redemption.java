package team.pseudocodeartists.redemption;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import team.pseudocodeartists.redemption.game.Maps;
import team.pseudocodeartists.redemption.game.WorldController;
import team.pseudocodeartists.redemption.game.WorldRenderer;

public class Redemption extends ApplicationAdapter {
	public static final String TAG = Redemption.class.getName();

	private WorldController worldController;
	private WorldRenderer worldRenderer;

	private boolean paused; // for pause or resume event on Android

	@Override
	public void create() {
		// Set Libgdx log level to DEBUG (LOG_NONE or LOG_INFO are more appropriate before publishing)
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Load maps
		Maps.instance.init();

		// Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		// Game world is active on start
		paused = false;
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void render() {
		// Do not update game world when paused
		if (!paused) {
			// Update game world by the time that has passed since last rendered frame
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		// Clear the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render game world to screen
		worldRenderer.render();
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		Maps.instance.init();
		paused = false;
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
		Maps.instance.dispose();
	}
}
