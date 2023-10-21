/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HBCSim.events;

import hbcsimulator.PersonalCustomer;
import executive.Executive;
import SimObs.Activity;
import SimObs.TraceFile;

/**
 *
 * @author ivan
 */
public class Arrive implements Activity {
    private PersonalCustomer persCust;

    public Arrive(PersonalCustomer persCust) {
        this.persCust = persCust;
    }
    
    
    
    public void doThisNow() {
      PersonalCustomer.personalCustomersWaitingForService.addToTail(Executive.getCurrentEntity()); // era chamado de getCurrentEvent
      PersonalCustomer.personalCustomersWaitingForService.show();
      TraceFile.traceFile.println("PersCust " + persCust.getCustomerID() + " arrives at " + Executive.getCurrentClockTime());
      System.out.println("PersCust " + persCust.getCustomerID() + " arrives at " + Executive.getCurrentClockTime());
      PersonalCustomer nextCust = new PersonalCustomer("PersCust");
      Executive.addNewEntity(nextCust);
      Executive.schedule(nextCust, PersonalCustomer.nextArrivalEvent, persCust.interArrivalTime.getSampleFromNegativeExponentialDistribution(PersonalCustomer.meanIntervalArrivalTime));
      nextCust.writeEntityOnTraceFile();
    }

    @Override
    public String toString() {
        return "Arrive.toString()";
    }
    
    
  }
