package com.liftSystem.model;

import com.liftSystem.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class LiftSystemController
{
    public void processRequest(Request request, List<Lift> listOfLifts) throws InterruptedException {
        Direction requestDirection=findDirection(request);
        Lift bestSuitLift= findBestLift(requestDirection, request.getMoveFromFloor(),listOfLifts);
        System.out.println("Go to Lift No. : "+bestSuitLift.getLiftNo());
        System.out.println("Thats current Floor is : "+bestSuitLift.getCurrentFloorNum());
        bestSuitLift.moveTo(request.getMoveFromFloor(), request.getMoveToFloor());
    }
    private Lift findBestLift(Direction requestDirection , int moveFromFloor, List<Lift> listOfLifts)
    {
        int minDistance=Integer.MAX_VALUE;
        Lift desiredLift=null;
        for(Lift lift:listOfLifts)
        {
            if(lift.getCurrentDirection()==requestDirection)
            {
                if(requestDirection==Direction.UP&&lift.getCurrentFloorNum()<moveFromFloor) {
                    if (minDistance > Math.abs(lift.getCurrentFloorNum() - moveFromFloor)) {
                        minDistance = Math.abs(lift.getCurrentFloorNum() - moveFromFloor);
                        desiredLift = lift;
                    }
                }
                else if(requestDirection==Direction.DOWN&&lift.getCurrentFloorNum()>moveFromFloor)
                {
                    if (minDistance > Math.abs(lift.getCurrentFloorNum() - moveFromFloor)) {
                        minDistance = Math.abs(lift.getCurrentFloorNum() - moveFromFloor);
                        desiredLift = lift;
                    }
                }
            }
        }
        if(desiredLift==null)
        {
            desiredLift=listOfLifts.get(0);
        }
        return desiredLift;
    }

    private Direction findDirection(Request request)
    {
        if(request.getMoveFromFloor()< request.getMoveToFloor())
            return Direction.UP;
        return Direction.DOWN;
    }
}
