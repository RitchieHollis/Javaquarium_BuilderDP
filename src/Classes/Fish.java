package Classes;

import Interfaces.*;

import java.util.*;


abstract public class Fish implements IFish {
    
    protected int hp; protected int age; protected String name; protected char sexe;
    protected int maxHP = 10;
    protected int pAttack;
    protected int ageReproduction = 7;
    protected boolean busy = false;

    List<TypeLiving> typesHerbi = new ArrayList<>(Arrays.asList(TypeLiving.Bare, TypeLiving.Sole, TypeLiving.Carpe));
    List<TypeLiving> typesCarni = new ArrayList<>(Arrays.asList(TypeLiving.Thon, TypeLiving.Mérou, TypeLiving.Poisson_clown));

    public Fish(String name, char sexe, int hp, int age, int pAttack) {

        this.name = name;
        this.sexe = sexe;
        this.hp = hp;
        this.age = age;
        this.pAttack = pAttack;
    }

    @Override public int getMaxHP(){ return this.maxHP; }

    @Override public String getName(){ return this.name; }

    @Override public Character getSexe(){ return this.sexe; }

    abstract public TypeLiving getType(); //mettre en abstract après les types

    @Override public int getPAttack(){ return this.pAttack; }

    @Override public int getHP(){ return this.hp; }
    @Override public void changeHP(int damage){ this.hp += damage; }

    @Override public int getAge(){ return this.age; }
    @Override public void getOlder(){ this.age++; }

    abstract public void eat(ILivingBeing a);
    abstract public IFish reproduire(IFish a);

    public static String generateName(){

        List<String> fragmentsName = new ArrayList<>(Arrays.asList("ca","da","mo","di","du","ma","ca","no"));
        Random rand = new Random();
        String name = "";
        int b = rand.nextInt(5);
        if(b == 0) b++;

        for(int i = 0; i < b; i++){
            if(i == 0){
                name += fragmentsName.get(rand.nextInt(fragmentsName.size()));
                name = name.substring(0, 1).toUpperCase() + name.substring(1);;
            }
            else
                name += fragmentsName.get(rand.nextInt(fragmentsName.size()));
        }
        return name;
    }

    @Override public int getAgeReproduction(){ return this.ageReproduction; }

    @Override public boolean isBusy() { return this.busy; }

    @Override public void setBusy(boolean a) { this.busy = a;}

}
