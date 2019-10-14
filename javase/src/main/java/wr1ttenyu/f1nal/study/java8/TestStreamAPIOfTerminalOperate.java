package wr1ttenyu.f1nal.study.java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testMiddleOperateMap() {
        personList.stream().map(person -> {
            person.setName(person.getName() + "1");
            return person;
        });

        String[] strs = {"aaa", "bbb", "ccc", "ddd"};
        Arrays.stream(strs).map(TestStreamAPIOfTerminalOperate::seprateStringToCS);
        // TODO continue

        Arrays.stream(strs).flatMap(TestStreamAPIOfTerminalOperate::seprateStringToCS);
    }

    @Test
    public void testStreamSort() {
        List<String> list = new ArrayList<>();
        list.add("bbb");
        list.add("aaa");
        list.add("ddd");
        list.add("ccc");
        list.stream().sorted().forEach(System.out::println);
        for (String s : list) {
            System.out.println(s);
        }

        personList.stream().sorted((p1, p2) -> {
            int ageRes = p1.getAge().compareTo(p2.getAge());
            if(ageRes == 0) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return ageRes;
            }
        }).forEach(System.out::println);

        for (Person person : personList) {
            System.out.println(person);
        }
    }

    private static Stream<Character> seprateStringToCS(String str) {
        List<Character> characters = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            characters.add(chars[i]);
        }
        return characters.stream();
    }
}

