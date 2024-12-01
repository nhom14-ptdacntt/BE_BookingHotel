package com.bookingHotel.nhom14.core;

import com.bookingHotel.nhom14.core.util.Logger;

public class alo {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Logger.DebugLogic("nhin gi", new Exception("alo"));

        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + i);
        } 
 }
}
