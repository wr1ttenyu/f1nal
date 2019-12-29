package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

public class NoRaffleState implements State {

    RaffleAcitivity raffleAcitivity;

    public NoRaffleState(RaffleAcitivity raffleAcitivity) {
        this.raffleAcitivity = raffleAcitivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("扣除50积分成功，可以抽奖了");
        raffleAcitivity.setState(raffleAcitivity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("还没有扣除积分，不可以抽奖");
        return false;
    }

    @Override
    public boolean dispensePrize() {
        System.out.println("还没有中奖，不能领奖");
        return false;
    }
}
