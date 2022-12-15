package Classes;

import Interfaces.*;

import java.util.*;


public class Carnivore extends Fish {

    TypeLiving type = null;
    List<TypeLiving> typesCarni = new ArrayList<>(Arrays.asList(TypeLiving.Thon, TypeLiving.Mérou, TypeLiving.Poisson_clown));

    public Carnivore(String race, String name, Character sexe, int hp, int age, int pAttack){ 

        super(name, sexe, hp, age, pAttack);

        for (TypeLiving typep : typesCarni) {
            if (typep.name().equalsIgnoreCase(race)) {
                this.type = typep;
                break;
            }
        }
    }

    @Override public TypeLiving getType(){ return this.type; }

    @Override public void eat(ILivingBeing a) {
        
        if(this != a){
            
            if(a instanceof IFish){

                IFish poiss = (IFish)a;
                Random rand = new Random();

                if(rand.nextInt(100)<= 60){ //probabilite de manger un autre;
                    System.out.print("Yes\n");
                    poiss.changeHP(-this.pAttack);
                    this.hp += 5;
                }
                else{
                    System.out.print("Non\n");
                    this.hp -= 4;
                }
            }
        }
    }
    @Override public IFish reproduire(IFish a){

        if(a.getSexe() != this.sexe){

            Random randomSex = new Random();
            int i = randomSex.nextInt(2);

            if(i == 0) {
                Carnivore fish = new Carnivore(this.getType().name(), generateName(), 'M', (10), 0, (this.getPAttack()+a.getPAttack()/2));
                return fish;
            }
            else if(i == 1) {
                Carnivore fish = new Carnivore(this.getType().name(), generateName(), 'F', (10), 0, (this.getPAttack()+a.getPAttack()/2));
                return fish;
            }
            else return null;
        }
        else return null;
    }
}