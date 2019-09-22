package sample;

import java.util.concurrent.Semaphore;

public class Child<quiet_time> extends Thread {
    private int id;
    private boolean ball;
    private int playing_time;
    private int quiet_time;
    private int timePlayingCounter;
    private int timeQuietCounter;
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

    public void ballBack(){
        if (spaces.availablePermits() == 0) {
            callBack.writeInLog("O cesto está cheio! " + id + " Bloqueada!\n");
            callBack.spacesFull(this);
        }
        try {
            spaces.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Basket.balls++;
        callBack.ballBack();
        mutex.release();
        items.release();
        callBack.writeInLog(this.id + "  colocou uma bola no cesto!\nBolas no cesto: " + Basket.balls + "\n");

    }

    public void ballGet() {
        if(items.availablePermits() == 0) {
            callBack.writeInLog("O cesto está sem bola! " + id + " Bloqueada!\n");
            callBack.noBall(this);
        }
        try {
            items.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Basket.balls--;
        callBack.ballGet();
        mutex.release();
        spaces.release();
        callBack.writeInLog(this.id + "  pegou uma bola do cesto!\nBolas no cesto: " + Basket.balls + "\n");
    }

    public void run () {
        while(true) {
            quiet_time = timeQuietCounter;
            playing_time = timePlayingCounter;
            if(ball) {
                mySleep(timePlayingCounter);
                ballBack();
                ball = false;
                callBack.quiet(this);
                mySleep(timeQuietCounter);
            } else {
                ballGet();
                callBack.playing(this);
                ball = true;
            }
            super.run();
        }
    }


    private void quiet(){
        for(int i = 0; i < quiet_time; timeQuietCounter--) {
        for (int j = 0; j < 40000; j++) {
            System.out.println(j);
            for (int k = 0; k < 100000; k++) {
                int x = 100/100 + k - j + i;
            }
        }
        callBack.method();
    }
}


    private void play() {
        for(int i = 0; i < playing_time; timePlayingCounter--) {
            for(int j = 0; j < 40000; j++) {
                System.out.println(j);
                for(int k = 0; k < 100000; k++) {
                    int x = 100/100 + k - j + i;
                }
            }
            callBack.method();
        }
    }

    private void mySleep(long tempo){
            if(ball) callBack.writeInLog(this.id + "  está brincando!\n");
            else callBack.writeInLog(this.id + "  está quieta\n");

            long atual = System.currentTimeMillis();
            long decorrido = 0, milisegundos = 0;
            while (decorrido < tempo) {
                milisegundos = (System.currentTimeMillis() - atual);
                if( (milisegundos / 1000) > decorrido) {
                    if(ball) timePlayingCounter--;
                    else timeQuietCounter--;
                    callBack.method();
                }
                if(ball && timePlayingCounter == 0) {
                    timePlayingCounter = playing_time;
                    break;
                }
                if(ball && timeQuietCounter == 0) {
                    timeQuietCounter = quiet_time;
                    break;
                }
                decorrido = milisegundos / 1000;
            }

            if(ball) callBack.writeInLog(this.id + "  terminou de brincar 😵\n");
            else callBack.writeInLog(this.id + "  não está mais quieta!\n");
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
