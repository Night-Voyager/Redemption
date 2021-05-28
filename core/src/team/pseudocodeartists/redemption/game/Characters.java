package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.*;
import team.pseudocodeartists.redemption.util.Constants;

public class Characters implements Disposable {
    public static final String TAG = Characters.class.getName();

    public static final Characters instance = new Characters();

    public Player player;

    public Characters() {
        init();
    }

    public void init() {
        player = new Player();
    }

    @Override
    public void dispose() {
        player.dispose();
    }

    public class Player {
        private TextureAtlas textureAtlas;
        private SkeletonJson skeletonJson;
        private SkeletonData skeletonData;
        private Skeleton skeleton;
        private AnimationStateData animationStateData;
        private AnimationState animationState;

        public Player() {
            loadPlayerAssets();
        }

        void loadPlayerAssets() {
            textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.CHARACTERS + "player/Player.atlas"));
            skeletonJson = new SkeletonJson(textureAtlas);
            skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(Constants.CHARACTERS + "player/Player.json"));
            skeleton = new Skeleton(skeletonData);
            animationStateData = new AnimationStateData(skeletonData);
            animationState = new AnimationState(animationStateData);
        }

        public void dispose() {
            textureAtlas.dispose();
        }

        public AnimationState getAnimationState() {
            return animationState;
        }

        public Skeleton getSkeleton() {
            return skeleton;
        }
    }
}
