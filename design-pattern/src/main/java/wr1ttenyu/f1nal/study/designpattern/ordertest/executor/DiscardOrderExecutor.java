package wr1ttenyu.f1nal.study.designpattern.ordertest.executor;

import wr1ttenyu.f1nal.study.designpattern.ordertest.Order;

public interface DiscardOrderExecutor extends Executor {

   Order discardOrder(Order order);
}
