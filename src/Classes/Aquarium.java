package Classes;

import java.util.*;

import Interfaces.*;
import Interfaces.IVivant.TypeVivant;
import java.security.SecureRandom;

public class Aquarium {
    
    static List<IVivant> listeVivant = new ArrayList<>();

    private static int nbrePoissons; private static int nbreAlgues; private int nbreJours;
    private Aquarium(Builder builder){ //this.nbrePoissons = 0; this.nbreAlgues = 0; this.nbreJours = 0;

    }
    private static void ajouterVivant(IVivant a){ 

        listeVivant.add((IVivant)a);
        if(a instanceof Algue)
            nbreAlgues++;
        else if(a instanceof Poisson)
            nbrePoissons++; 
    }
    private void montrerListe(){ 

        System.out.println("Type: \tNom: \tSexe: \tAge: \tPdv: \n");

        String nom; char sexe;
        for(int i = 0; i < listeVivant.size(); i++){

                if(listeVivant.get(i).getNom() == null) nom = ""; else nom = listeVivant.get(i).getNom();
                if(listeVivant.get(i).getSexe() == null) sexe = '\0'; else sexe = listeVivant.get(i).getSexe();
                System.out.println(listeVivant.get(i).getType()+"\t"+
                                   nom+"\t"+
                                   sexe+"\t"+
                                   listeVivant.get(i).getAge()+"\t"+
                                   listeVivant.get(i).getPdv());
        }
        System.out.println("\nNombre d'algues: "+nbreAlgues);
        System.out.println("Nombre de poissons: "+nbrePoissons);
    }
    private void supprimerMeurt(IVivant a){

        if(a instanceof IAlgue)
            nbreAlgues--;
        else if(a instanceof IPoisson)
            nbrePoissons--;
        listeVivant.remove(a);
    }
    public boolean passerUnJour(){

        if(listeVivant.size() != 0){
            System.out.println("--------------------------------------------------");
            System.out.println("Aquarium - Jour "+(++nbreJours)+"\n\n");
            actions();
            montrerListe();
            /* 
            System.out.println("\n Voulez vous regarder la liste de façon ordonné? (oui)");
            String choice = System.console().readLine();
            if(choice.equals("oui")){
                List<IVivant> temp = new ArrayList<>();
                temp.addAll(listeVivant);
                listeVivant.sort(IVivant);
                montrerListe();
                listeVivant.clear();
                listeVivant.addAll(temp);
            }*/
            System.out.println("--------------------------------------------------");
        }
        if(listeVivant.size() == 0)
            return false;
        else
            return true;
    }
    private void actions(){

        List<IVivant> tempList = new ArrayList<>();
        int propabiliteAlgueMulti = 15;
        SecureRandom rand = new SecureRandom();
        int temp = rand.nextInt(99);
        for(int i = 0; i < listeVivant.size(); i++){

            if(listeVivant.get(i) instanceof IAlgue && listeVivant.get(i) != null){

                IAlgue al = (IAlgue)listeVivant.get(i);
                al.changerPdv(2);
                al.vieillir();
                if(al.getAge() > 20){
                    supprimerMeurt(al);
                    System.out.println("- Une algue est morte");
                    continue;
                }
                
                if(rand.nextInt(100) <= propabiliteAlgueMulti && al.getAge() >= al.getAgeReproduction()){

                    IAlgue a =  al.multiplier(); //new Algue(rand.nextInt(this.listeVivant.get(i).getMaxPdv()), 0);
                    tempList.add(a);
                    System.out.println("- Une nouvelle algue a apparue");
                }
            }

            if(listeVivant.get(i) instanceof IPoisson && listeVivant.get(i) != null){

                IPoisson poisson = (IPoisson)listeVivant.get(i);
                poisson.changerPdv(-1);
                poisson.vieillir();
                if(poisson.getPdv() <= 0 || poisson.getAge() > 20) {
                    supprimerMeurt(poisson);
                    System.out.println("- "+poisson.getNom()+" est mort");
                    continue;
                }
                if(poisson.getPdv() <= 5){
                    if(poisson instanceof Herbivore){ //manger d'herbivore

                        Random algueToFind = new Random();
                        boolean mangeAlgue = false;

                        for(IVivant check : listeVivant){
                            if(check instanceof IAlgue)
                                mangeAlgue = true;
                        }
                        if(mangeAlgue){
                            int a;
                            
                            do{
                                a = algueToFind.nextInt(listeVivant.size());
                            } while(!(listeVivant.get(a) instanceof IAlgue));

                            IAlgue algue = (IAlgue)listeVivant.get(a);
                            if(poisson.getPdv() + 3 <= poisson.getMaxPdv())
                                poisson.manger(algue);
                                poisson.setBusy(true);
                                System.out.println("- "+poisson.getNom()+" a mangé un fragment d'algue");
                                if(algue.getPdv() <= 0){
                                    supprimerMeurt(algue);
                                    System.out.println("- Une algue a disparu de l'aquarium");
                                }
                        }
                    }
                    if(poisson instanceof Carnivore){ //manger de carnivore

                        Random poissonToFind = new Random();
                        boolean mangePoisson = false;

                        for(IVivant check : listeVivant){
                            if(check instanceof IPoisson && TypeVivant.valueOf(poisson.getType().name()) != TypeVivant.valueOf(check.getType().name()))
                                mangePoisson = true;
                        }
                        if(mangePoisson){

                            int a;
                            do{
                                a = poissonToFind.nextInt(listeVivant.size());
                            } while(!(listeVivant.get(a) instanceof IPoisson) || TypeVivant.valueOf(poisson.getType().name()) == TypeVivant.valueOf(listeVivant.get(a).getType().name()));

                            IPoisson poissonAAttaquer = (IPoisson)listeVivant.get(a);

                            System.out.print("- "+poisson.getNom()+" a attaqué "+poissonAAttaquer.getNom()+". Réussi? ");
                            if(poisson.getPdv() + 5 <= poisson.getMaxPdv()){
                                poisson.manger(poissonAAttaquer);
                                poisson.setBusy(true);
                                if(poissonAAttaquer.getPdv()-poisson.getPAttaque() <= 0){
                                    supprimerMeurt(poissonAAttaquer);
                                    System.out.println("- "+poissonAAttaquer.getNom()+" est meurt");
                                }
                            }
                        }
                    }
                }
                else if(poisson.getAge() >= poisson.getAgeReproduction() && temp < 49){

                    Random poissonToFind = new Random();
                    boolean reproduction = false;
                    for(IVivant check : listeVivant){
                        if(check instanceof IPoisson && poisson.getType().name().equals(check.getType().name()) 
                           && !(poisson.getSexe().equals(check.getSexe())) && !tempList.contains(check) && 
                           listeVivant.indexOf(check) > i && check.getAge() >= check.getAgeReproduction() && (((IPoisson)check).isBusy()) == false){ reproduction = true;}
                    }
                    if(reproduction){
                        int a;
                        do{ 
                            a = poissonToFind.nextInt(listeVivant.size());
                        } while(!(listeVivant.get(a) instanceof IPoisson) || 
                                  !(poisson.getType().name().equals(listeVivant.get(a).getType().name())) ||
                                  poisson.getSexe().equals(listeVivant.get(a).getSexe()) ||
                                  tempList.contains(listeVivant.get(a)) || 
                                  listeVivant.get(a).getAge() < listeVivant.get(a).getAgeReproduction() ||
                                  ((IPoisson)listeVivant.get(a)).isBusy() == true);
                        IPoisson partenaire = (IPoisson)listeVivant.get(a);

                        IPoisson newPoisson = poisson.reproduire(partenaire);
                        tempList.add(partenaire);
                        tempList.add(poisson);
                        listeVivant.remove(partenaire);
                        if(i == listeVivant.size()-1){
                            continue;
                        }
                        else{
                            listeVivant.add(i+1,partenaire);
                            i += 2;
                        }
                        if(newPoisson != null){
                            System.out.println("- "+newPoisson.getType()+" "+newPoisson.getNom()+" a apparue");
                            tempList.add(newPoisson);
                        }
                    }
                    else continue;
                }
            }
            else continue;           
        }
        for(int i = 0; i < tempList.size(); i++){
            if(!(listeVivant.contains(tempList.get(i))))
                ajouterVivant(tempList.get(i));
        }
        Collections.shuffle(listeVivant);
        System.out.println();
    }
    public static class Builder { //builder pour aquarium
        
        public Builder(int nbrealgues, int nbreCarni, int nbreHerbi){
            nbreAlgues(nbrealgues); nbreDePoissonCarni(nbreCarni); nbreDePoissonHerbi(nbreHerbi);
        }
        public Builder nbreDePoissonCarni(int nbre){

            List<IPoisson> liste = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < nbre; i++){

                TypeVivant a;
                do{
                    a = TypeVivant.values()[random.nextInt(TypeVivant.values().length)];
                }
                while(a.equals(TypeVivant.Algue) || a.equals(TypeVivant.Bare) || a.equals(TypeVivant.Carpe) || a.equals(TypeVivant.Sole));

                if(a.equals(TypeVivant.Mérou) || a.equals(TypeVivant.Poisson_clown) || a.equals(TypeVivant.Thon)){
                    int sexe = random.nextInt(2);
                    Character genre = null;
                    if(sexe == 0) genre = 'M'; 
                    else if(sexe == 1) genre = 'F';
                    Carnivore car = new Carnivore(a.toString(), Poisson.generateName(), genre, 10, random.nextInt(20), random.nextInt(6));
                    liste.add(car);
                }
            }
            for(IVivant i : liste) ajouterVivant(i);;
            return this;
        }
        public Builder nbreDePoissonHerbi(int nbre){

            List<IPoisson> liste = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < nbre; i++){

                TypeVivant a;
                do{
                    a = TypeVivant.values()[random.nextInt(TypeVivant.values().length)];
                }
                while(a.equals(TypeVivant.Algue) || a.equals(TypeVivant.Mérou) || a.equals(TypeVivant.Poisson_clown) || a.equals(TypeVivant.Thon));

                if(a.equals(TypeVivant.Bare) || a.equals(TypeVivant.Carpe) || a.equals(TypeVivant.Sole)){
                    int sexe = random.nextInt(2);
                    Character genre = null;
                    if(sexe == 0) genre = 'M'; 
                    else if(sexe == 1) genre = 'F';
                    Herbivore car = new Herbivore(a.toString(), Poisson.generateName(), genre, 10, random.nextInt(20), random.nextInt(6));
                    liste.add(car);
                }
            }
            for(IVivant i : liste) ajouterVivant(i);
            return this;
        }
        public Builder nbreAlgues(int nbre){

            List<IAlgue> liste = new ArrayList<>();
            Random random = new Random();
            for(int i = 0; i < nbre; i++){

                TypeVivant a;
                do{
                    a = TypeVivant.values()[random.nextInt(TypeVivant.values().length)];
                }
                while(!(a.equals(TypeVivant.Algue)));

                if(a.equals(TypeVivant.Algue)){
                    Algue car = new Algue(random.nextInt(10), random.nextInt(20));
                    liste.add(car);
                }
            }
            for(IVivant i : liste) ajouterVivant(i);;
            return this;
        }
        public Aquarium build(){
            return new Aquarium(this);
        }
    }
}
