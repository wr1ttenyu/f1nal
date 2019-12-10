package wr1ttenyu.f1nal.study.designpattern.pattern23.facade;

public class HomeTheaterFacade {

    private DvdPlayer dvdPlayer;
    private Popcorn popcorn;
    private Projector projector;
    private Screen screen;
    private Stereo stereo;
    private TheaterLight theaterLight;

    public HomeTheaterFacade() {
        this.dvdPlayer = DvdPlayer.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.stereo = Stereo.getInstance();
        this.theaterLight = TheaterLight.getInstance();
    }

    // 家庭影院操作
    // 1. 准备步骤
    public void ready() {
        dvdPlayer.on();
        popcorn.on();
        popcorn.pop();
        projector.on();
        projector.project();
        projector.focus();
        screen.on();
        screen.down();
        stereo.on();
        stereo.up();
        // 灯光调暗
        theaterLight.dim();
    }

    public void play() {
        dvdPlayer.play();
    }

    public void pause() {
        dvdPlayer.pause();
    }

    public void end() {
        dvdPlayer.off();
        popcorn.off();
        projector.off();
        screen.up();
        screen.off();
        stereo.off();
        // 灯光调暗
        theaterLight.bright();
    }
}
