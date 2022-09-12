package com.liftSystem.model;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LiftInventory
{
    private TreeSet<Integer> requestQueueForUpToBeProcessedNow;
    private TreeSet<Integer> requestQueueForUpToBeProcessedLater;
    private TreeSet<Integer> requestQueueForDownToBeProcessedNow;
    private TreeSet<Integer> requestQueueForDownToBeProcessedLater;
    LiftInventory()
    {
        init();
    }
    private void init()
    {
        requestQueueForUpToBeProcessedNow=new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        requestQueueForDownToBeProcessedNow=new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        requestQueueForUpToBeProcessedLater=new TreeSet<>();
        requestQueueForDownToBeProcessedLater=new TreeSet<>();
    }

    public TreeSet<Integer> getRequestQueueForUpToBeProcessedNow() {
        return requestQueueForUpToBeProcessedNow;
    }

    public TreeSet<Integer> getRequestQueueForUpToBeProcessedLater() {
        return requestQueueForUpToBeProcessedLater;
    }

    public TreeSet<Integer> getRequestQueueForDownToBeProcessedNow() {
        return requestQueueForDownToBeProcessedNow;
    }

    public TreeSet<Integer> getRequestQueueForDownToBeProcessedLater() {
        return requestQueueForDownToBeProcessedLater;
    }
}
