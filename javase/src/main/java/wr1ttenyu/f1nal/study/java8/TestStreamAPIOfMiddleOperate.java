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
public class TestStreamAPIOfMiddleOperate {

    private static List<Person> personList;

    static {
        personList = new ArrayList<>();
        personList.add(new Person("wr1ttenyu"));
        personList.add(new Person("licongrong"));
        personList.add(new Person("zhaowanli"));
        personList.add(new Person("wwy"));
        personList.add(new Person("money"));
    }

    @Test
    public void testMiddleOperateMap() {
        personList.stream().map(person -> {
            person.setName(person.getName() + "1");
            return person;
        });

        String[] strs = {"aaa", "bbb", "ccc", "ddd"};
        Arrays.stream(strs).map(TestStreamAPIOfMiddleOperate::seprateStringToCS);
        // TODO continue

        Arrays.stream(strs).flatMap(TestStreamAPIOfMiddleOperate::seprateStringToCS);
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

