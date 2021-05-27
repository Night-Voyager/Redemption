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
        private TextureAtlas playerTextureAtlas;
        private SkeletonJson playerSkeletonJson;
        private SkeletonData playerSkeletonData;
        private Skeleton playerSkeleton;
        private AnimationStateData playerAnimationStateData;
        private AnimationState playerAnimationState;

        public Player() {
            loadPlayerAssets();
        }

        void loadPlayerAssets() {
            playerTextureAtlas = new TextureAtlas(Gdx.files.internal(Constants.CHARACTERS + "player/Player.atlas"));
            playerSkeletonJson = new SkeletonJson(playerTextureAtlas);
            playerSkeletonData = playerSkeletonJson.readSkeletonData(Gdx.files.internal(Constants.CHARACTERS + "player/Player.json"));
            playerSkeleton = new Skeleton(playerSkeletonData);
            playerAnimationStateData = new AnimationStateData(playerSkeletonData);
            playerAnimationState = new AnimationState(playerAnimationStateData);
        }

        public void dispose() {
            playerTextureAtlas.dispose();
        }

        public AnimationState getPlayerAnimationState() {
            return playerAnimationState;
        }

        public Skeleton getPlayerSkeleton() {
            return playerSkeleton;
        }
    }
}
