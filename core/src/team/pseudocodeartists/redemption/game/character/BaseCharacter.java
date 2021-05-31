package team.pseudocodeartists.redemption.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import team.pseudocodeartists.redemption.util.Constants;

public abstract class BaseCharacter implements Character{
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
    public void loadAssets(String characterName) {
        String path = characterName + '/' + characterName;
        textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.CHARACTERS + path + ".atlas"));
        skeletonJson = new SkeletonJson(textureAtlas);
        skeletonData = skeletonJson.readSkeletonData(Gdx.files.internal(Constants.CHARACTERS + path + ".json"));
        skeleton = new Skeleton(skeletonData);
        animationStateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(animationStateData);
    }

    @Override
    public void update(float deltaTime) {
        this.animationState.update(deltaTime);
        this.animationState.apply(this.skeleton);
        this.skeleton.updateWorldTransform();
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
    }
}
