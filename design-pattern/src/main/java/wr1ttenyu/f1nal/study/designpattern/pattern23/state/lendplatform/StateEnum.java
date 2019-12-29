package wr1ttenyu.f1nal.study.designpattern.pattern23.state.lendplatform;

public enum StateEnum {

    GENERATE(1, "新订单"),

    REVIEWED(1, "订单待定价"),

    PUBLISHED(1, "订单已发布"),

    NOT_PAY(1, "订单待支付"),

    PAID(1, "订单已支付"),

    FEED_BACK(1, "订单流程结束");

    StateEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
