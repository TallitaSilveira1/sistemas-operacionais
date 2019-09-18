package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Semaphores {
    private Semaphore mutex;
    private Semaphore items;
    private Semaphore spaces;
    List<Child> pirralhos;

    public Semaphores(int nBolas) {
        mutex = new Semaphore(1);
        items = new Semaphore(0);
        spaces = new Semaphore(nBolas);
        pirralhos = new ArrayList<>();
    }

    public void addChild(Child pirralho){
        pirralhos.add(pirralho);
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public Semaphore getItems() {
        return items;
    }

    public void setItems(Semaphore items) {
        this.items = items;
    }

    public Semaphore getSpaces() {
        return spaces;
    }

    public void setSpaces(Semaphore spaces) {
        this.spaces = spaces;
    }
}
