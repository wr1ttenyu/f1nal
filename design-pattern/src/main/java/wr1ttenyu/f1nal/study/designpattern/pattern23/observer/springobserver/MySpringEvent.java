package wr1ttenyu.f1nal.study.designpattern.pattern23.observer.springobserver;

import org.springframework.context.ApplicationEvent;

public class MySpringEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(Object source) {
        super(source);
    }

    public void println() {
        System.out.println("my spring application event print .... source: " + (String) source);
    }
}
