package wr1ttenyu.f1nal.study.designpattern.pattern23.state;

public class RaffleAcitivity {

    private State state;

    private int count;

    private State dispenseState= new DispenseState(this);
    private State dispenseOutState= new DispenseOutState(this);
    private State noRaffleState= new NoRaffleState(this);
    private State canRaffleState= new CanRaffleState(this);

    public RaffleAcitivity(int count) {
        this.count = count;
        state = new NoRaffleState(this);
    }

    public void deduceMoney() {
        state.deduceMoney();
    }

    public boolean raffle() {
        return state.raffle();
    }

    public boolean dispensePrize() {
        count--;
        return state.dispensePrize();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }
}
