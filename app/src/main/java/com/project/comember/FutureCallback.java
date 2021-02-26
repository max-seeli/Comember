package com.project.comember;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class FutureCallback {

    public FutureCallback(Future<?> f) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    f.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    futureFinished();
                }
            }
        }).start();

    }

    public abstract void futureFinished();
}
