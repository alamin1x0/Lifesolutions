package com.lifesolutions.bd.Helpers;

public class RandomCallCheck {
    private static Boolean check = false;
    private static Boolean callRunning = false;

    public static void setCheck(Boolean c) {
        check = c;
    }

    public static Boolean getCheck() {
        return check;
    }

    public static Boolean getCallRunning() {
        return callRunning;
    }

    public static void setCallRunning(Boolean callRunning) {
        RandomCallCheck.callRunning = callRunning;
    }
}
