package com.askar.matrix.main;

import com.askar.matrix.entity.Changer;
import com.askar.matrix.report.Report;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Report report = new Report();
        Semaphore semaphore = new Semaphore(1);

        new Thread(new Changer("1", 0, semaphore)).start();
        new Thread(new Changer("2", 1, semaphore)).start();
        new Thread(new Changer("3", 2, semaphore)).start();
        new Thread(new Changer("4", 3, semaphore)).start();
        new Thread(new Changer("5", 4, semaphore)).start();


        Thread.sleep(1000);
        report.printMatrix();
    }
}
