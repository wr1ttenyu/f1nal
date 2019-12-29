package wr1ttenyu.f1nal.study.designpattern.pattern23.state.lendplatform;

public class Context implements State {

    private State state;
    // 订单结果
    private String result;

    public Context(State state) {
        this.state = state;
    }

    @Override
    public void checkEvent(Context context) {
        state.checkEvent(context);
    }

    @Override
    public void checkFailEvent(Context context) {
        state.checkFailEvent(context);
    }

    @Override
    public void makePriceEvent(Context context) {
        state.makePriceEvent(context);
    }

    @Override
    public void acceptOrderEvent(Context context) {
        state.acceptOrderEvent(context);
    }

    @Override
    public void noPeopleAcceptEvent(Context context) {
        state.noPeopleAcceptEvent(context);
    }

    @Override
    public void payOrderEvent(Context context) {
        state.payOrderEvent(context);
    }

    @Override
    public void payOrderFailEvent(Context context) {
        state.payOrderFailEvent(context);
    }

    @Override
    public void feedBackEvent(Context context) {
        state.feedBackEvent(context);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }
}
