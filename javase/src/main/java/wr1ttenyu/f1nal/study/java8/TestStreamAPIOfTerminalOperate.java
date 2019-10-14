package wr1ttenyu.f1nal.study.java8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
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

        LongStream.rangeClosed(0,111111111).parallel().sum();
    }

}

