package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import team.pseudocodeartists.redemption.util.Constants;

public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    public Maps maps;

    // singleton: prevent instantiation from other classes
    private Assets() {}

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        // set asset manager error handler
        assetManager.setErrorListener(this);

        maps = new Maps();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.debug(TAG, "Couldn't load asset '" + asset.fileName + "'", throwable);
    }

    public class Maps {
        public ChooseGate chooseGate;

        public Maps() {
            init();
        }

        public void init() {
            assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
            chooseGate = new ChooseGate(assetManager);
        }

        public class ChooseGate {
            public final TiledMap tiledMap;

            public ChooseGate(AssetManager assetManager) {
                assetManager.load(Constants.MAPS + "ChooseGate/ChooseGate.tmx", TiledMap.class);
                assetManager.finishLoading();
                tiledMap = assetManager.get("ChooseGate.tmx");
            }
        }
    }
}
