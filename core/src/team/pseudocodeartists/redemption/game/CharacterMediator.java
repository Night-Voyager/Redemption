package team.pseudocodeartists.redemption.game;

import com.badlogic.gdx.utils.Disposable;

public class CharacterMediator implements Disposable {
    public static final String TAG = CharacterMediator.class.getName();

    public static final CharacterMediator instance = new CharacterMediator();

    public Player player;

    public CharacterMediator() {
        init();
    }

    public void init() {
        player = new Player();
    }

    @Override
    public void dispose() {
        player.dispose();
    }
}
