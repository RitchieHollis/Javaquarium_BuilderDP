package Classes;
import Interfaces.IAlgue;


public class Algue implements IAlgue {
    
    protected int pdv; protected int age;
    protected int maxPdv = 10;
    protected int ageReproduction = 4;

    public Algue(int pdv, int age){ this.pdv = pdv; this.age = age; }

    @Override public int getMaxPdv(){ return this.maxPdv; }

    @Override public TypeVivant getType(){ return TypeVivant.Algue; }
    @Override public String getNom(){ return null; }
    @Override public Character getSexe() { return null; }

    @Override public int getPdv(){ return this.pdv; }
    @Override public void changerPdv(int damage){ this.pdv += damage; }

    @Override public int getAge(){ return this.age; }
    @Override public void vieillir(){ this.age++; }

    @Override public IAlgue multiplier(){ 

        IAlgue al = new Algue(this.pdv/2, 0);
        this.pdv /= 2;
        return al;
    }

    @Override public int getAgeReproduction(){ return this.ageReproduction; }
}