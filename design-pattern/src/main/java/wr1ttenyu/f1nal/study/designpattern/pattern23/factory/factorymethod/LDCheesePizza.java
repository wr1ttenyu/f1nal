package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod;

public class LDCheesePizza extends Pizza {

    public LDCheesePizza() {
        setName("伦敦的奶酪Pizza");
    }

    @Override
    public void prepare() {
        System.out.println("给伦敦的奶酪Pizza 准备原材料");
    }
}
