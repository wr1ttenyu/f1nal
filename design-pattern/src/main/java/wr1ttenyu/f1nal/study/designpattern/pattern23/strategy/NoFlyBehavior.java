package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy;

public class NoFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("不会飞  哈哈哈哈~~~");
    }
}
