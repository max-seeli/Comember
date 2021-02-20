package com.project.comember;

import java.util.concurrent.Executor;

public class ThreadExecutor implements Executor {

    private static ThreadExecutor singletonThreadExecutor;

    Thread mThread;

    @Override
    public void execute(Runnable command) {
        if (this.mThread != null) {
            this.mThread.interrupt();
            while (this.mThread.isAlive())
                ;
        }
        this.mThread = new Thread(command);
        this.mThread.start();
    }

    public static ThreadExecutor getExecutor() {
        if (singletonThreadExecutor == null)
            singletonThreadExecutor = new ThreadExecutor();
        return singletonThreadExecutor;
    }
}
