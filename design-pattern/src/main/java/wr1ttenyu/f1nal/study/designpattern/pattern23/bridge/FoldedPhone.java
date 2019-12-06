package wr1ttenyu.f1nal.study.designpattern.pattern23.bridge;

public class FoldedPhone extends PhoneStyle {

    @Override
    public void call() {
        System.out.println("我是折叠手机");
        System.out.println(function.call());
    }

    @Override
    public void close() {
        System.out.println("我是折叠手机");
        System.out.println(function.close());
    }

    @Override
    public void open() {
        System.out.println("我是折叠手机");
        System.out.println(function.call());
    }
}
