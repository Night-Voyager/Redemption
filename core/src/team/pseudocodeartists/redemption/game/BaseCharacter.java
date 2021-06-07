package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.spine.*;

public abstract class BaseCharacter implements Character, Disposable {
    TextureAtlas textureAtlas;
    SkeletonJson skeletonJson;
    SkeletonData skeletonData;
    Skeleton skeleton;
    AnimationStateData animationStateData;
    AnimationState animationState;

    @Override
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    @Override
    public BaseCharacter setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
        return this;
    }

    @Override
    public SkeletonJson getSkeletonJson() {
        return skeletonJson;
    }

    @Override
    public BaseCharacter setSkeletonJson(SkeletonJson skeletonJson) {
        this.skeletonJson = skeletonJson;
        return this;
    }

    @Override
    public SkeletonData getSkeletonData() {
        return skeletonData;
    }

    @Override
    public BaseCharacter setSkeletonData(SkeletonData skeletonData) {
        this.skeletonData = skeletonData;
        return this;
    }

    @Override
    public Skeleton getSkeleton() {
        return skeleton;
    }

    @Override
    public BaseCharacter setSkeleton(Skeleton skeleton) {
        this.skeleton = skeleton;
        return this;
    }

    @Override
    public AnimationStateData getAnimationStateData() {
        return animationStateData;
    }

    @Override
    public BaseCharacter setAnimationStateData(AnimationStateData animationStateData) {
        this.animationStateData = animationStateData;
        return this;
    }

    @Override
    public AnimationState getAnimationState() {
        return animationState;
    }

    @Override
    public BaseCharacter setAnimationState(AnimationState animationState) {
        this.animationState = animationState;
        return this;
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
    }
}
