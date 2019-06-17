package com.askar.matrix.entity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Field {

    private int number;

    private Lock lock = lock = new ReentrantLock();

    private AtomicBoolean changed = new AtomicBoolean(false);

    public Field(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean getChanged() {
        return changed.get();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Lock getLock() {
        return lock;
    }

    public void setChanged(boolean changed) {
        this.changed.set(changed);
    }
}
