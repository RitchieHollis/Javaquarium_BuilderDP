package Classes;
import Interfaces.IAlgae;


public class Algae implements IAlgae {
    
    protected int hp; protected int age;
    protected int maxHP = 10;
    protected int ageReproduction = 4;

    public Algae(int hp, int age){ this.hp = hp; this.age = age; }

    @Override public int getMaxHP(){ return this.maxHP; }

    @Override public TypeLiving getType(){ return TypeLiving.Algue; }
    @Override public String getName(){ return null; }
    @Override public Character getSexe() { return null; }

    @Override public int getHP(){ return this.hp; }
    @Override public void changeHP(int damage){ this.hp += damage; }

    @Override public int getAge(){ return this.age; }
    @Override public void getOlder(){ this.age++; }

    @Override public IAlgae multiply(){ 

        IAlgae al = new Algae(this.hp/2, 0);
        this.hp /= 2;
        return al;
    }

    @Override public int getAgeReproduction(){ return this.ageReproduction; }
}