package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod;

public class BJPepperPizza extends Pizza {

    public BJPepperPizza() {
        setName("北京的胡椒Pizza");
    }

    @Override
    public void prepare() {
        System.out.println("给北京的胡椒 Pizza 准备原材料");
    }
}
