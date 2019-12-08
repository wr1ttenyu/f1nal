package wr1ttenyu.f1nal.study.designpattern.pattern23.decorator;

public class Decorator extends Drink {

    private Drink mainDrink;

    public Decorator(Drink mainDrink) {
        this.mainDrink = mainDrink;
    }

    @Override
    public float cost() {
        return super.getPrice() + mainDrink.cost();
    }

    @Override
    public String getDes() {
        return super.getDes() + " " + super.getPrice() + " && " + mainDrink.getDes();
    }
}
