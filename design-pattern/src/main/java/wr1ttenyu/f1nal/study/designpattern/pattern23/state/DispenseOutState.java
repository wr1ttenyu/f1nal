package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

public class DispenseOutState implements State {

    RaffleAcitivity raffleAcitivity;

    public DispenseOutState(RaffleAcitivity raffleAcitivity) {
        this.raffleAcitivity = raffleAcitivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("奖品领完了，请下次参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品领完了，请下次参加");
        return false;
    }

    @Override
    public boolean dispensePrize() {
        System.out.println("奖品领完了，请下次参加");
        return false;
    }
}
