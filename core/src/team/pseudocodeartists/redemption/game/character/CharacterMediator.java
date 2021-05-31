package team.pseudocodeartists.redemption.game.character;

import com.badlogic.gdx.utils.Disposable;

public class CharacterMediator implements Disposable {
    public static final String TAG = CharacterMediator.class.getName();

    public static final CharacterMediator instance = new CharacterMediator();

    public Player player;
    public Boss boss;

    public CharacterMediator() {
        init();
    }

    public void init() {
        player = new Player();
        boss = new Boss("octopus");
        boss.getAnimationState().setAnimation(0, "State(L)", true);
    }

    @Override
    public void dispose() {
        player.dispose();
        boss.dispose();
    }
}
