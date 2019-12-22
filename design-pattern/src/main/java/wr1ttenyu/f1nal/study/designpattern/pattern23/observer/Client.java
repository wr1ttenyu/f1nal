package wr1ttenyu.f1nal.study.designpattern.pattern23.observer;

/**
 * 天气预报项目需求,具体要求如下：
 * 1) 气象站可以将每天测量到的温度， 湿度， 气压等等以公告的形式发布出去(比如发布到自己的网站或第三方)。
 * 2) 需要设计开放型 API， 便于其他第三方也能接入气象站获取数据。
 * 3) 提供温度、 气压和湿度的接口
 * 4) 测量数据更新时， 要能实时的通知给第三方
 *
 * 传统设计方案：
 * 1. 推送  设计一个气象站类 和 气象信息接收端类，气象变化时，气象站类主动向气象信息接收端类推送气象信息数据
 *
 * 传统方案分析
 * 1. 其他第三方接入气象站获取数据的问题，
 * 2. 无法在运行时动态的添加第三方，增加一个接入方时，就要修改 WeatherData 的 dataChange 方法
 * 3. 违反 ocp 原则 => 观察者模式
 */
public class Client {

    public static void main(String[] args) {
        CurrentConditions currentConditions = new CurrentConditions();
        WeatherData weatherData = new WeatherData(currentConditions);
        weatherData.setData(19, 26, 50);
        System.out.println("============天气情况变化=============");
        weatherData.setData(15, 20, 60);

        System.out.println("============ 观察者模式 =============");
        WeatherData01 weatherData01 = new WeatherData01();
        weatherData01.registerObserver(new CurrentConditions01());
        weatherData01.registerObserver(new Sina());
        weatherData01.setData(19, 26, 50);
        System.out.println("============ 观察者模式 --- 天气情况变化 =============");
        weatherData01.setData(15, 20, 60);

    }
}
