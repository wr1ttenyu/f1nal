package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod;

public class BJCheesePizza extends Pizza {

    public BJCheesePizza() {
        setName("北京的奶酪Pizza");
    }

    @Override
    public void prepare() {
        System.out.println("给北京的奶酪Pizza 准备原材料");
    }
}
