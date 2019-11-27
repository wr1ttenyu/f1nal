package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.abstractfactory;

import wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod.Pizza;

public interface AbsPizzeFactory {

    Pizza makePizza(String orderType);
}
