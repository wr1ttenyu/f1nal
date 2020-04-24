package wr1ttenyu.f1nal.study.designpattern.ordertest.executor;

import wr1ttenyu.f1nal.study.designpattern.ordertest.Order;


public interface CancelOrderExecutor extends Executor {

    Order cancelOrder(Order order);



}
