package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import team.pseudocodeartists.redemption.game.character.CharacterMediator;
import team.pseudocodeartists.redemption.util.CameraHelper;

public class WorldController extends InputAdapter {
    public static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;

    public WorldController() {
        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
    }

    public void update(float deltaTime) {
        handleDesktopInput(deltaTime);
        cameraHelper.update(deltaTime);

        CharacterMediator.instance.player.getAnimationState().update(deltaTime);
        CharacterMediator.instance.player.getAnimationState().apply(CharacterMediator.instance.player.getSkeleton());
        CharacterMediator.instance.player.getSkeleton().updateWorldTransform();
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.D)
            CharacterMediator.instance.player.getAnimationState().setAnimation(0, "Walk(R)", false);

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        // Player Controls (animation)
        if (keycode == Keys.SPACE)
            CharacterMediator.instance.player.getAnimationState().setAnimation(0, "Jump(R)", false);
        if (keycode == Keys.D)
            CharacterMediator.instance.player.getAnimationState().setAnimation(0, "Walk(R)", true);
        if (keycode == Keys.C)
            CharacterMediator.instance.player.getAnimationState().setAnimation(0, "Chop(R)", false);
        if (keycode == Keys.V)
            CharacterMediator.instance.player.getAnimationState().setAnimation(0, "Stab(R)", false);
        if (keycode == Keys.B)
            CharacterMediator.instance.player.getAnimationState().setAnimation(0, "Stab(L)", false);

        return false;
    }

    private void handleDesktopInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;

        // Player Controls (move)
        if (Gdx.input.isKeyPressed(Keys.W))
            CharacterMediator.instance.player.getSkeleton().setY(
                    CharacterMediator.instance.player.getSkeleton().getY() + 10
            );
        if (Gdx.input.isKeyPressed(Keys.A))
            CharacterMediator.instance.player.getSkeleton().setX(
                    CharacterMediator.instance.player.getSkeleton().getX() - 10
            );
        if (Gdx.input.isKeyPressed(Keys.S))
            CharacterMediator.instance.player.getSkeleton().setY(
                    CharacterMediator.instance.player.getSkeleton().getY() - 10
            );
        if (Gdx.input.isKeyPressed(Keys.D))
            CharacterMediator.instance.player.getSkeleton().setX(
                    CharacterMediator.instance.player.getSkeleton().getX() + 10
            );

        // Camera Controls (move)
        float cameraMoveSpeed = 5 * deltaTime;
        float cameraMoveSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            cameraMoveSpeed *= cameraMoveSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveCamera(-cameraMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveCamera(cameraMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Keys.UP))
            moveCamera(0, cameraMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveCamera(0, -cameraMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
            cameraHelper.setPosition(0, 0);

        // Camera Controls (zoom)
        float cameraZoomSpeed = 1 * deltaTime;
        float cameraZoomSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            cameraZoomSpeed *= cameraZoomSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Keys.COMMA))
            cameraHelper.addZoom(cameraZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.PERIOD))
            cameraHelper.addZoom(-cameraZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.SLASH))
            cameraHelper.setZoom(200);
    }

    private void moveCamera(float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;
        cameraHelper.setPosition(x, y);
    }
}
