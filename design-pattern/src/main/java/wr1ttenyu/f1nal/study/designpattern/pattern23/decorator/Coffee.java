package wr1ttenyu.f1nal.study.designpattern.pattern23.decorator;

public class Coffee extends Drink {

    @Override
    public float cost() {
        return super.getPrice();
    }

    @Override
    public String getDes() {
        return super.getDes() + " " + super.getPrice();
    }
}
