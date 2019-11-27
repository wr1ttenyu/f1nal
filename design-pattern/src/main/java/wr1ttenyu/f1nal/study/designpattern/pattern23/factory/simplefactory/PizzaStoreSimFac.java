package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.simplefactory;

class PizzaStoreSimFac {

    public static void main(String[] args) {
        // 简单工厂模式
        SimpleFactory2 simpleFactory = new SimpleFactory2();
        new OrderPizzaSimFac(simpleFactory).orderPizza();
        // 简单工厂模式之静态方法模式
        new OrderPizzaStatic().orderPizza();
    }
}

