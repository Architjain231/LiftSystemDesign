package com.liftSystem.model;
import com.liftSystem.enums.Direction;

public class Lift
{
    private int currentFloorNum;
    private static int DEFAULT_FLOOR_NUM=0;
    private static Direction DEFAULT_LIFT_DIRECTION=Direction.UP;
    private int liftNo;
    private LiftController controller;
    static int liftNumberAssigner=0;
    private Direction currentDirection;
    public Lift()
    {
        this(DEFAULT_FLOOR_NUM);
    }
    public Lift(int currentFloorNum)
    {
        this.currentFloorNum=currentFloorNum;
        liftNo=liftNumberAssigner;
        controller=new LiftController();
        liftNumberAssigner++;
        currentDirection=DEFAULT_LIFT_DIRECTION;
    }

    public int getCurrentFloorNum() {
        return currentFloorNum;
    }

    public int getLiftNo() {
        return liftNo;
    }

   public void moveTo(int fromFloorNum,int toFloorNum) throws InterruptedException {
       controller.processRequest(this,fromFloorNum,toFloorNum);
   }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentFloorNum(int currentFloorNum) {
        this.currentFloorNum = currentFloorNum;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
