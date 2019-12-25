package wr1ttenyu.f1nal.study.designpattern.pattern23.mediator;

public class Tv extends Colleague {

    public Tv(Mediator mediator, String name) {
        super(mediator, name);
        mediator.addColleague(Tv.class, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        mediator.receiveMessage(this, stateChange);
    }

    public void open() {
        System.out.println(name + "打开了");
    }

    public void close() {
        System.out.println(name + "关闭了");
    }
}
