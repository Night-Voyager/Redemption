package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {
    public static final String TAG = WorldController.class.getName();

    public WorldController() {
        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(this);
    }

    public void update(float deltaTime) {
        handleDesktopInput(deltaTime);
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println(Keys.toString(keycode));
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println(Keys.toString(keycode));
        return false;
    }

    private void handleDesktopInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;

        if (Gdx.input.isKeyPressed(Keys.A))
            System.out.println('A');
    }
}
