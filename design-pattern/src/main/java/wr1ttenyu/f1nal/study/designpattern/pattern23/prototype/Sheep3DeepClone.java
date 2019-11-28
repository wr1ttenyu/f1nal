package wr1ttenyu.f1nal.study.designpattern.pattern23.prototype;

import java.io.*;

public class Sheep3DeepClone implements Serializable, Cloneable {

    private String name;

    private Integer age;

    private String color;

    private Sheep1 friend;

    public Sheep3DeepClone(String name, Integer age, String color) {
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

    public Sheep1 getFriend() {
        return friend;
    }

    public void setFriend(Sheep1 friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Sheep3DeepClone{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", friend=" + friend +
                '}';
    }

    @Override
    protected Object clone() {
        Sheep3DeepClone sheep = null;
        try {
            sheep = (Sheep3DeepClone)super.clone();
            Sheep1 friend = (Sheep1)getFriend().clone();
            sheep.setFriend(friend);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return sheep;
    }

    public Sheep3DeepClone deepCloneBySerialization() {
        Sheep3DeepClone sheep = null;
        try {
            // 读
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            // 写
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            sheep = (Sheep3DeepClone)ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sheep;
    }
}
