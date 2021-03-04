package Patterns.TemplateMethod;

public class Shooter extends Game {
    @Override
    void initializeTheGame() {
        System.out.println("Launch Call of Duty");
    }

    @Override
    void startPlay() {
        System.out.println("Shoot the enemy");
    }

    @Override
    void endPlay() {
        System.out.println("You died");

    }

}
