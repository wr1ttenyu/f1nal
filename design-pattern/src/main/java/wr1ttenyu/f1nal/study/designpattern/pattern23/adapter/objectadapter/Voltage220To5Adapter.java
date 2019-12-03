package wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.objectadapter;

import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter.IVoltage5V;
import wr1ttenyu.f1nal.study.designpattern.pattern23.adapter.classadapter.Voltage220V;

public class Voltage220To5Adapter implements IVoltage5V {

    private Voltage220V voltage220V;

    @Override
    public Integer getVoltage5V() {
        return voltage220V.getVoltage220V()/44;
    }

    public void setVoltage220V(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }
}
