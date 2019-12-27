package wr1ttenyu.f1nal.study.designpattern.pattern23.mediator;

public abstract class Colleague {

    protected Mediator mediator;

    public String name;

    public Colleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void sendMessage(int stateChange);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
