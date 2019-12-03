package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter;

public class Voltage220To5Adapter extends Voltage220V implements IVoltage5V{
    @Override
    public Integer getVoltage5V() {
        return super.getVoltage220V()/44;
    }
}
