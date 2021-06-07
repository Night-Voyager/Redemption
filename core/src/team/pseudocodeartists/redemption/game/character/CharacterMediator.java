package team.pseudocodeartists.redemption.game.character;

import com.badlogic.gdx.utils.Disposable;
import team.pseudocodeartists.redemption.util.Memento;
import team.pseudocodeartists.redemption.util.MementoCareTaker;
import team.pseudocodeartists.redemption.util.MementoOriginator;

import java.io.*;
import java.util.Base64;

public class CharacterMediator implements Disposable, MementoOriginator {
    public static final String TAG = CharacterMediator.class.getName();

    public static final CharacterMediator instance = new CharacterMediator();

    private MementoCareTaker history;

    public Player player;
    public Boss boss;

    public CharacterMediator() {
        init();
    }

    public void init() {
        history = new MementoCareTaker();
        player = new Player();
        boss = new Boss("octopus");
        boss.getAnimationState().setAnimation(0, "State(L)", true);
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
        if (boss!=null)
            boss.update(deltaTime);
    }

    @Override
    public void dispose() {
        player.dispose();
        boss.dispose();
    }

    @Override
    public Memento backup() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this.player);
            objectOutputStream.close();
            System.out.println(objectOutputStream);
            return new Memento(Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            System.out.println("IOException occurred in CharacterMediator while backing up.");
            return new Memento("");
        }
    }

    @Override
    public void restore(Memento memento) {
        try {
            byte [] data = Base64.getDecoder().decode(memento.getState());
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            player = (Player) objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("IOException occurred in CharacterMediator while restoring.");
            init();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred in CharacterMediator while restoring.");
            init();
        }
    }
}
