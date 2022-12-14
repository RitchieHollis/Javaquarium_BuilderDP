import Classes.*;
public class Main {
    
    public static void main(String[] args) {

        Aquarium aquarium = new Aquarium.Builder(3,6,6).build();

        boolean boucle = true;
        while(boucle){ 

            boucle = aquarium.passerUnJour();
            System.console().readLine();
        }
    }
}