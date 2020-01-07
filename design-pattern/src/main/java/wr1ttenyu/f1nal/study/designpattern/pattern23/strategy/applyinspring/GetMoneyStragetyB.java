package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;

import org.springframework.stereotype.Service;

@Service
public class GetMoneyStragetyB implements IGetMoneyStrategy {

    @Override
    public Boolean judgeMoneyCanEarn(MoneyInfo moneyInfo) {
        if (moneyInfo != null && MoneyType.HARD_MONEY.equals(moneyInfo.getMoneyType()))
            return true;
        else
            return false;
    }

    @Override
    public void earnMoney(MoneyInfo moneyInfo) {
        System.out.println(moneyInfo.getMoneyType() + " 这个钱赚的累啊~~~");
    }
}
