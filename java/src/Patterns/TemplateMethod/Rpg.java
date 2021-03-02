package Patterns.TemplateMethod;

public class Rpg extends Game {
    @Override
    void initializeTheGame() {
        System.out.println("Launch GTA");
    }

    @Override
    void startPlay() {
        System.out.println("Mission started");
    }

    @Override
    void endPlay() {
        System.out.println("Wasted");
    }

}
