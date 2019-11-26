package wr1ttenyu.f1nal.study.designpattern.factory;

public class SimpleFactory {
}

abstract class Pizza {

    protected String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }
}

class CheesePizza extends Pizza {

    public CheesePizza() {
        super.setName("奶酪Pizza");
    }

    @Override
    public void prepare() {
        System.out.println(" 给奶酪Pizza 准备原材料");
    }
}

class GreekPizza extends Pizza {

    public GreekPizza() {
        super.setName("希腊Pizza");
    }

    @Override
    public void prepare() {
        System.out.println(" 给希腊Pizza 准备原材料");
    }
}
