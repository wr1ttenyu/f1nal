package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy;

public abstract class Duck implements FlyBehavior, QuackBehavior {

    protected QuackBehavior quackBehavior;

    protected FlyBehavior flyBehavior;
}
