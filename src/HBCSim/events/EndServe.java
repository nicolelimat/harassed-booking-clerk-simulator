/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HBCSim.events;

import hbcsimulator.HBC3;
import hbcsimulator.PersonalCustomer;
import executive.Executive;
import SimObs.Activity;
import SimObs.TraceFile;

/**
 *
 * @author ivan
 */
public  class EndServe implements Activity {
    private PersonalCustomer persCust;

    public EndServe(PersonalCustomer persCust) {
        this.persCust = persCust;
    }
    
    
    
    public void doThisNow() {
      PersonalCustomer.personalCustomersServed.addToTail(Executive.getCurrentEntity()); // era chamado de getCurrentEvent
      TraceFile.traceFile.println("PersCust " + persCust.getCustomerID() + " leaves at " + Executive.getCurrentClockTime());
      System.out.println("PersCust " + persCust.getCustomerID() + " leaves at " + Executive.getCurrentClockTime());
      HBC3.clerks.makeResourceAvailable(1);
      Executive.destroyEntity(Executive.getCurrentEntity()); // era chamado de getCurrentEvent
    }

    @Override
    public String toString() {
        return "End serve toString()";
    }
    
    
  }
