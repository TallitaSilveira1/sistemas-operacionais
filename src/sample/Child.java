package sample;

import java.util.concurrent.Semaphore;

public class Child extends Thread {
    private int id;
    private boolean ball;
    private int playing_time;
    private int quiet_time;
    private Semaphore spaces;
    private Semaphore items;
    private Semaphore mutex;

    public Child(int id, boolean ball, int playing_time, int quiet_time) {
        this.id = id;
        this.ball = ball;
        this.playing_time = playing_time;
        this.quiet_time = quiet_time;
        this.spaces = spaces;
        this.items = items;
        this.mutex = mutex;
    }

    public void ballGet () throws InterruptedException {//consumidor

        items.acquire();
        mutex.acquire();
        mutex.release();
        spaces.release();

    }

    public void ballBack () throws InterruptedException { //produtor

        spaces.acquire();
        mutex.acquire();
        mutex.release();
        items.release();

    }


    public void run () {
    }

    public long getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBall() {
        return ball;
    }

    public void setBall(boolean ball) {
        this.ball = ball;
    }

    public int getPlaying_time() {
        return playing_time;
    }

    public void setPlaying_time(int joking_time) {
        this.playing_time = joking_time;
    }

    public int getQuiet_time() {
        return quiet_time;
    }

    public void setQuiet_time(int quiet_time) {
        this.quiet_time = quiet_time;
    }
}
