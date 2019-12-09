package wr1ttenyu.f1nal.study.designpattern.pattern23.composite;

public abstract class OrganizationComponent {

    private String name;

    private String desc;

    public OrganizationComponent(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    protected void add(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    };

    protected void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    };

    abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
