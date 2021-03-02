package Patterns.Facade;

public class WeaponMaker {
    private Weapon sword;
    private Weapon rapier;
    private Weapon pistol;

    public WeaponMaker() {
        sword = new Sword();
        rapier = new Rapier();
        pistol = new Pistol();
    }

    public void makeSword() {
        sword.type();
    }

    public void makeRapier() {
        rapier.type();
    }

    public void makePistol() {
        pistol.type();
    }
}
