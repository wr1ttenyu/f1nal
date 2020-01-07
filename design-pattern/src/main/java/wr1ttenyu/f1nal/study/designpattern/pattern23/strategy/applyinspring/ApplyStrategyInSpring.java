package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class ApplyStrategyInSpring {

    // SOLVE spring中两个策略模式的应用  https://mp.weixin.qq.com/s/V4d_nWUsU1HfYAYFVXsquw
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(StrategyBeanConfig.class);

        List<IGetMoneyStrategy> earnMoneyStragetyList = (List<IGetMoneyStrategy>) applicationContext
                .getBean("earnMoneyStragetyList");

        Map<MoneyType, IGetMoneyStrategy> earnMoneyStragetyMap = (Map<MoneyType, IGetMoneyStrategy>) applicationContext
                .getBean("earnMoneyStragetyMap");

        MoneyInfo easyMoney = new MoneyInfo();
        easyMoney.setMoneyType(MoneyType.EASY_MONEY);

        MoneyInfo hardMoney = new MoneyInfo();
        hardMoney.setMoneyType(MoneyType.HARD_MONEY);

        // list 数据结构的策略
        for (IGetMoneyStrategy earnMoneyStrategy : earnMoneyStragetyList) {
            if(earnMoneyStrategy.judgeMoneyCanEarn(easyMoney))
                earnMoneyStrategy.earnMoney(easyMoney);
            if(earnMoneyStrategy.judgeMoneyCanEarn(hardMoney))
                earnMoneyStrategy.earnMoney(hardMoney);
        }

        // map 数据结构的策略
        earnMoneyStragetyMap.get(easyMoney.getMoneyType()).earnMoney(easyMoney);
        earnMoneyStragetyMap.get(hardMoney.getMoneyType()).earnMoney(hardMoney);
    }
}
