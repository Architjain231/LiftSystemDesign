package com.liftSystem.model;

import com.liftSystem.enums.Direction;

import java.util.TreeSet;

public class LiftController {
    private LiftInventory inventory;

    public LiftController() {
        inventory = new LiftInventory();
    }

    private void swapQueues(TreeSet<Integer> set1, TreeSet<Integer> set2) {
        TreeSet<Integer> t = set1;
        set1 = set2;
        set2 = t;
    }

    private void process(int liftCurrentFloor, int fromFloorNum, int toFloorNum, Direction liftCurrentDirection) {
        if (liftCurrentDirection == Direction.UP) {
            if (requestDirection(fromFloorNum, toFloorNum) == Direction.UP) {
                if (liftCurrentFloor < fromFloorNum) {
                    inventory.getRequestQueueForUpToBeProcessedNow().add(fromFloorNum);
                    inventory.getRequestQueueForUpToBeProcessedNow().add(toFloorNum);
                } else {
                    inventory.getRequestQueueForUpToBeProcessedLater().add(fromFloorNum);
                    inventory.getRequestQueueForUpToBeProcessedLater().add(toFloorNum);
                }
            } else {
                inventory.getRequestQueueForDownToBeProcessedNow().add(fromFloorNum);
                inventory.getRequestQueueForDownToBeProcessedNow().add(toFloorNum);
            }
        } else {
            if (requestDirection(fromFloorNum, toFloorNum) == Direction.UP) {
                inventory.getRequestQueueForUpToBeProcessedNow().add(fromFloorNum);
                inventory.getRequestQueueForUpToBeProcessedNow().add(toFloorNum);
            } else {
                if (liftCurrentFloor > fromFloorNum) {
                    inventory.getRequestQueueForDownToBeProcessedNow().add(fromFloorNum);
                    inventory.getRequestQueueForDownToBeProcessedNow().add(toFloorNum);
                } else {
                    inventory.getRequestQueueForDownToBeProcessedLater().add(fromFloorNum);
                    inventory.getRequestQueueForDownToBeProcessedLater().add(toFloorNum);
                }
            }
        }
    }

    public Direction requestDirection(int fromFloorNum, int toFloorNum) {
        if (fromFloorNum < toFloorNum)
            return Direction.UP;
        return Direction.DOWN;
    }

    private void move(Lift lift, int fromFloorNum, int toFloorNum) throws InterruptedException {

        TreeSet<Integer> upRequestQueue = inventory.getRequestQueueForUpToBeProcessedNow();
        lift.setCurrentDirection(Direction.UP);
        while (!upRequestQueue.isEmpty()&&lift.getCurrentFloorNum() > upRequestQueue.first()) {
            lift.setCurrentFloorNum(lift.getCurrentFloorNum() - 1);
            System.out.println("Lift No "+lift.getLiftNo()+" Current Floor : " + lift.getCurrentFloorNum());
            Thread.sleep(1500);
        }
        System.out.println("Lift No "+lift.getLiftNo()+" Current Floor : " + lift.getCurrentFloorNum());
        while (!upRequestQueue.isEmpty()) {
            int destinationFloor = upRequestQueue.pollFirst();
            while (lift.getCurrentFloorNum() < destinationFloor) {
                lift.setCurrentFloorNum(lift.getCurrentFloorNum() + 1);
                System.out.println("Lift No "+lift.getLiftNo()+" Current Floor : " + lift.getCurrentFloorNum());
                Thread.sleep(1500);
            }
            System.out.println("----------Gate Opened----------");
            Thread.sleep(1500);
        }
        swapQueues(inventory.getRequestQueueForUpToBeProcessedLater(), inventory.getRequestQueueForUpToBeProcessedNow());
        lift.setCurrentDirection(Direction.DOWN);
        TreeSet<Integer> downRequestQueue = inventory.getRequestQueueForDownToBeProcessedNow();
       boolean hasToPrint=false;
        while (!downRequestQueue.isEmpty()&&lift.getCurrentFloorNum() < downRequestQueue.first()) {
                lift.setCurrentFloorNum(lift.getCurrentFloorNum() + 1);
                System.out.println("Lift No "+lift.getLiftNo()+"Lift No "+lift.getLiftNo()+" Current Floor : " + lift.getCurrentFloorNum());
                Thread.sleep(1500);
                hasToPrint=true;
            }
        if(hasToPrint)
        System.out.println("Lift No"+lift.getLiftNo()+"Lift Current Floor : " + lift.getCurrentFloorNum());
        while (!downRequestQueue.isEmpty()) {
            int destinationFloor = downRequestQueue.pollFirst();
            while (lift.getCurrentFloorNum() > destinationFloor) {
                lift.setCurrentFloorNum(lift.getCurrentFloorNum() - 1);
                System.out.println(lift.getCurrentFloorNum());
                Thread.sleep(1500);
            }
            System.out.println("--------Gate Opened---------");
            Thread.sleep(1500);
        }
        System.out.println("Lift No "+lift.getLiftNo()+" Current Floor : " + lift.getCurrentFloorNum());
        swapQueues(inventory.getRequestQueueForDownToBeProcessedLater(), inventory.getRequestQueueForDownToBeProcessedNow());
    }

    public void processRequest(Lift lift, int fromFloorNum, int toFloorNum) throws InterruptedException {
        this.process(lift.getCurrentFloorNum(), fromFloorNum, toFloorNum, lift.getCurrentDirection());
        while (inventory.getRequestQueueForDownToBeProcessedLater().size() > 0 ||
                inventory.getRequestQueueForUpToBeProcessedLater().size() > 0 ||
                inventory.getRequestQueueForUpToBeProcessedNow().size() > 0 ||
                inventory.getRequestQueueForDownToBeProcessedNow().size() > 0) {
            move(lift, fromFloorNum, toFloorNum);
        }
    }
}
