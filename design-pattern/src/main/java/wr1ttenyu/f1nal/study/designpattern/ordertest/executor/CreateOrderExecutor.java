package wr1ttenyu.f1nal.study.designpattern.ordertest.executor;

import wr1ttenyu.f1nal.study.designpattern.ordertest.Order;

public interface CreateOrderExecutor extends Executor {

   Order createOrder(Order order);
}
