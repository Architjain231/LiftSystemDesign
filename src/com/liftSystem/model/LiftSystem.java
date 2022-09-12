package com.liftSystem.model;

import com.liftSystem.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class LiftSystem {

    private final List<Lift> listOfLifts;
    private final LiftSystemController systemController;
    int numberOfLifts=0;
    static final int DEFAULT_NUM_OF_LIFTS=1;

    public LiftSystem()
    {
        this(DEFAULT_NUM_OF_LIFTS);
    }
    LiftSystem(int numberOfLifts)
    {
      this.numberOfLifts=numberOfLifts;
      systemController=new LiftSystemController();
        listOfLifts=new ArrayList<>();
        init();
    }

    public List<Lift> getListOfList() {
        return listOfLifts;
    }

    public LiftSystemController getSystemController() {
        return systemController;
    }
    public void processRequest(Request request) throws InterruptedException {
     systemController.processRequest(request,listOfLifts);
    }

   private void init()
   {
       for(int i=0;i<numberOfLifts;i++)
       {
           listOfLifts.add(new Lift());
       }
   }

}
