package com.project.comember;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class SequentialExecutor implements Executor {

    private static SequentialExecutor singletonSequentialExecutor;

    private Queue<Runnable> tasks = new ArrayDeque<>();
    //Executor executor = new Executor();
    private Runnable active;

    @Override
    public synchronized void execute(Runnable command) {
        tasks.add(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if((active = tasks.poll()) != null) {
//            executor.execute(active);
        }
    }

    public static SequentialExecutor getExecutor() {
        if (singletonSequentialExecutor == null)
            singletonSequentialExecutor = new SequentialExecutor();
        return singletonSequentialExecutor;
    }
}
