package Interfaces;

public interface IPoisson extends IVivant {
    
    void manger(IVivant a);
    IPoisson reproduire(IPoisson a);
    int getPAttaque();
    boolean isBusy();
    void setBusy(boolean a);
}

