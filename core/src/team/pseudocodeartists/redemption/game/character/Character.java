package team.pseudocodeartists.redemption.game.character;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;

import java.io.Serializable;

public interface Character extends Serializable {

    TextureAtlas getTextureAtlas();

    BaseCharacter setTextureAtlas(TextureAtlas textureAtlas);

    SkeletonJson getSkeletonJson();

    BaseCharacter setSkeletonJson(SkeletonJson skeletonJson);

    SkeletonData getSkeletonData();

    BaseCharacter setSkeletonData(SkeletonData skeletonData);

    Skeleton getSkeleton();

    BaseCharacter setSkeleton(Skeleton skeleton);

    AnimationStateData getAnimationStateData();

    BaseCharacter setAnimationStateData(AnimationStateData animationStateData);

    AnimationState getAnimationState();

    BaseCharacter setAnimationState(AnimationState animationState);

    void loadAssets(String characterName);

    void update(float deltaTime);
}
