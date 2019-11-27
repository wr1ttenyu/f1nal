package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.abstractfactory;

import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.LDCheesePizza;
import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.LDPepperPizza;
import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.Pizza;

public class LDPizzaFactory implements AbsPizzeFactory {

    @Override
    public Pizza makePizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        } else if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else {
            System.out.println("pizza 类型不存在");
        }
        return pizza;
    }
}
