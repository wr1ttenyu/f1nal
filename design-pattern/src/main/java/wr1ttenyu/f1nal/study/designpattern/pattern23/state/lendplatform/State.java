package wr1ttenyu.f1nal.study.designpattern.pattern23.state.lendplatform;

/**
 * 借款审核状态接口
 */
interface State {

    RuntimeException EXCEPTION = new RuntimeException("操作流程不允许");

    /**
     * 电审
     *
     * @param context
     */
    default void checkEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 电审失败
     *
     * @param context
     */
    default void checkFailEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 定价发布
     *
     * @param context
     */
    default void makePriceEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 接单
     *
     * @param context
     */
    default void acceptOrderEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 无人接单失效
     *
     * @param context
     */
    default void noPeopleAcceptEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 付款
     *
     * @param context
     */
    default void payOrderEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 接单有人支付失效
     *
     * @param context
     */
    default void payOrderFailEvent(Context context) {
        throw EXCEPTION;
    }

    /**
     * 反馈
     *
     * @param context
     */
    default void feedBackEvent(Context context) {
        throw EXCEPTION;
    }

    String getCurrentState();
}
