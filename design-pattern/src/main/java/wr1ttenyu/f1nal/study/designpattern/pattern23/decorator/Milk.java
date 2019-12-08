package wr1ttenyu.f1nal.study.designpattern.pattern23.decorator;

public class Milk extends Decorator {

    public Milk(Drink mainDrink) {
        super(mainDrink);
        super.setDes("牛奶");
        super.setPrice(3);
    }
}
