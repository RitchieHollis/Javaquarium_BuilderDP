import Interfaces.*;
import Classes.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {

        List<IVivant> ajouter = new ArrayList<>();
        Algue a1 = new Algue(15, 4); ajouter.add(a1);
        Algue a2 = new Algue(15, 4); ajouter.add(a2);
        Algue a3 = new Algue(15, 4); ajouter.add(a3);
        Carnivore c1 = new Carnivore("Thon","Dada", 'M', 10, 5, 3); ajouter.add(c1);
        Carnivore c2 = new Carnivore("Thon", "Fifi", 'F', 10, 4, 2); ajouter.add(c2);
        Carnivore c3 = new Carnivore("Mérou","Holmes", 'M', 10, 5, 3); ajouter.add(c3);
        Carnivore c4 = new Carnivore("Mérou", "Watson", 'F', 10, 4, 2); ajouter.add(c4);
        Carnivore c5 = new Carnivore("Mérou","Flip", 'M', 10, 5, 4); ajouter.add(c5);
        Carnivore c6 = new Carnivore("Mérou", "Flap", 'F', 10, 4, 3); ajouter.add(c6);
        Herbivore h1 = new Herbivore("Sole", "Popo", 'F', 10, 3, 4); ajouter.add(h1);
        Herbivore h2 = new Herbivore("Sole", "Octo", 'M', 10, 4, 5); ajouter.add(h2);
        Herbivore h3 = new Herbivore("Carpe", "Roco", 'M', 10, 5, 4); ajouter.add(h3);
        Herbivore h4 = new Herbivore("Carpe", "Maloc", 'F', 10, 5, 4); ajouter.add(h4);
        Herbivore h5 = new Herbivore("Bare", "Trisha", 'F', 10, 5, 4); ajouter.add(h5);
        Herbivore h6 = new Herbivore("Bare", "Jeremy", 'M', 10, 4, 5); ajouter.add(h6);
        
        Aquarium aquarium = new Aquarium();
       
        for(IVivant i : ajouter) aquarium.ajouterVivant(i);


        boolean boucle = true;
        while(boucle){ 

            boucle = aquarium.passerUnJour();
            System.console().readLine();
        }
    }
}