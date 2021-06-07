package team.pseudocodeartists.redemption.util;

import java.util.Stack;

public class MementoCareTaker {
    private Stack<Memento> mementoStack = new Stack<>();

    public void pushMemento(Memento memento) {
        mementoStack.push(memento);
    }

    public Memento popMemento() {
        return mementoStack.pop();
    }
}
