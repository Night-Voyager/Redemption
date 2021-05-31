package team.pseudocodeartists.redemption.game.character;

public class Boss extends BaseCharacter{
    public static final String TAG = Boss.class.getName();

    public Boss(String bossName) {
        loadAssets(bossName);
    }
}
