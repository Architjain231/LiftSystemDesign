package com.liftSystem.main;

import com.liftSystem.model.LiftSystem;
import com.liftSystem.model.Request;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        Scanner sc=new Scanner(System.in);
        LiftSystem liftSystem=new LiftSystem();
        Request r1=new Request(4,10,liftSystem);
        Request r2=new Request(6,10,liftSystem);
        Request r3=new Request(2,7,liftSystem);
        Request r4=new Request(9,1,liftSystem);
        Request r5=new Request(7,3,liftSystem);
        r1.start();
        Thread.sleep(2000);
        r2.start();
        Thread.sleep(2000);
        r3.start();
        Thread.sleep(2000);
        r4.start();
        Thread.sleep(2000);
        r5.start();

    }
}
