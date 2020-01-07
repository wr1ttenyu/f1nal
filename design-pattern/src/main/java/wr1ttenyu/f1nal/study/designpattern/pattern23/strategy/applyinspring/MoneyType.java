package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;

public enum MoneyType {

    EASY_MONEY("EASY_MONEY"),

    HARD_MONEY("HARD_MONEY");

    private String type;

    MoneyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MoneyType{" +
                "type='" + type + '\'' +
                '}';
    }
}
