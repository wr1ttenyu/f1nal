package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.abstractfactory;

import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.Pizza;
import wr1ttenyu.f1nal.study.designpattern.utils.DesignPatterUtil;

public class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;
        String orderType;
        String pizzaFactoryName;
        AbsPizzeFactory pizzeFactory;
        for (; ; ) {
            pizzaFactoryName = DesignPatterUtil.getFactoryName();
            if("bj".equals(pizzaFactoryName)) {
                pizzeFactory = new BJPizzaFactory();
            } else if("ld".equals(pizzaFactoryName)) {
                pizzeFactory = new LDPizzaFactory();
            } else {
                System.out.println("不存在该pizza工厂");
                break;
            }

            orderType = DesignPatterUtil.getType();
            // 实际调用的是子类实现的 makePizza 方法
            pizza = pizzeFactory.makePizza(orderType);
            if(pizza == null) {
                System.out.println("pizza种类不存在，订购失败");
                break;
            }
            // 输出pizza制作流程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }
}

