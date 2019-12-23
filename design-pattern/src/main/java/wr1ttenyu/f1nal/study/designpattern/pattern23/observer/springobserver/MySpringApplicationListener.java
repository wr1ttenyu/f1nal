package wr1ttenyu.f1nal.study.designpattern.pattern23.observer.springobserver;

import org.springframework.context.ApplicationListener;

public class MySpringApplicationListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.println("MySpringApplicationListener receive a event .... ");
        event.println();
    }
}
