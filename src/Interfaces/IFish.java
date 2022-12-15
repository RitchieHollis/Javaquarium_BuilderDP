package Interfaces;

public interface IFish extends ILivingBeing {
    
    void eat(ILivingBeing a);
    IFish reproduire(IFish a);
    int getPAttack();
    boolean isBusy();
    void setBusy(boolean a);
}

