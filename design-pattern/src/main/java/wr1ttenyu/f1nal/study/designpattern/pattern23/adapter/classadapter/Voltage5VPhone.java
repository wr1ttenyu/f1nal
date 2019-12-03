package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter;

public class Voltage5VPhone {

    public void charging(IVoltage5V voltage5V){
        System.out.println("获得" + voltage5V.getVoltage5V() + "V电压，开始充电....");
    }

}
