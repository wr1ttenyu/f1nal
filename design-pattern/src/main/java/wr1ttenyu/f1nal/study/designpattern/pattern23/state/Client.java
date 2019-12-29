package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

/**
 * 需求
 * APP 抽奖活动问题
 * 请编写程序完成 APP 抽奖活动 具体要求如下:
 * 1) 假如每参加一次这个活动要扣除用户 50 积分， 中奖概率是 10%
 * 2) 奖品数量固定， 抽完就不能抽奖
 * 3) 活动有四个状态: 可以抽奖、 不能抽奖、 发放奖品和奖品领完
 */
public class Client {

    public static void main(String[] args) {
        RaffleAcitivity raffleAcitivity = new RaffleAcitivity(1);
        State state = raffleAcitivity.getState();
        System.out.println("activity init state: " + state.getClass());
        raffleAcitivity.deduceMoney();
        for (int i = 0; i < 10; i++) {
            if(raffleAcitivity.raffle()) {
                raffleAcitivity.dispensePrize();
            }
        }
    }
}
