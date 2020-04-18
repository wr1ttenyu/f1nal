package wr1ttenyu.study.f1nal.springboot.aoptest.configbyinterface;

public class UserServiceImpl implements UserService{

    private static User user = null;

    @Override
    public User createUser(String name, Integer age) {
        user = new User(name, age);
        return user;
    }

    @Override
    public User getUser(String name) {
        return user;
    }
}
