package Interfaces;

public interface ILivingBeing {
    
    enum TypeLiving{
        Algue,
        Mérou, Thon, Poisson_clown,
        Sole, Bare, Carpe
    }

    int getMaxHP();

    String getName();
    Character getSexe();

    TypeLiving getType();

    int getHP();
    void changeHP(int attack);

    int getAge();
    void getOlder();

    int getAgeReproduction();
}
