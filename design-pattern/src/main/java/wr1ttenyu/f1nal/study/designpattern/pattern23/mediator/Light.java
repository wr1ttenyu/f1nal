package wr1ttenyu.f1nal.study.designpattern.pattern23.mediator;

public class Light extends Colleague {

    public Light(Mediator mediator, String name) {
        super(mediator, name);
        mediator.addColleague(Light.class, this);
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
