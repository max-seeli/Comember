package com.project.comember.util;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class FutureCallback {

    public FutureCallback(Future<?> future) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    future.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    futureFinished();
                }
            }
        }).start();

    }

    public abstract void futureFinished();
}
