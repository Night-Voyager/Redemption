package team.pseudocodeartists.redemption.util;

public interface MementoOriginator {
    Memento backup();

    void restore(Memento memento);
}
