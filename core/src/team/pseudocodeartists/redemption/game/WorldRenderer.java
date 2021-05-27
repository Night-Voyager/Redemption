package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.SkeletonRenderer;
import team.pseudocodeartists.redemption.util.Constants;

public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;

    private OrthoCachedTiledMapRenderer mapRenderer;

    private SkeletonRenderer skeletonRenderer;
    private PolygonSpriteBatch polygonBatch;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();

        mapRenderer = new OrthoCachedTiledMapRenderer(Maps.instance.chooseGate.tiledMap);

        skeletonRenderer = new SkeletonRenderer();
        polygonBatch = new PolygonSpriteBatch();
    }

    public void render() {
        worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        polygonBatch.setProjectionMatrix(camera.combined);

        // The following codes have an impact on the camera, the reason needs to be found out
        float x = camera.position.x - camera.viewportWidth * camera.zoom;
        float y = camera.position.y - camera.viewportHeight * camera.zoom;
        float width = camera.viewportWidth * camera.zoom * 2;
        float height = camera.viewportHeight * camera.zoom * 2;
        mapRenderer.setView(camera.combined, x, y, width, height);
        mapRenderer.render();

        polygonBatch.begin();
        skeletonRenderer.draw(polygonBatch, Characters.instance.player.getPlayerSkeleton());
        polygonBatch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        mapRenderer.dispose();
    }
}
