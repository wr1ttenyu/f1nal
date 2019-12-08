package wr1ttenyu.f1nal.study.designpattern.pattern23.decorator;

public abstract class Drink {

    private String des;

    private float price = 0.0f;

    public abstract float cost();

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}