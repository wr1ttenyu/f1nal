package wr1ttenyu.f1nal.study.designpattern.ordertest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import wr1ttenyu.f1nal.study.designpattern.ordertest.executor.CancelOrderExecutor;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements BeanPostProcessor {

    public List<CancelOrderExecutor> CANCLE_EXECUTOR_CONTAINER = new ArrayList<>();
    public List<CancelOrderExecutor> CREATE_EXECUTOR_CONTAINER = new ArrayList<>();
    public List<CancelOrderExecutor> DIACARD_EXECUTOR_CONTAINER = new ArrayList<>();
    public List<CancelOrderExecutor> INVERSE_EXECUTOR_CONTAINER = new ArrayList<>();
    public List<CancelOrderExecutor> PAYSUCCESS_EXECUTOR_CONTAINER = new ArrayList<>();
    public List<CancelOrderExecutor> REFUND_EXECUTOR_CONTAINER = new ArrayList<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof CancelOrderExecutor) {

        }
        return null;
    }
}
