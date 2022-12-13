package Interfaces;

public interface IVivant {
    
    enum TypeVivant{
        Algue,
        Mérou, Thon, Poisson_clown,
        Sole, Bare, Carpe
    }

    int getMaxPdv();

    String getNom();
    Character getSexe();

    TypeVivant getType();

    int getPdv();
    void changerPdv(int degats);

    int getAge();
    void vieillir();

    int getAgeReproduction();
}
