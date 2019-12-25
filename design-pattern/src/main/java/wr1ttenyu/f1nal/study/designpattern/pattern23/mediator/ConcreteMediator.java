package wr1ttenyu.f1nal.study.designpattern.pattern23.mediator;

public class ConcreteMediator extends Mediator {

    @Override
    void addColleague(Class clazz, Colleague colleague) {
        colleagues.put(clazz, colleague);
    }

    @Override
    void sendMessage() {

    }

    @Override
    void receiveMessage(Colleague colleague, int changeState) {
        if (colleague instanceof Tv) {
            Light light = (Light)colleagues.get(Light.class);
            if (changeState == 1) {
                // 开电视
                light.open();
            } else if (changeState == 0) {
                light.close();
            }
        } else if (colleague instanceof Light) {
            Tv tv = (Tv)colleagues.get(Tv.class);
            if (changeState == 1) {
                tv.open();
            } else if (changeState == 0) {
                tv.close();
            }
        }
    }
}
