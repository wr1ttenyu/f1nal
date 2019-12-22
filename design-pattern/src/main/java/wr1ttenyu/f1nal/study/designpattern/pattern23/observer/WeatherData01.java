package wr1ttenyu.f1nal.study.designpattern.pattern23.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * 类是核心
 * 1. 包含最新的天气情况信息
 * 2. 含有 CurrentConditions 对象
 * 3. 当数据有更新时， 就主动的调用 CurrentConditions 对象 update 方法(含 display), 这样他们（接入方） 就看
 *      到最新的信息
 */
public class WeatherData01 implements Subject {

    private float temperature;
    private float pressure;
    private float humidity;
    private List<Observer> observers = new ArrayList<>();

    //当数据有更新时，就调用 setData
    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用 dataChange， 将最新的信息 推送给 接入方 currentConditions
        dataChange();
    }

    public void dataChange() {
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, pressure, humidity);
        }
    }
}