package com.project.comember.misc;

import com.project.comember.ui.widgets.GameButton;

public class HighlightButtonRunnable implements Runnable {

    private final GameButton mGameButton;
    private final int mHighlightMillis;
    private final int mHighlightPauseMillis;

    public HighlightButtonRunnable(GameButton gameButton, int highlightMillis, int highlightPauseMillis) {
        mGameButton = gameButton;
        mHighlightMillis = highlightMillis;
        mHighlightPauseMillis = highlightPauseMillis;
    }

    @Override
    public void run() {
        try {
            highlightPhase();
            unhighlightPhase();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private void highlightPhase() throws InterruptedException {
        mGameButton.highlight();
        Thread.sleep(mHighlightMillis);
    }

    private void unhighlightPhase() throws InterruptedException {
        mGameButton.unhighlight();
        Thread.sleep(mHighlightPauseMillis);
    }

    private void cleanup() {
        mGameButton.unhighlight();
    }


}
