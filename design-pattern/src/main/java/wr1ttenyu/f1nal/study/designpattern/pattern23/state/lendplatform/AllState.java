package wr1ttenyu.f1nal.study.designpattern.pattern23.state.lendplatform;

class FeedBackState implements State {

    @Override
    public String getCurrentState() {
        return StateEnum.FEED_BACK.getValue();
    }
}

class GenerateState implements State {

    @Override
    public void checkEvent(Context context) {
        System.out.println("-----------------  审核成功  -----------------");
        System.out.println("贷款订单审核成功！进入审核定价阶段！");
        context.setState(new ReviewState());
    }

    @Override
    public void checkFailEvent(Context context) {
        System.out.println("-----------------  审核失败  -----------------");
        System.out.println("贷款订单审核失败！订单gg！");
        context.setResult("贷款订单审核失败！订单gg！");
        context.setState(new FeedBackState());
    }

    @Override
    public String getCurrentState() {
        return StateEnum.GENERATE.getValue();
    }
}

class ReviewState implements State {

    @Override
    public void makePriceEvent(Context context) {
        System.out.println("-----------------  定价成功  -----------------");
        System.out.println("贷款订单定价成功！定价为: 90W！");
        context.setState(new PublishedState());
    }

    @Override
    public String getCurrentState() {
        return StateEnum.REVIEWED.getValue();
    }
}

class PublishedState implements State {

    @Override
    public void acceptOrderEvent(Context context) {
        System.out.println("-----------------  订单接单成功  -----------------");
        System.out.println("订单接单成功！请支付，金额为: 90W！");
        context.setState(new NotPayState());
    }

    @Override
    public void noPeopleAcceptEvent(Context context) {
        System.out.println("-----------------  订单无人接单  -----------------");
        System.out.println("订单无人接单！订单关闭！");
        context.setResult("订单无人接单！订单关闭！");
        context.setState(new FeedBackState());
    }

    @Override
    public String getCurrentState() {
        return StateEnum.PUBLISHED.getValue();
    }
}

class NotPayState implements State {

    @Override
    public void payOrderEvent(Context context) {
        System.out.println("-----------------  订单支付成功  -----------------");
        System.out.println("贷款订单支付成功！支付金额为: 90W！");
        context.setState(new PaidState());
    }

    @Override
    public void payOrderFailEvent(Context context) {
        System.out.println("-----------------  订单支付失败  -----------------");
        System.out.println("贷款订单支付失败，订单已被关闭！");
        context.setResult("贷款订单支付失败，订单已被关闭！");
        context.setState(new FeedBackState());
    }

    @Override
    public String getCurrentState() {
        return StateEnum.NOT_PAY.getValue();
    }
}

class PaidState implements State {

    @Override
    public void feedBackEvent(Context context) {
        System.out.println("-----------------  订单还款成功  -----------------");
        System.out.println("订单还款成功，订单关闭！");
        context.setResult("订单还款成功，订单关闭！");
        context.setState(new FeedBackState());
    }

    @Override
    public String getCurrentState() {
        return StateEnum.PAID.getValue();
    }
}

