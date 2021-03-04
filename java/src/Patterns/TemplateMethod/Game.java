package Patterns.TemplateMethod;

public abstract class Game {
    abstract void initializeTheGame();
    abstract void startPlay();
    abstract void endPlay();

    public final void play() {
        initializeTheGame();
        startPlay();
        endPlay();
    }

}
