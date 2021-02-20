package com.example.truegames;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.truegames.Util.IButtonSelected;
import com.example.truegames.Util.IMapDetails;
import com.example.truegames.Util.MapCalculations;
import com.example.truegames.Util.OnSwipeTouchListener;
import com.example.truegames.Util.TTSManager;
import com.example.truegames.Util.Utility;

import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, IMapDetails, IButtonSelected {
    boolean isSelected = true;
    TTSManager ttsManager;
    public static boolean isBackroundMusicSelected = false;
    boolean isFireButtonPressed, isWalkSTarted = false;
    AlertDialog.Builder builder;
    Object object = new Object();
    int counter = 0;
    boolean noTilesAvailable = false;
    TextView fire;
    int directionIncrement = 1;
    int selectedWeapon = 2;
    OnSwipeTouchListener onSwipeTouchListener;
    View view;
    int lastStoredValue = 0;
    public static int mXStartPoint,mXEndPoint = 0;
    public static int mYStartPoint,mYEndPoint = 0;
    public static int mAlltStartPoint,mAlltEndPoint = 0;
    Handler handler;
    int counterValue = 0;
    int selectedButton = 0;
    public static int lastknownDirection = 0;
    public static int directionCOunter = 0;
    public static boolean isRotationEventHappened = false;
    public static String rotationalDirection = "";
    int counterValAfterDoubleFinger = 0;
    long startTime= 0,stopTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = 0;
        MapCalculations.getInstance().getSharedPreferenceInstance(MainActivity.this);
        if (MapCalculations.getInstance().getTransactionSTate() == 0) {
            Utility.getInstance().stopMediaPlayer();
        }
        ttsManager = new TTSManager(this, "MainScreen");

        builder = new AlertDialog.Builder(this);
        handler = new Handler();
        onSwipeTouchListener = new OnSwipeTouchListener(this, this);
        view = (View) findViewById(R.id.view);
        view.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this, this, 0) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                OnSwipeTouchListener.value = 3;
                Utility.getInstance().startMediaPlayerProperties(7, MainActivity.this,true);
                lastStoredValue = 1;
                Log.e("Swipe", "" + 1);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                noTilesAvailable = false;
                OnSwipeTouchListener.value = 4;
                Utility.getInstance().startMediaPlayerProperties(7, MainActivity.this,true);
                ttsManager.stopTTSSpeech();
                lastStoredValue = 2;
                Log.e("Swipe", "" + 2);
            }


        });
        // Utility.getInstance().startWelcomeNote(this, R.string.login_successful);

        MapCalculations.getInstance().setMapDetails(this);
        // disableAccessibility(this);
        //view = (LinearLayout) findViewById(R.id.view);
        fire = (TextView) findViewById(R.id.fire);
        ImageButton weapons = (ImageButton) findViewById(R.id.weapons);
        ImageButton exit = (ImageButton) findViewById(R.id.exit);
        ImageButton createMap= (ImageButton) findViewById(R.id.createMap);
        TextView reload = (TextView) findViewById(R.id.reload);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isSelected = false;
            }
        }, 3000);
        // view.setOnTouchListener(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ttsManager.stopTTSSpeech();
                Utility.getInstance().setFireButtonPressed(true);
                isFireButtonPressed = true;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                        Utility.getInstance().stopMediaPlayer();
                    }
                    stopWalk();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (selectedWeapon == 1) {
                        Utility.getInstance().startMediaPlayerProperties(5, MainActivity.this,true);
                        Utility.getInstance().startInLoop();
                    }

                }

                if (selectedWeapon == 2) {
                    Utility.getInstance().startMediaPlayerProperties(2, MainActivity.this,true);
                } else if (selectedWeapon == 3) {
                    Utility.getInstance().startMediaPlayerProperties(6, MainActivity.this,true);
                }
                return true;
            }
        });
        reload.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this, this,4) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }
        });
        weapons.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this, this,3) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }
        });
        exit.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this, this,2) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }
        });

        createMap.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this, this,1) {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("map","x Start value: " + mXStartPoint + " x End value: " + mXEndPoint +  " y Start value: " + mYStartPoint +
                " y End value: " + mYEndPoint + "alltitude Start value: " + mAlltStartPoint + " alltitude End value: " + mAlltEndPoint);
       /* registerReceiver(walkStartedAction, new IntentFilter("com.walk.startPlaying"));
        registerReceiver(walkStartedAction, new IntentFilter("com.long.walk"));
        registerReceiver(walkStartedAction, new IntentFilter("com.long.walk.exit"));
        registerReceiver(walkStartedAction, new IntentFilter("com.long.jump"));*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        isFireButtonPressed = true;
        if (requestCode == 1001) {
            int message = data.getIntExtra("weaponSelected", 2);
            switch (message) {
                case 1: //
                    selectedWeapon = 1;
                    break;
                case 2: //
                    selectedWeapon = 2;
                    break;
                case 3: //
                    selectedWeapon = 3;
                    break;
            }
            if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                Utility.getInstance().stopMediaPlayer();
            }
            MapCalculations.getInstance().stop();
        }
    }

    public void stopWalk() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isFireButtonPressed = false;
            }
        }, 1000);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override
    protected void onPause() {
        if (MapCalculations.getInstance().getTransactionSTate() == 0) {
            Utility.getInstance().stopMediaPlayer();
        }
        ttsManager.stopTTSSpeech();
        super.onPause();
        Log.e("flow", "onPause");
        counter = 0;
    }

    @Override
    protected void onStop() {
        if (MapCalculations.getInstance().getTransactionSTate() == 0) {
            Utility.getInstance().stopMediaPlayer();
        }
        super.onStop();
        Log.e("flow", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("flow", "onDestroy");
    }

    int statusValue = 0;

    @Override
    public void coordinatesStatus(int status) {
        Log.e("App", "coordinatesStatus " + statusValue);
        noTilesAvailable = true;
        statusValue++;
        if (statusValue == 1) {
            if (status == 0) {
                ttsManager = new TTSManager(MainActivity.this, "Map Finished");
            } else if (status == 1) {
                ttsManager = new TTSManager(MainActivity.this, "No Tile");
            } else if (status == 2) {
                ttsManager = new TTSManager(MainActivity.this, "Jump not possible less tiles");
            }

            MapCalculations.getInstance().stop();
            if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                Utility.getInstance().stopMediaPlayer();
            }
        }
    }

    @Override
    public void selectedState(int value, int selectedButtonChoose) {
        Log.e("state" , " " +selectedButtonChoose);
       /* if (selectedButtonChoose != 0){
            return;
        }*/
        switch (value) {
            case 1:
                Log.e("swipe", "1");
                if (selectedButtonChoose == 1){
                    Intent intent=new Intent(MainActivity.this,CreateMapActivity.class);
                    startActivity(intent);
                }else if (selectedButtonChoose == 2){
                    finishAffinity();
                    System.exit(0);
                }else if (selectedButtonChoose == 3){
                    Intent intent = new Intent(MainActivity.this, WeaponActivity.class);
                    startActivityForResult(intent, 1001);
                    stopWalk();
                }else if (selectedButtonChoose == 4){
                    ttsManager.stopTTSSpeech();
                    Utility.getInstance().startMediaPlayerProperties(4, MainActivity.this,true);
                    stopWalk();
                }else  if (selectedButtonChoose == 0 || selectedButtonChoose == 5){
                    noTilesAvailable = false;
                    ttsManager.stopTTSSpeech();
                    MapCalculations.getInstance().startWalking(directionIncrement, true);
                    ttsManager = new TTSManager(MainActivity.this, "Jump");
                    ttsManager.stopTTSSpeech();
                    if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                        Utility.getInstance().stopMediaPlayer();
                    }
                    MapCalculations.getInstance().stop();
                }
                break;

            case 2:
                Log.e("Swipe", "" + 5);
                break;

            case 3:
                Log.e("Swipe", "" + 3);
                // currentState=2;
                // btnFbLogin.setFocusable(View.FOCUSABLE);
                break;

            case 4:
                Log.e("Swipe", "" + 4);
                // currentState=3;
                // btnFbLogin.setFocusable(View.FOCUSABLE);
                break;

            case 5:
                counterValue++;
                statusValue = 0;
                ttsManager.stopTTSSpeech();
                Log.e("swipe", "5");
                if (counterValue == 1) {
                    handler.postDelayed(timercheck, 100);
                }

                Log.e("Swipe", "" + 5);
                // currentState=3;
                // btnFbLogin.setFocusable(View.FOCUSABLE);
                break;
            case 6:
                Log.e("swipe", "stopMediaPlayer");
                counterValue = 0;
                statusValue = 0;
                if (selectedButton != 4) {
                    if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                        Utility.getInstance().stopMediaPlayer();
                    }
                }
                handler.removeCallbacks(timercheck);
                stopTime = System.currentTimeMillis();
                long interval= stopTime - startTime;
                countervalues = 0;
                Log.e("rotationalDirection", "Time Interval: " +interval);
                if (!isRotationEventHappened) {
                  //  ttsManager = new TTSManager(MainActivity.this, "coordinates");
                }
                Log.e("rotationalDirection", " " + isRotationEventHappened + "  " + directionCOunter + " counterValAfterDoubleFinger: " +counterValAfterDoubleFinger);
                if (interval > 130) {
                    MainActivity.isRotationEventHappened = false;
                    counterValAfterDoubleFinger = 0;
                    Log.e("rotationalDirection", " " +isRotationEventHappened);
                    countervalues = 0;
                    if (selectedButtonChoose == 0) {
                        ttsManager = new TTSManager(MainActivity.this, "coordinates");
                    }
                }

                break;

            case 7:
                if (directionCOunter == 0) {
                    directionCOunter = 8;
                }
                //onTwoFingerSwipeUp
                if (directionCOunter > 0) {
                    directionCOunter--;
                }
                startTime =System.currentTimeMillis();
                counterValAfterDoubleFinger++;
                ttsManager = new TTSManager(MainActivity.this, "onTwoFingerSwipeUp");
                isRotationEventHappened = true;
                rotationalDirection = "onSwipeUp";
                handler.removeCallbacks(timercheck);
                Log.e("rotationalDirection", "7 ");
                Log.e("swipe", "timercheck " + MapCalculations.getInstance().getCurrentCoordinate());
                break;

            case 8:
                //onTwoFingerSwipeDown
                if (directionCOunter < 8) {
                    directionCOunter++;
                }
                startTime =System.currentTimeMillis();
                counterValAfterDoubleFinger++;
                isRotationEventHappened = true;
                rotationalDirection = "onSwipeDown";
                handler.removeCallbacks(timercheck);
                Log.e("rotationalDirection", "8");
                ttsManager = new TTSManager(MainActivity.this, "onTwoFingerSwipeDown");
                break;

            case 9:
                //onTwoFingerSwipeDown
                if (selectedButtonChoose == 0) {
                    ttsManager.stopTTSSpeech();
                    selectedButton = 5;
                }else if (selectedButtonChoose == 1) {
                    if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                        Utility.getInstance().stopMediaPlayer();
                    }
                    MapCalculations.getInstance().stop();
                    ttsManager.stopTTSSpeech();
                    ttsManager = new TTSManager(MainActivity.this, "createMap_selected");
                    selectedButton = 1;
                }else if (selectedButtonChoose == 2){
                    isFireButtonPressed = true;
                    if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                        Utility.getInstance().stopMediaPlayer();
                    }
                    selectedButton = 2;
                    ttsManager.stopTTSSpeech();
                    ttsManager = new TTSManager(MainActivity.this, "exit");
                    MapCalculations.getInstance().stop();
                }else if (selectedButtonChoose == 3){
                    ttsManager.stopTTSSpeech();
                    isFireButtonPressed = true;
                    ttsManager.stopTTSSpeech();
                    selectedButton = 3;
                    ttsManager = new TTSManager(MainActivity.this, "weapons");
                }
                else if (selectedButtonChoose == 4){
                    ttsManager.stopTTSSpeech();
                    isFireButtonPressed = true;
                    noTilesAvailable = false;
                    selectedButton = 4;
                    ttsManager = new TTSManager(MainActivity.this, "reload");
                }
        }
    }
    private int countervalues = 0;
    private Runnable timercheck = new Runnable() {
        @Override
        public void run() {
            Log.e("swipe", "timercheck " + MapCalculations.getInstance().getCurrentCoordinate());
            if (MapCalculations.getInstance().getTransactionSTate() == 0) {
                Utility.getInstance().stopMediaPlayer();
            }
            Utility.getInstance().startMediaPlayerProperties(7, MainActivity.this,true);
            MapCalculations.getInstance().startWalking(OnSwipeTouchListener.value, false);
           // Utility.getInstance().startInLoop();
            handler.postDelayed(timercheck, 200);
            countervalues++;
            Log.e("timercheck","count: " +countervalues);
        }
    };

}
