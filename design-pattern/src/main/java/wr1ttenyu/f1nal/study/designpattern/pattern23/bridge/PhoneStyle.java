package wr1ttenyu.f1nal.study.designpattern.pattern23.bridge;

public abstract class PhoneStyle {

    private String style;

    protected IFunction function;

    public void setStyle(String style) {
        this.style = style;
    }

    public void setFunction(IFunction function) {
        this.function = function;
    }

    protected void call() {
        System.out.println(style + " " + function.call());
    }

    protected void close() {
        System.out.println(style + " " + function.close());
    }

    protected void open() {
        System.out.println(style + " " + function.call());
    }
}
