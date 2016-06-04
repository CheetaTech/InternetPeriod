package com.mobiledatatimerwidget;

import android.widget.LinearLayout;


public class Config {

    private static Integer playerid;
    private static String username;
    private Integer sound;
    private Integer usedLetters;
    private Integer gameSelection;
    private Integer notifications;
    private Integer orientation;
    private Integer numGames;
    private Integer tutorial;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static boolean IS_TABLET = false;
    public static boolean IS_SMALLTABLET = false;
    public static boolean IS_LANDSCAPE = false;
    public static final boolean FREE_VERSION = false;
    public static final String appPackage = "com.google.android.apps.maps";
    public static boolean IS_HARDKEYBOARDAVAILABLE = false;
    public static LinearLayout.LayoutParams tabletButtonParams = new LinearLayout.LayoutParams(300, 75);
    public Config(){}


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Config.username = username;
    }




    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Integer getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(Integer usedLetters) {
        this.usedLetters = usedLetters;
    }

    public Integer getGameSelection() {
        return gameSelection;
    }

    public void setGameSelection(Integer gameSelection) {
        this.gameSelection = gameSelection;
    }

    public Integer getNotifications() {
        return notifications;
    }

    public void setNotifications(Integer notifications) {
        this.notifications = notifications;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public static Integer getPlayerid() {
        return playerid;
    }

    public static void setPlayerid(Integer playerid) {
        Config.playerid = playerid;
    }

    public Integer getNumGames() {
        return numGames;
    }

    public void setNumGames(Integer numGames) {
        this.numGames = numGames;
    }

    public static boolean isIS_HARDKEYBOARDAVAILABLE() {
        return IS_HARDKEYBOARDAVAILABLE;
    }

    public static void setIS_HARDKEYBOARDAVAILABLE(boolean iS_HARDKEYBOARDAVAILABLE) {
        IS_HARDKEYBOARDAVAILABLE = iS_HARDKEYBOARDAVAILABLE;
    }

    public Integer getTutorial() {
        return tutorial;
    }

    public void setTutorial(Integer tutorial) {
        this.tutorial = tutorial;
    }
}