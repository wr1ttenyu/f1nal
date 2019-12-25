package wr1ttenyu.f1nal.study.designpattern.pattern23.memento;

public class Originator {

    private String state;

    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setState(state);
        return memento;
    }

    public void recoverFromMemento(Memento memento) {
        setState(memento.getState());
    }

    public void showState() {
        System.out.println("当前位置：" + state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
