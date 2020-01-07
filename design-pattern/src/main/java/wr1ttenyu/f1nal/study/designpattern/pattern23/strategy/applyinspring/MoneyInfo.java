package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;

public class MoneyInfo {

    private MoneyType moneyType;

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    @Override
    public String toString() {
        return "MoneyInfo{" +
                "moneyType='" + moneyType + '\'' +
                '}';
    }
}
