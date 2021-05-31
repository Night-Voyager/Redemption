package team.pseudocodeartists.redemption.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import team.pseudocodeartists.redemption.util.Constants;

public class Maps implements Disposable {
    public static final String TAG = Maps.class.getName();

    public static final Maps instance = new Maps();

    private TmxMapLoader tmxMapLoader;

    public ChooseGate chooseGate;

    public Maps() {
        init();
    }

    public void init() {
        tmxMapLoader = new TmxMapLoader();
        chooseGate = new ChooseGate(tmxMapLoader);
    }

    @Override
    public void dispose() {
        chooseGate.dispose();
    }

    public class ChooseGate {
        public final TiledMap tiledMap;

        public ChooseGate(TmxMapLoader tmxMapLoader) {
            tiledMap = tmxMapLoader.load(Constants.MAPS + "ChooseGate/ChooseGate.tmx");
        }

        public void dispose() {
            tiledMap.dispose();
        }
    }
}
