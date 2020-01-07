package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class GetMoneyStragetyProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource(name = "earnMoneyStragetyList")
    List<IGetMoneyStrategy> earnMoneyStrategiesList;

    @Resource(name = "earnMoneyStragetyMap")
    Map<MoneyType, IGetMoneyStrategy> earnMoneyStrategiesMap;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof IGetMoneyStrategy) {
            MoneyInfo moneyInfo = new MoneyInfo();
            IGetMoneyStrategy earnMoneyStrategy = (IGetMoneyStrategy) bean;
            earnMoneyStrategiesList.add(earnMoneyStrategy);
            for (MoneyType value : MoneyType.values()) {
                moneyInfo.setMoneyType(value);
                if (earnMoneyStrategy.judgeMoneyCanEarn(moneyInfo)) {
                    earnMoneyStrategiesMap.put(value, earnMoneyStrategy);
                    break;
                }
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
