package wr1ttenyu.f1nal.study.designpattern.pattern23.prototype;

public class Sheep2 implements Cloneable {

    private String name;

    private Integer age;

    private String color;

    private Sheep2 friend;

    public Sheep2(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Sheep2 getFriend() {
        return friend;
    }

    public void setFriend(Sheep2 friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Sheep2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", friend=" + friend +
                '}';
    }

    @Override
    protected Object clone() {
        Sheep2 sheep = null;
        try {
            sheep = (Sheep2)super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return sheep;
    }
}
