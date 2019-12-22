package wr1ttenyu.f1nal.study.designpattern.pattern23.observer;

/**
 * 显示当前天气情况（可以理解成是气象站自己的网站）
 */
public class Sina implements Observer {
    // 温度， 气压， 湿度
    private float temperature;
    private float pressure;
    private float humidity;

    // 更新 天气情况， 是由 WeatherData 来调用， 我使用推送模式
    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    // 显示
    public void display() {
        System.out.println("*** 新浪 天气预报 ***");
        System.out.println("***Today mTemperature: " + temperature + "***");
        System.out.println("***Today mPressure: " + pressure + "***");
        System.out.println("***Today mHumidity: " + humidity + "***");
    }
}
