package com.example.truegames.Util;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.example.truegames.CreateMapActivity;
import com.example.truegames.MainActivity;
import com.example.truegames.R;

import java.util.Locale;

public class TTSManager implements TextToSpeech.OnUtteranceCompletedListener, TextToSpeech.OnInitListener {

    TextToSpeech textToSpeech;
    String message = "";

    public TTSManager(Context context, final String message) {
        this.message = message;
        textToSpeech = new TextToSpeech(context, this);

    }

    public void playSpeatch(String text, int success) {
        if (success == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(Locale.US);
            Bundle bundle = new Bundle();
            textToSpeech.setPitch(1.0f); //Normal Pitch
            textToSpeech.setSpeechRate(1.0f); // 1.0 is Normal speech Rate
            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC);
            Log.e("rotationalDirection", "text:: " +text);
            if (text.equalsIgnoreCase("welcome")) {
                textToSpeech.speak("Welcome to True Games.", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("login")) {
                textToSpeech.speak("Instruction for Login", TextToSpeech.QUEUE_FLUSH, bundle, null);

            } else if (text.equalsIgnoreCase("onDoubleTapEvent")) {
                textToSpeech.speak("Welcome to Facebook Login", TextToSpeech.QUEUE_FLUSH, bundle, null);

            } else if (text.equalsIgnoreCase("Menu")) {
                textToSpeech.speak("Follow the instructions for application menu. Choose option for login through login option & facebook option", TextToSpeech.QUEUE_FLUSH, bundle, null);

            } else if (text.equalsIgnoreCase("Walk")) {
                textToSpeech.speak("Walk Started", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("Target")) {
                textToSpeech.speak("Target", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("MainScreen")) {
                textToSpeech.speak("User Login Successful Player 1. To Walk slide left and right.Bottom of screen press circle button to shot", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("Machine Gun")) {
                textToSpeech.speak("Machine Gun", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("Short Gun")) {
                textToSpeech.speak("Short Gun", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("Rifle")) {
                textToSpeech.speak("Rifle", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("normal_login")) {
                textToSpeech.speak("Login Button is selected. Double Tap to use", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("fb_login")) {
                textToSpeech.speak("FB Login Button is selected. Double Tap to use", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("login_selected")) {
                textToSpeech.speak("Login Button pressed", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("fb_selected")) {
                textToSpeech.speak("FB Login Button pressed", TextToSpeech.QUEUE_FLUSH, bundle, null);
            } else if (text.equalsIgnoreCase("coordinates")) {
                textToSpeech.speak("" + MapCalculations.getInstance().getCurrentCoordinate(), TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("Map Finished")) {
                textToSpeech.speak("Map Finished", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("No Tile")) {
                textToSpeech.speak("No Tile", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("Jump not possible less tiles")) {
                textToSpeech.speak("Jump not possible less tiles", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("Jump")) {
                textToSpeech.speak("Jump", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("shotGun")) {
                textToSpeech.speak("shotGun", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("rifle")) {
                textToSpeech.speak("rifle", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("matchineGun")) {
                textToSpeech.speak("matchineGun", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("onTwoFingerSwipeDown")) {
                validateDirection(bundle);
            }else if (text.equalsIgnoreCase("onTwoFingerSwipeUp")) {
                validateDirection(bundle);
            }else if (text.equalsIgnoreCase("CreateMap")){
                textToSpeech.speak("Create Map Choose X coordinates & Y Coordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("ycordinates")){
                textToSpeech.speak("Choose Y coordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("submit")){
                textToSpeech.speak("Submit coordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("xcordinates")){
                textToSpeech.speak("Choose X coordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("not Submit")){
                textToSpeech.speak("X Cordinate Should be less than Y coordinate", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("Selected Coordinate")){
                textToSpeech.speak("Cordinate Selected for x axis is" + CreateMapActivity.xValue + "and Y asis is" +CreateMapActivity.yValue, TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("not value")){
                textToSpeech.speak("No Y Cordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("success X")){
                textToSpeech.speak("Cordinate Selected for x axis is" + CreateMapActivity.xValue + "and Y asis is" +CreateMapActivity.yValue, TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("success Y")){
                textToSpeech.speak("Cordinate Selected for Y axis is" + CreateMapActivity.xValue + "and Y asis is" +CreateMapActivity.yValue, TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("x not value")){
                textToSpeech.speak("No X Cordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("y not value")){
                textToSpeech.speak("No Y Cordinates", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("mEnVSetup")){
                textToSpeech.speak("Swipe left and right for choosing sounds.", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("createMap_selected")){
                textToSpeech.speak("create Map selected", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("exit")){
                textToSpeech.speak("Exit application selected", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("weapons")){
                textToSpeech.speak("weapon selected", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }else if (text.equalsIgnoreCase("reload")){
                textToSpeech.speak("reload selected", TextToSpeech.QUEUE_FLUSH, bundle, null);
            }

            textToSpeech.setOnUtteranceCompletedListener(this);
        }
    }

    private void validateDirection(Bundle bundle) {
        Log.e("rotationalDirection", "validateDirection:: " +MainActivity.directionCOunter);
        if (MainActivity.directionCOunter == 1) {
            textToSpeech.speak("Direction Turned North East", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 2){
            textToSpeech.speak("Direction Turned East", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 3){
            textToSpeech.speak("Direction Turned South East", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 4){
            textToSpeech.speak("Direction Turned South", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 5){
            textToSpeech.speak("Direction Turned South West", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 6){
            textToSpeech.speak("Direction Turned West", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 7){
            textToSpeech.speak("Direction Turned West North", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else if (MainActivity.directionCOunter == 0){
            textToSpeech.speak("Direction Turned North", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }else{
            textToSpeech.speak("Direction Turned North", TextToSpeech.QUEUE_FLUSH, bundle, null);
        }
    }


    @Override
    public void onUtteranceCompleted(String s) {
        Log.e("App", "completed");
    }

    @Override
    public void onInit(int i) {
        playSpeatch(message, i);
    }

    public void stopTTSSpeech() {
        textToSpeech.stop();

    }

    public boolean isPlayingTTS() {
        return textToSpeech.isSpeaking();

    }
}
