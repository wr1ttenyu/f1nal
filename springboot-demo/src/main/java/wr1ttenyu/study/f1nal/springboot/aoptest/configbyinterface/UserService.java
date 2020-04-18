package wr1ttenyu.study.f1nal.springboot.aoptest.configbyinterface;

public interface UserService {

    User createUser(String name,Integer age);

    User getUser(String name);
}
