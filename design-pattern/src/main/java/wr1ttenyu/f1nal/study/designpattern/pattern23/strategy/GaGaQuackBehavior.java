package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy;

public class GaGaQuackBehavior implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("嘎嘎的叫  嘎嘎嘎~~~");
    }
}
