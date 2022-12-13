package Classes;

import Interfaces.*;

import java.util.*;

public class Herbivore extends Poisson{

    TypeVivant type = null;
    List<TypeVivant> typesHerbi = new ArrayList<>(Arrays.asList(TypeVivant.Bare, TypeVivant.Sole, TypeVivant.Carpe));

    public Herbivore(String race, String nom, Character sexe, int pdv, int age, int pAttaque){ 

        super(nom, sexe, pdv, age, pAttaque);

        for (TypeVivant typep : typesHerbi) {
            if (typep.name().equalsIgnoreCase(race)) {
                this.type = typep;
                break;
            }
        }
    }

    @Override public TypeVivant getType(){ return this.type; }

    @Override public void manger(IVivant a) {
        
        if(this != a){
            
            if(a instanceof IAlgue){

                a.changerPdv(-this.pAttaque);
                this.pdv += 3;
            }
        }
    }

    @Override public IPoisson reproduire(IPoisson a){

        if(a.getSexe() != this.sexe){

            Random sexeAuHazard = new Random();
            int i = sexeAuHazard.nextInt(2);

            if(i == 0) {
                Herbivore poisson = new Herbivore(this.getType().name(), generateName(), 'M', ((this.getMaxPdv()+a.getMaxPdv())/2), 0, ((this.getPAttaque()+a.getPAttaque())/2));
                return poisson;
            }
            else if(i == 1) {
                Herbivore poisson = new Herbivore(this.getType().name(), generateName(), 'F', (this.getMaxPdv()+a.getMaxPdv()/2), 0, (this.getPAttaque()+a.getPAttaque()/2));
                return poisson;
            }
            else return null;
        }
        else return null;
    }
}