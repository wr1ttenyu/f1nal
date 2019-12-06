package wr1ttenyu.f1nal.study.designpattern.pattern23.bridge;

public class XiaoMiFunction implements IFunction {

    @Override
    public String call() {
        return "小米手机打电话";
    }

    @Override
    public String close() {
        return "小米手机关机";
    }

    @Override
    public String open() {
        return "小米手机开机";
    }
}