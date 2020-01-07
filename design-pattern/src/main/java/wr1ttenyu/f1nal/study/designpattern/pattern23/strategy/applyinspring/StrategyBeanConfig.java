package wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@ComponentScan("wr1ttenyu.f1nal.study.designpattern.pattern23.strategy.applyinspring")
public class StrategyBeanConfig {

    @Bean("earnMoneyStragetyList")
    public List<IGetMoneyStrategy> getMoneyStragetyList() {
        return new CopyOnWriteArrayList<>();
    }

    @Bean("earnMoneyStragetyMap")
    public Map<MoneyType, IGetMoneyStrategy> getMoneyStragetyMap() {
        return new ConcurrentHashMap<>(5);
    }
}
