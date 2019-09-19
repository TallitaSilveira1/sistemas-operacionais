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
    private ChildCallBack callBack;

    public Child(int id, boolean ball, int playing_time, int quiet_time, Semaphore spaces, Semaphore items, Semaphore mutex) {
        this.id = id;
        this.ball = ball;
        this.playing_time = playing_time;
        this.quiet_time = quiet_time;
        this.spaces = spaces;
        this.items = items;
        this.mutex = mutex;
    }

    public void ballGet () throws InterruptedException {//consumidor
        if(items.availablePermits() == 0) {
            System.out.println("Bloqueado sem bola no cesto!");
        }
        items.acquire();
        mutex.acquire();
        Basket.balls--;
        mutex.release();
        spaces.release();
    }

    public void ballBack () throws InterruptedException { //produtor
        if(spaces.availablePermits() == 0) {
            System.out.println("Bloqueado cesto cheio!");
        }
        spaces.acquire();
        mutex.acquire();
        Basket.balls++;
        mutex.release();
        items.release();
    }

    public void run () {
        while (true) {
            if(ball) {
                callBack.writeInLog("Criança vai brincar!\n");
                play();
                try {
                    ballBack();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ball = false;
                quiet();
            } else {
                try {
                    ballGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ball = true;
            }
        }
    }

    private void quiet() {
        for(int i = 0; i < quiet_time * 10000; i++) {
            for(int j = 0; j < 100000; j++) {
                for(int k = 0; k < 49999 + 1; k++) {
                    int x = k*299 + j/899 + i/199;
                }
                System.out.println(j);
            }
        }
    }

    private void play() {
        for(int i = 0; i < playing_time * 10000; i++) {
            for(int j = 0; j < 100000; j++) {
                for(int k = 0; k < 49999 + 1; k++) {
                    int x = k*299 + j/899 + i/199;
                }
                System.out.println(j);
            }
        }
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

    public Semaphore getSpaces() {
        return spaces;
    }

    public void setSpaces(Semaphore spaces) {
        this.spaces = spaces;
    }

    public Semaphore getItems() {
        return items;
    }

    public void setItems(Semaphore items) {
        this.items = items;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public ChildCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ChildCallBack callBack) {
        this.callBack = callBack;
    }
}
