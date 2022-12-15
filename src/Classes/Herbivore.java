package Classes;

import Interfaces.*;

import java.util.*;

public class Herbivore extends Fish{

    TypeLiving type = null;
    List<TypeLiving> typesHerbi = new ArrayList<>(Arrays.asList(TypeLiving.Bare, TypeLiving.Sole, TypeLiving.Carpe));

    public Herbivore(String race, String name, Character sexe, int hp, int age, int pAttack){ 

        super(name, sexe, hp, age, pAttack);

        for (TypeLiving typep : typesHerbi) {
            if (typep.name().equalsIgnoreCase(race)) {
                this.type = typep;
                break;
            }
        }
    }

    @Override public TypeLiving getType(){ return this.type; }

    @Override public void eat(ILivingBeing a) {
        
        if(this != a){
            
            if(a instanceof IAlgae){

                a.changeHP(-this.pAttack);
                this.hp += 3;
            }
        }
    }

    @Override public IFish reproduire(IFish a){

        if(a.getSexe() != this.sexe){

            Random randomSexe = new Random();
            int i = randomSexe.nextInt(2);

            if(i == 0) {
                Herbivore fish = new Herbivore(this.getType().name(), generateName(), 'M', ((this.getMaxHP()+a.getMaxHP())/2), 0, ((this.getPAttack()+a.getPAttack())/2));
                return fish;
            }
            else if(i == 1) {
                Herbivore fish = new Herbivore(this.getType().name(), generateName(), 'F', (this.getMaxHP()+a.getMaxHP()/2), 0, (this.getPAttack()+a.getPAttack()/2));
                return fish;
            }
            else return null;
        }
        else return null;
    }
}