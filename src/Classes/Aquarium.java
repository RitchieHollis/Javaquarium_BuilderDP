package Classes;

import java.util.*;

import Interfaces.*;
import Interfaces.ILivingBeing.TypeLiving;
import java.security.SecureRandom;

public class Aquarium {
    
    static List<ILivingBeing> livingBeings = new ArrayList<>();

    private static int nbFish; private static int nbAlgae; private int nbDays;
    private Aquarium(Builder builder){ //this.nbrePoissons = 0; this.nbreAlgues = 0; this.nbreJours = 0;

    }
    private static void addBeing(ILivingBeing a){ 

        livingBeings.add((ILivingBeing)a);
        if(a instanceof Algae)
            nbAlgae++;
        else if(a instanceof Fish)
            nbFish++; 
    }
    private void showList(){ 

        System.out.println("Type: \tName: \tSexe: \tAge: \tHP: \n");

        String name; char sexe;
        for(int i = 0; i < livingBeings.size(); i++){

                if(livingBeings.get(i).getName() == null) name = ""; else name = livingBeings.get(i).getName();
                if(livingBeings.get(i).getSexe() == null) sexe = '\0'; else sexe = livingBeings.get(i).getSexe();
                System.out.println(livingBeings.get(i).getType()+"\t"+
                                   name+"\t"+
                                   sexe+"\t"+
                                   livingBeings.get(i).getAge()+"\t"+
                                   livingBeings.get(i).getHP());
        }
        System.out.println("\nQuantity of algaes: "+nbAlgae);
        System.out.println("Quantity of fishes: "+nbFish);
    }
    private void deleteDead(ILivingBeing a){

        if(a instanceof IAlgae)
            nbAlgae--;
        else if(a instanceof IFish)
            nbFish--;
        livingBeings.remove(a);
    }
    public boolean passingDay(){

        if(livingBeings.size() != 0){
            System.out.println("--------------------------------------------------");
            System.out.println("Aquarium - Day "+(++nbDays)+"\n\n");
            actions();
            showList();
            System.out.println("--------------------------------------------------");
        }
        if(livingBeings.size() == 0)
            return false;
        else
            return true;
    }
    private void actions(){

        List<ILivingBeing> tempList = new ArrayList<>();
        int probabilityAlgaeMulti = 15;
        SecureRandom rand = new SecureRandom();
        int temp = rand.nextInt(99);
        for(int i = 0; i < livingBeings.size(); i++){

            if(livingBeings.get(i) instanceof IAlgae && livingBeings.get(i) != null){

                IAlgae al = (IAlgae)livingBeings.get(i);
                al.changeHP(2);
                al.getOlder();
                if(al.getAge() > 20){
                    deleteDead(al);
                    System.out.println("- One algae passed away");
                    continue;
                }
                
                if(rand.nextInt(100) <= probabilityAlgaeMulti && al.getAge() >= al.getAgeReproduction()){

                    IAlgae a =  al.multiply(); //new Algue(rand.nextInt(this.listeVivant.get(i).getMaxPdv()), 0);
                    tempList.add(a);
                    System.out.println("- New algae was created");
                }
            }

            if(livingBeings.get(i) instanceof IFish && livingBeings.get(i) != null){

                IFish fish = (IFish)livingBeings.get(i);
                fish.changeHP(-1);
                fish.getOlder();
                if(fish.getHP() <= 0 || fish.getAge() > 20) {
                    deleteDead(fish);
                    System.out.println("- "+fish.getName()+" was found dead");
                    continue;
                }
                if(fish.getHP() <= 5){
                    if(fish instanceof Herbivore){ //eating for herbivore

                        Random algaeToFind = new Random();
                        boolean eatAlgue = false;

                        for(ILivingBeing check : livingBeings){
                            if(check instanceof IAlgae)
                                eatAlgue = true;
                        }
                        if(eatAlgue){
                            int a;
                            
                            do{
                                a = algaeToFind.nextInt(livingBeings.size());
                            } while(!(livingBeings.get(a) instanceof IAlgae));

                            IAlgae algae = (IAlgae)livingBeings.get(a);
                            if(fish.getHP() + 3 <= fish.getMaxHP())
                                fish.eat(algae);
                                fish.setBusy(true);
                                System.out.println("- "+fish.getName()+" eated a piece of algae");
                                if(algae.getHP() <= 0){
                                    deleteDead(algae);
                                    System.out.println("- One algue was eated completely");
                                }
                        }
                    }
                    if(fish instanceof Carnivore){ //eating of carnivore

                        Random poissonToFind = new Random();
                        boolean eatFish = false;

                        for(ILivingBeing check : livingBeings){
                            if(check instanceof IFish && TypeLiving.valueOf(fish.getType().name()) != TypeLiving.valueOf(check.getType().name()))
                                eatFish = true;
                        }
                        if(eatFish){

                            int a;
                            do{
                                a = poissonToFind.nextInt(livingBeings.size());
                            } while(!(livingBeings.get(a) instanceof IFish) || TypeLiving.valueOf(fish.getType().name()) == TypeLiving.valueOf(livingBeings.get(a).getType().name()));

                            IFish fishToAttack = (IFish)livingBeings.get(a);

                            System.out.print("- "+fish.getName()+" attacked "+fishToAttack.getName()+". With success? ");
                            if(fish.getHP() + 5 <= fish.getMaxHP()){
                                fish.eat(fishToAttack);
                                fish.setBusy(true);
                                if(fishToAttack.getHP()-fish.getPAttack() <= 0){
                                    deleteDead(fishToAttack);
                                    System.out.println("- "+fishToAttack.getName()+" was killed");
                                }
                            }
                        }
                    }
                }
                else if(fish.getAge() >= fish.getAgeReproduction() && temp < 49){

                    Random poissonToFind = new Random();
                    boolean reproduction = false;
                    for(ILivingBeing check : livingBeings){
                        if(check instanceof IFish && fish.getType().name().equals(check.getType().name()) 
                           && !(fish.getSexe().equals(check.getSexe())) && !tempList.contains(check) && 
                           livingBeings.indexOf(check) > i && check.getAge() >= check.getAgeReproduction() && (((IFish)check).isBusy()) == false){ reproduction = true;}
                    }
                    if(reproduction){
                        int a;
                        do{ 
                            a = poissonToFind.nextInt(livingBeings.size());
                        } while(!(livingBeings.get(a) instanceof IFish) || 
                                  !(fish.getType().name().equals(livingBeings.get(a).getType().name())) ||
                                  fish.getSexe().equals(livingBeings.get(a).getSexe()) ||
                                  tempList.contains(livingBeings.get(a)) || 
                                  livingBeings.get(a).getAge() < livingBeings.get(a).getAgeReproduction() ||
                                  ((IFish)livingBeings.get(a)).isBusy() == true);
                        IFish partenaire = (IFish)livingBeings.get(a);

                        IFish newPoisson = fish.reproduire(partenaire);
                        tempList.add(partenaire);
                        tempList.add(fish);
                        livingBeings.remove(partenaire);
                        if(i == livingBeings.size()-1){
                            continue;
                        }
                        else{
                            livingBeings.add(i+1,partenaire);
                            i += 2;
                        }
                        if(newPoisson != null){
                            System.out.println("- "+newPoisson.getType()+" "+newPoisson.getName()+" was born");
                            tempList.add(newPoisson);
                        }
                    }
                    else continue;
                }
            }
            else continue;           
        }
        for(int i = 0; i < tempList.size(); i++){
            if(!(livingBeings.contains(tempList.get(i))))
                addBeing(tempList.get(i));
        }
        Collections.shuffle(livingBeings);
        System.out.println();
    }
    public static class Builder { //builder pour aquarium
        
        public Builder(int nbrealgues, int nbreCarni, int nbreHerbi){
            nbreAlgues(nbrealgues); nbreDePoissonCarni(nbreCarni); nbreDePoissonHerbi(nbreHerbi);
        }
        public Builder nbreDePoissonCarni(int nbre){

            List<IFish> liste = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < nbre; i++){

                TypeLiving a;
                do{
                    a = TypeLiving.values()[random.nextInt(TypeLiving.values().length)];
                }
                while(a.equals(TypeLiving.Algue) || a.equals(TypeLiving.Bare) || a.equals(TypeLiving.Carpe) || a.equals(TypeLiving.Sole));

                if(a.equals(TypeLiving.Mérou) || a.equals(TypeLiving.Poisson_clown) || a.equals(TypeLiving.Thon)){
                    int sexe = random.nextInt(2);
                    Character genre = null;
                    if(sexe == 0) genre = 'M'; 
                    else if(sexe == 1) genre = 'F';
                    Carnivore car = new Carnivore(a.toString(), Fish.generateName(), genre, 10, random.nextInt(20), random.nextInt(6));
                    liste.add(car);
                }
            }
            for(ILivingBeing i : liste) addBeing(i);
            return this;
        }
        public Builder nbreDePoissonHerbi(int nbre){

            List<IFish> liste = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < nbre; i++){

                TypeLiving a;
                do{
                    a = TypeLiving.values()[random.nextInt(TypeLiving.values().length)];
                }
                while(a.equals(TypeLiving.Algue) || a.equals(TypeLiving.Mérou) || a.equals(TypeLiving.Poisson_clown) || a.equals(TypeLiving.Thon));

                if(a.equals(TypeLiving.Bare) || a.equals(TypeLiving.Carpe) || a.equals(TypeLiving.Sole)){
                    int sexe = random.nextInt(2);
                    Character genre = null;
                    if(sexe == 0) genre = 'M'; 
                    else if(sexe == 1) genre = 'F';
                    Herbivore car = new Herbivore(a.toString(), Fish.generateName(), genre, 10, random.nextInt(20), random.nextInt(6));
                    liste.add(car);
                }
            }
            for(ILivingBeing i : liste) addBeing(i);
            return this;
        }
        public Builder nbreAlgues(int nbre){

            List<IAlgae> liste = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < nbre; i++){

                TypeLiving a;
                do{
                    a = TypeLiving.values()[random.nextInt(TypeLiving.values().length)];
                }
                while(!(a.equals(TypeLiving.Algue)));

                if(a.equals(TypeLiving.Algue)){
                    Algae car = new Algae(random.nextInt(10), random.nextInt(20));
                    liste.add(car);
                }
            }
            for(ILivingBeing i : liste) addBeing(i);
            return this;
        }
        public Aquarium build(){
            return new Aquarium(this);
        }
    }
}
