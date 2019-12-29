package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

import java.util.Random;

public class CanRaffleState implements State {

    RaffleAcitivity raffleAcitivity;

    public CanRaffleState(RaffleAcitivity raffleAcitivity) {
        this.raffleAcitivity = raffleAcitivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("已经扣除了积分，不用重复扣除了，可以直接抽奖了");
    }

    @Override
    public boolean raffle() {
        int result = new Random().nextInt(100);
        if(result > 50) {
            System.out.println("恭喜中奖了,中奖数字为: " + result);
            raffleAcitivity.setState(raffleAcitivity.getDispenseState());
            return true;
        }
        System.out.println("抽中数字为: " + result + " 遗憾未中奖!");
        return false;
    }

    @Override
    public boolean dispensePrize() {
        System.out.println("请先抽奖，中奖后才可以领奖!");
        return false;
    }
}
