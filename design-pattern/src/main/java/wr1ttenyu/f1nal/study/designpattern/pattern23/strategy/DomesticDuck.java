package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy;

public class DomesticDuck extends Duck {

    public DomesticDuck() {
        flyBehavior = new NoFlyBehavior();
        quackBehavior = new YiYiQuackBehavior();
    }

    @Override
    public void fly() {
        flyBehavior.fly();
    }

    @Override
    public void quack() {
        quackBehavior.quack();
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}
