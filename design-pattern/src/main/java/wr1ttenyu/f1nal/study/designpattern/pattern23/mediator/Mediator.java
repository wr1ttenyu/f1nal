package wr1ttenyu.f1nal.study.designpattern.pattern23.mediator;

import java.util.HashMap;
import java.util.Map;

public abstract class Mediator {

    protected Map<Class, Colleague> colleagues = new HashMap<>();

    abstract void addColleague(Class clazz, Colleague colleague);

    abstract void sendMessage();

    abstract void receiveMessage(Colleague colleague, int changeState);
}
