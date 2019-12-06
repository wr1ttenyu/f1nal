package wr1ttenyu.f1nal.study.designpattern.pattern23.bridge;

public class VivoFunction implements IFunction {

    @Override
    public String call() {
        return "vivo手机打电话";
    }

    @Override
    public String close() {
        return "vivo手机关机";
    }

    @Override
    public String open() {
        return "vivo手机开机";
    }
}
