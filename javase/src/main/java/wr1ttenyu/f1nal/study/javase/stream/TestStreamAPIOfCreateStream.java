package wr1ttenyu.f1nal.study.javase.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * step of operate steam api:
 * 1. create stream
 * <p>
 * 2. middle operate 中间操作
 * <p>
 * 3. terminal operate 终止操作
 */
public class TestStreamAPIOfCreateStream {

    private static List<Person> personList;

    static {
        personList = new ArrayList<>();
        personList.add(new Person("wr1ttenyu", 13, UserState.BUSY));
        personList.add(new Person("licongrong", 123, UserState.FREE));
        personList.add(new Person("zhaowanli", 45, UserState.VOCATION));
        personList.add(new Person("wwy", 345, UserState.FREE));
        personList.add(new Person("money", 2, UserState.VOCATION));
    }

    @Test
    public void testCreateStream() {
        // 1. Collection provide two method to gain stream
        // stream() and parallelStream()
        List<String> strs = new ArrayList<>();
        Stream<String> stream = strs.stream(); // get a sequential stream
        Stream<String> parallelStream = strs.parallelStream(); // get a parallel stream

        // 2. Arrays provide stream() method to gain stream based on an array
        Integer[] arrInt = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(arrInt);

        // 3. use static method of() in Stream to create a stream
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        // 4. create infinite stream
        Stream<Integer> infinite = Stream.iterate(0, i -> i + 2).limit(10);
        Iterator<Integer> iterator = infinite.iterator();

        // 5. create generator stream
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);


    }

    @Test
    public void selfTest() {
        // By the following code
        // the stream return iterator is based on the stream source
        // the element in iterator is the element in source collection
        Stream<Person> limit = personList.stream().limit(2);
        Iterator<Person> iterator = limit.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            next.setName("123");
        }
        for (Person person : personList) {
            System.out.println(person.getName());
        }
    }
}

class Person {
    private String name;

    private Integer age;

    private UserState state;

    public Person(String name, Integer age, UserState state) {
        this.name = name;
        this.age = age;
        this.state = state;
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

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", state=" + state +
                '}';
    }
}

enum UserState {

    FREE("free"),

    BUSY("busy"),

    VOCATION("vocation");

    private String code;

    UserState(String code) {
        this.code = code;
    }

}