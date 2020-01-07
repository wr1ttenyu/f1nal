package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;

public interface IGetMoneyStrategy {

    Boolean judgeMoneyCanEarn(MoneyInfo moneyInfo);

    void earnMoney(MoneyInfo moneyInfo);
}
