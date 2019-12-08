package wr1ttenyu.f1nal.study.designpattern.pattern23.decorator;

public class CoffeeBar {

    public static void main(String[] args) {
        Drink longCoffee = new LongCoffee();
        longCoffee = new Milk(longCoffee);
        longCoffee = new Chocolate(longCoffee);
        longCoffee = new Milk(longCoffee);
        System.out.println(longCoffee.getDes());
        System.out.println(longCoffee.cost());
    }
}
