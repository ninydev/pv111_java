package com.itstep.mythread;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

public class MyBackgroundTask implements Runnable  {

    private boolean isPause = false;

    public void setPause(boolean pause) {
        isPause = pause;
    }

    private boolean isCancel = false;

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    private final MainActivity activity;

    private static final int sleep = 513;

    private Object data;

    /**
     * Данные, без которых выполнение фоновой задачи бессмысленно
     * @param data
     */
    public MyBackgroundTask(MainActivity activity, Object data) {
        this.activity = activity;
        this.data = data;
    }

    private int start;
    private int finish;

    public MyBackgroundTask(MainActivity activity, int start, int finish) throws Exception {
        this.activity = activity;
        if(start > finish) {
            throw  new Exception(" Out of Range");
        }

        this.start = start;
        this.finish = finish;
        this.current = start;
    }

    private int current;

    public int getCurrent() {
        return current;
    }

    /**
     * Точка входа в фоновую задачу
     */
    @Override
    public void run() {
        execution();
    }

    /**
     * Сама полезная нагрузка
     */
    private void execution() {
        for (int i = this.start; i < this.finish; i++) {
            try {
                current++;
                Log.d("keeper ", " " + current);
                echo();

                do {
                    Thread.sleep(sleep);
                    if (isCancel) {
                        return;
                    }
                } while (isPause);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void echo() throws Exception {
        if (this.activity == null) {
            throw new Exception("Context not found");
        }
        ProgressBar bar = activity.findViewById(R.id.progress_bar);
        if (bar == null) {
            throw new Exception("View not found");
        }
        bar.setProgress(current);
    }
}
