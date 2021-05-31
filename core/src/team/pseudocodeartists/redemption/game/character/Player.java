package team.pseudocodeartists.redemption.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonJson;
import team.pseudocodeartists.redemption.util.Constants;

public class Player extends BaseCharacter{
    public static final String TAG = Player.class.getName();

    public Player() {
        loadPlayerAssets();
    }

    public void loadPlayerAssets() {
        textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.CHARACTERS + "player/Player.atlas"));
        skeletonJson = new SkeletonJson(textureAtlas);
        skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(Constants.CHARACTERS + "player/Player.json"));
        skeleton = new Skeleton(skeletonData);
        animationStateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(animationStateData);
    }
}
