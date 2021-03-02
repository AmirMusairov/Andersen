package Patterns.TemplateMethod;

public class TemplateMethodImpl {
    public static void main(String[] args) {
        Game game = new Shooter();
        game.play();
        game = new Rpg();
        game.play();
        game = new Strategy();
        game.play();

    }
}
