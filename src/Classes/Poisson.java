package Classes;

import Interfaces.*;

import java.util.*;


abstract public class Poisson implements IPoisson {
    
    protected int pdv; protected int age; protected String nom; protected char sexe;
    protected int maxPdv = 10;
    protected int pAttaque;
    protected int ageReproduction = 7;
    protected boolean occupe = false;

    List<TypeVivant> typesHerbi = new ArrayList<>(Arrays.asList(TypeVivant.Bare, TypeVivant.Sole, TypeVivant.Carpe));
    List<TypeVivant> typesCarni = new ArrayList<>(Arrays.asList(TypeVivant.Thon, TypeVivant.Mérou, TypeVivant.Poisson_clown));

    public Poisson(String nom, char sexe, int pdv, int age, int pAttaque) {

        this.nom = nom;
        this.sexe = sexe;
        this.pdv = pdv;
        this.age = age;
        this.pAttaque = pAttaque;
    }

    @Override public int getMaxPdv(){ return this.maxPdv; }

    @Override public String getNom(){ return this.nom; }

    @Override public Character getSexe(){ return this.sexe; }

    abstract public TypeVivant getType(); //mettre en abstract après les types

    @Override public int getPAttaque(){ return this.pAttaque; }

    @Override public int getPdv(){ return this.pdv; }
    @Override public void changerPdv(int damage){ this.pdv += damage; }

    @Override public int getAge(){ return this.age; }
    @Override public void vieillir(){ this.age++; }

    abstract public void manger(IVivant a);
    abstract public IPoisson reproduire(IPoisson a);

    public String generateName(){

        List<String> fragmentsNom = new ArrayList<>(Arrays.asList("ca","da","mo","di","du","ma","ca","no"));
        Random rand = new Random();
        String nom = "";
        int b = rand.nextInt(5);
        if(b == 0) b++;

        for(int i = 0; i < b; i++){
            if(i == 0){
                nom += fragmentsNom.get(rand.nextInt(fragmentsNom.size()));
                nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);;
            }
            else
                nom += fragmentsNom.get(rand.nextInt(fragmentsNom.size()));
        }
        return nom;
    }

    @Override public int getAgeReproduction(){ return this.ageReproduction; }

    @Override public boolean isBusy() { return this.occupe; }

    @Override public void setBusy(boolean a) { this.occupe = a;}

}
