package com.example.haeju.diary_music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by HAEJU on 2018-01-16.
 */

public class PlayerService extends Service {
    static boolean running;
    MediaPlayer mPlayer;
    String title;

    @Override
    public IBinder onBind(Intent intent) { return null;     }
    @Override
    public void onCreate() {
        mPlayer = new MediaPlayer();
        running = true;
    }
    @Override
    public void onDestroy() {
        mPlayer.stop();
        running = false;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        String audioPath = extras.getString("filename");
        title = extras.getString("title");
        try {
            mPlayer.setDataSource(audioPath);
            mPlayer.setLooping(false);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener(completionListener);
        } catch (Exception e) {
            running = false;
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener()  {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    running = false;
                    stopSelf();
                }
            };

    static public boolean isRunning() {
        return running;
    }
}