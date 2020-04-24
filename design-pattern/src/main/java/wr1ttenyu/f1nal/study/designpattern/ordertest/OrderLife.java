package wr1ttenyu.f1nal.study.designpattern.ordertest;

import wr1ttenyu.f1nal.study.designpattern.ordertest.executor.*;

public interface OrderLife {

    Order createOrder(CreateOrderExecutor executor);

    Order discardOrder(DiscardOrderExecutor order);

    Order cancelOrder(CancelOrderExecutor order);

    Order paySuccess(PaySuccessExecutor order);

    Order inverseOrder(InverseOrderExecutor order);

    Order refundOrder(RefundOrderExecutor order);

}
