package Patterns.TemplateMethod;


public class Strategy extends Game {
    @Override
    void initializeTheGame() {
        System.out.println("Launch Warcraft");
    }

    @Override
    void startPlay() {
        System.out.println("Choose hero");
    }

    @Override
    void endPlay() {
        System.out.println("You have been slain");

    }

}
