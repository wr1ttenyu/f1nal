package wr1ttenyu.f1nal.study.java8.stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * step of operate steam api:
 * 1. create stream
 * <p>
 * 2. middle operate 中间操作
 * <p>
 * 3. terminal operate 终止操作
 */
public class TestStreamAPIOfTerminalOperate {

    private static List<Person> personList;

    static {
        personList = new ArrayList<>();
        personList.add(new Person("wr1ttenyu", 13, UserState.BUSY));
        personList.add(new Person("licongrong", 123, UserState.FREE));
        personList.add(new Person("zhaowanli", 45, UserState.VOCATION));
        personList.add(new Person("wwy", 345, UserState.FREE));
        personList.add(new Person("money", 2, UserState.VOCATION));
    }

    /*
        find and mathch
        allMatch --- predicate whether all element match the condition
        anyMatch --- predicate whether to contain at least one element match the condition
        noneMatch --- predicate whether no element match the condition
        findFirst --- return the first element
        findAny --- return any element
        count --- count all element
        max --- find max element match the condition
        min --- find min element match the condition
    */
    @Test
    public void testStreamMatch() {
        boolean b1 = personList.stream().allMatch(person -> person.getState().equals(UserState.BUSY));
        Assertions.assertFalse(b1);
        boolean b2 = personList.stream().anyMatch(person -> person.getState().equals(UserState.FREE));
        Assertions.assertTrue(b2);
        boolean b3 = personList.stream().noneMatch(person -> person.getState().equals(UserState.VOCATION));
        Assertions.assertFalse(b3);
    }

    @Test
    public void testStreamFind() {
        Optional<Person> first = personList.stream().sorted(Comparator.comparingInt(Person::getAge)).findFirst();
        System.out.println(first.get());

        Optional<Person> any = personList.parallelStream().sorted(Comparator.comparingInt(Person::getAge)).findAny();
        System.out.println(any.get());
    }

    @Test
    public void testStreamSummaryOperation() {
        long count = personList.stream().filter(person -> person.getState().equals(UserState.BUSY)).count();
        System.out.println(count);

        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println(max.get());

        Optional<Person> min = personList.stream().min(Comparator.comparingInt(Person::getAge));
        System.out.println(min.get());

        LongStream.rangeClosed(0, 111111111).parallel().sum();
    }

    /**
     * stream can not be used after terminal operate
     */
    @Test
    public void testStreamCanRepeatUse() {
        Stream<Person> personStream = personList.stream().filter(person -> person.getState().equals(UserState.BUSY));

        long count = personStream.count();

        System.out.println(count);

        Optional<Person> max = personStream.max(Comparator.comparingInt(Person::getAge));
    }

    /**
     * reduce: can combine elements in stream repeatedly to get a value
     */
    @Test
    public void testReduce() {
        Optional<Person> person = personList.stream().reduce((person1, person2) -> person1);
        System.out.println(person.orElse(new Person("temp", 12, UserState.FREE)));

        int ageSum = personList.stream().mapToInt(Person::getAge).reduce(0, Integer::sum);
        System.out.println(ageSum);

        // 这里reduce的第三个参数  是在并行下面使用的
        // <a href="https://blog.csdn.net/icarusliu/article/details/79504602>具体可参见</a>
        Integer reduce = personList.stream().reduce(0, (age, per) -> age + per.getAge(), Integer::sum);
        System.out.println(reduce);
    }

    /**
     * collect: can collect elements in stream into a collection
     */
    @Test
    public void testCollect() {
        List<String> personNames = personList.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(personNames);

        Set<String> personNames2 = personList.stream().map(Person::getName).collect(Collectors.toSet());
        System.out.println(personNames2);
    }
}

