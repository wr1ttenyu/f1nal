package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.abstractfactory;

import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.BJCheesePizza;
import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.BJPepperPizza;
import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.Pizza;

public class BJPizzaFactory implements AbsPizzeFactory {

    @Override
    public Pizza makePizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        } else if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else {
            System.out.println("pizza 类型不存在");
        }
        return pizza;
    }
}
