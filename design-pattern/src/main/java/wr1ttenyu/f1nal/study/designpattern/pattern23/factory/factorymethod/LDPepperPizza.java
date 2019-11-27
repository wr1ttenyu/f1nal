package wr1ttenyu.f1nal.study.designpattern.pattern23.factory.factorymethod;

public class LDPepperPizza extends Pizza {

    public LDPepperPizza() {
        setName("伦敦的胡椒Pizza");
    }

    @Override
    public void prepare() {
        System.out.println("给伦敦的胡椒 Pizza 准备原材料");
    }
}
