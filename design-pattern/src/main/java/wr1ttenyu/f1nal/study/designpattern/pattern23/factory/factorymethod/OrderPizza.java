package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod;

import wr1ttenyu.f1nal.study.designpattern.utils.DesignPatterUtil;

abstract class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;
        String orderType;
        for (; ; ) {
            orderType = DesignPatterUtil.getType();
            // 实际调用的是子类实现的 makePizza 方法
            pizza = makePizza(orderType);
            // 输出pizza制作流程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }

    // 定义抽象方法 让子类去实现
    abstract Pizza makePizza(String orderType);
}

