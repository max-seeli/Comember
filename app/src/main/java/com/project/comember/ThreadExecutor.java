package com.project.comember;

import java.util.concurrent.Executor;

public class ThreadExecutor implements Executor {

    private static ThreadExecutor singletonThreadExecutor;
    private static int typeLock;
    private static boolean typeLockChanged;

    Thread mThread;

    @Override
    public void execute(Runnable command) {
//        if (ThreadExecutor.typeLockChanged) {
//            this.mThread.interrupt();
//            this.mThread = null;
//        }
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

    public static ThreadExecutor getExecutor(int typeLock) {
        ThreadExecutor.typeLockChanged = ThreadExecutor.typeLock != typeLock;
        ThreadExecutor.typeLock = typeLock;
        return ThreadExecutor.getExecutor();
    }
}
