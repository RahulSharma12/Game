package com.example.truegames.Util;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

import com.example.truegames.MainActivity;
import com.example.truegames.R;

public class Utility {

    public static Utility utility = null;

    public static String APP_PREFERENCES = "truegames";

    public boolean isFireButtonPressed() {
        return isFireButtonPressed;
    }

    public void setFireButtonPressed(boolean fireButtonPressed) {
        isFireButtonPressed = fireButtonPressed;
    }

    public boolean isFireButtonPressed = false;

    private Utility() {
    }

    ;

    public synchronized static Utility getInstance() {
        if (utility == null) {
            utility = new Utility();
        }
        return utility;
    }


    static MediaPlayer mediaPlayer;

    public void startWelcomeNote(Context context, int id) {
        synchronized (context) {
            AccessibilityManager manager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
            if (manager.isEnabled()) {

                AccessibilityEvent e = AccessibilityEvent.obtain();
                e.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
                e.getText().add(context.getString(id));

                //There may be other things you need to add like class/packagename, I'm doing this from memory on my non-dev machine, so if this isn't quite right I apologize, I promise it's super close! :)

                manager.sendAccessibilityEvent(e);
            }
        }
    }

    public void startWelcomeNoteInStringFormat(Context context, String value) {
        synchronized (context) {
            AccessibilityManager manager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
            if (manager.isEnabled()) {

                AccessibilityEvent e = AccessibilityEvent.obtain();
                e.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
                if (value.equalsIgnoreCase("")) {
                    e.getText().add("Walk Not Started");
                }else{
                    e.getText().add(value);
                }

                //There may be other things you need to add like class/packagename, I'm doing this from memory on my non-dev machine, so if this isn't quite right I apologize, I promise it's super close! :)

                manager.sendAccessibilityEvent(e);
            }
        }
    }


    public void startMediaPlayerProperties(int val, Context context, boolean loopedin) {
        if (mediaPlayer != null && !MainActivity.isBackroundMusicSelected) {
            Log.e("mediaPlayer","stop startMediaPlayerProperties");
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        switch (val) {
            case 1:
                mediaPlayer = MediaPlayer.create(context, R.raw.game_theme);
                break;

            case 2:
                mediaPlayer = MediaPlayer.create(context, R.raw.snippershot);

                break;

            case 3:
                mediaPlayer = MediaPlayer.create(context, R.raw.footsteps);

                break;

            case 4:
                mediaPlayer = MediaPlayer.create(context, R.raw.reloadweapon);

                break;

            case 5:
                mediaPlayer = MediaPlayer.create(context, R.raw.machineweaponinitiated);

                break;

            case 6:
                mediaPlayer = MediaPlayer.create(context, R.raw.shortgunweapon);

                break;

            case 7:
                mediaPlayer = MediaPlayer.create(context, R.raw.concreteland);

                break;

            case 8:
                mediaPlayer = MediaPlayer.create(context, R.raw.surfacefootsteps);

                break;

            case 9:
                mediaPlayer = MediaPlayer.create(context, R.raw.warningsound);

                break;

            case 10:
                mediaPlayer = MediaPlayer.create(context, R.raw.background_music);

                break;

            case 11:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb1);

                break;

            case 12:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb2);

                break;

            case 13:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb3);

                break;

            case 14:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb4);

                break;

            case 15:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb5);

                break;

            case 16:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb6);

                break;

            case 17:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb7);

                break;

            case 18:
                mediaPlayer = MediaPlayer.create(context, R.raw.amb8);

                break;

        }
        mediaPlayer.setVolume(1f, 1f);
        mediaPlayer.start();

    }

    public void startInLoop() {
        mediaPlayer.setLooping(true);
    }

    public void stopMediaPlayer() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                Log.e("mediaPlayer","stop");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
