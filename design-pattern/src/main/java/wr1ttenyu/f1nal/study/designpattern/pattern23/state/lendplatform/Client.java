package wr1ttenyu.f1nal.study.designpattern.pattern23.state.lendplatform;

/**
 * 状态模式在实际项目-借贷平台 源码剖析
 * <p>
 * 1) 借贷平台的订单， 有审核-发布-抢单 等等 步骤， 随着操作的不同， 会改变订单的状态, 项目中的这个模块实
 * 现就会使用到状态模式
 * <p>
 * 2) 通常通过 if/else 判断订单的状态， 从而实现不同的逻辑
 * <p>
 * 3) 使用状态模式完成 借贷平台项目的审核模块
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context(new GenerateState());
        printState(context.getCurrentState());
        context.checkEvent(context);
        printState(context.getCurrentState());
        context.makePriceEvent(context);
        printState(context.getCurrentState());
        context.acceptOrderEvent(context);
        printState(context.getCurrentState());
        context.payOrderEvent(context);
        printState(context.getCurrentState());
        context.feedBackEvent(context);
        printState(context.getCurrentState());
        printState(context.getResult());
    }

    private static void printState(String state) {
        System.out.println(state);
    }
}
