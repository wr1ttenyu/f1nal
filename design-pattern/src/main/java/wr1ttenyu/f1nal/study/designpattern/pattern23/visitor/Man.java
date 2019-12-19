package wr1ttenyu.f1nal.study.designpattern.pattern23.visitor;

public class Man extends Person {

    @Override
    void accept(Action action) {
        action.getManResult(this);
    }
}
