package wr1ttenyu.f1nal.study.designpattern.pattern23.factory;

import wr1ttenyu.f1nal.study.designpattern.utils.DesignPatterUtil;

/**
 * 简单工厂模式介绍：
 *  1.简单工厂模式是属于创建型模式，是工厂模式的一种。简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实例。
 *     简单工厂模式是工厂模式家族中最简单实用的模式。
 *  2.简单工厂模式：定义一个创建对象的类，由这个类来封装实例化对象的行为（代码）
 *  3.在软件开发中，当我们会用到大量的创建某种、某类或者某批对象时，就会使用到工厂模式
 *
 *
 */
public class SimpleFactory2 {

    public Pizza createPizza(String orderType) {
        System.out.println("使用简单工厂模式创建pizza");
        return getPizza(orderType);
    }

    public static Pizza createPizzaStatic(String orderType) {
        System.out.println("使用简单工厂模式创建pizza 静态方法模式");
        return getPizza(orderType);
    }

    private static Pizza getPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
        } else {
            return pizza;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public static void main(String[] args) {
        SimpleFactory2 simpleFactory = new SimpleFactory2();
        new OrderPizza2(simpleFactory).orderPizza();
    }
}

class OrderPizza2 {

    private SimpleFactory2 simpleFactory;

    public OrderPizza2(SimpleFactory2 simpleFactory) {
        this.simpleFactory = simpleFactory;
    }

    public void orderPizza() {
        Pizza pizza = null;
        String orderType;
        for (; ; ) {
            orderType = DesignPatterUtil.getType();
            simpleFactory.createPizza(orderType);
        }
    }

}

/**
 * 静态方法方式的简单工厂
 */
class OrderPizza3 {

    public void orderPizza() {
        Pizza pizza = null;
        String orderType;
        for (; ; ) {
            orderType = DesignPatterUtil.getType();
            SimpleFactory2.createPizzaStatic(orderType);
        }
    }
}
