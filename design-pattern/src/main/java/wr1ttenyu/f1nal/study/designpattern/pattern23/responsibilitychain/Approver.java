package wr1ttenyu.f1nal.study.designpattern.pattern23.responsibilitychain;

public abstract class Approver {

    private String name;

    private Approver nextApprover;

    public Approver(String name) {
        this.name = name;
    }

    public abstract void processRequest(PurchaseRequest purchaseRequest);

    public Approver getNextApprover() {
        return nextApprover;
    }

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
