package wr1ttenyu.f1nal.study.designpattern.pattern23.observer;

public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
