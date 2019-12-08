package wr1ttenyu.f1nal.study.designpattern.pattern23.decorator;

public class Chocolate extends Decorator {

    public Chocolate(Drink mainDrink) {
        super(mainDrink);
        super.setDes("巧克力");
        super.setPrice(4);
    }
}
