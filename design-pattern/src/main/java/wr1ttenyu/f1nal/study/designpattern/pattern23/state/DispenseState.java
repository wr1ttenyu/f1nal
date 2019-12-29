package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

public class DispenseState implements State {

    RaffleAcitivity raffleAcitivity;

    public DispenseState(RaffleAcitivity raffleAcitivity) {
        this.raffleAcitivity = raffleAcitivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("已中奖,请先领奖！");
    }

    @Override
    public boolean raffle() {
        System.out.println("已中奖,请先领奖！");
        return false;
    }

    @Override
    public boolean dispensePrize() {
        System.out.println("恭喜领取奖金1个亿！");
        if (raffleAcitivity.getCount() == 0) raffleAcitivity.setState(raffleAcitivity.getDispenseOutState());
        return true;
    }
}
