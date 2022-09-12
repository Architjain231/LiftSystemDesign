package com.liftSystem.model;

public class Request extends Thread{
    private int moveFromFloor;
    private int moveToFloor;
    private LiftSystem liftSystem;
    public Request(int moveFromFloor, int moveToFloor,LiftSystem liftSystem) {
        this.moveFromFloor = moveFromFloor;
        this.moveToFloor = moveToFloor;
        this.liftSystem=liftSystem;
    }

    public int getMoveFromFloor() {
        return moveFromFloor;
    }

    public void setMoveFromFloor(int moveFromFloor) {
        this.moveFromFloor = moveFromFloor;
    }

    public int getMoveToFloor() {
        return moveToFloor;
    }

    public void setMoveToFloor(int moveToFloor) {
        this.moveToFloor = moveToFloor;
    }
    @Override
    public void run()
    {
        try {
            liftSystem.processRequest(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
