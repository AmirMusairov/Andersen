package Patterns.Facade;

public class FacadeImpl {
    public static void main(String[] args) {
        WeaponMaker weaponMaker = new WeaponMaker();
        weaponMaker.makeSword();
        weaponMaker.makeRapier();
        weaponMaker.makePistol();
    }
}
