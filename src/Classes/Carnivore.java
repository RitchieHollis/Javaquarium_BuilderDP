package Classes;

import Interfaces.*;

import java.util.*;


public class Carnivore extends Poisson {

    TypeVivant type = null;
    List<TypeVivant> typesCarni = new ArrayList<>(Arrays.asList(TypeVivant.Thon, TypeVivant.Mérou, TypeVivant.Poisson_clown));

    public Carnivore(String race, String nom, Character sexe, int pdv, int age, int pAttaque){ 

        super(nom, sexe, pdv, age, pAttaque);

        for (TypeVivant typep : typesCarni) {
            if (typep.name().equalsIgnoreCase(race)) {
                this.type = typep;
                break;
            }
        }
    }

    @Override public TypeVivant getType(){ return this.type; }

    @Override public void manger(IVivant a) {
        
        if(this != a){
            
            if(a instanceof IPoisson){

                IPoisson poiss = (IPoisson)a;
                Random rand = new Random();

                if(rand.nextInt(100)<= 60){ //probabilite de manger un autre;
                    System.out.print("Oui\n");
                    poiss.changerPdv(-this.pAttaque);
                    this.pdv += 5;
                }
                else{
                    System.out.print("Non\n");
                    this.pdv -= 4;
                }
            }
        }
    }
    @Override public IPoisson reproduire(IPoisson a){

        if(a.getSexe() != this.sexe){

            Random sexeAuHazard = new Random();
            int i = sexeAuHazard.nextInt(2);

            if(i == 0) {
                Carnivore poisson = new Carnivore(this.getType().name(), generateName(), 'M', (10), 0, (this.getPAttaque()+a.getPAttaque()/2));
                return poisson;
            }
            else if(i == 1) {
                Carnivore poisson = new Carnivore(this.getType().name(), generateName(), 'F', (10), 0, (this.getPAttaque()+a.getPAttaque()/2));
                return poisson;
            }
            else return null;
        }
        else return null;
    }
}