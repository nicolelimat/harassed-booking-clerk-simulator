/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HBCSim.events;

import hbcsimulator.HBC3;
import hbcsimulator.PersonalCustomer;
import executive.Executive;
import SimObs.Activity;
import SimObs.Resource;
import SimObs.TraceFile;

/**
 *
 * @author ivan
 */
/**
* The first C, begin serve, representing the start of the service of a
* personal customer.
* Checks whether customers are waiting in the queue and a clerk is free, if so
* reduces the number of free clerks by 1 and schedules the service to end, using
* the distribution set up in the PersCust class. Writes to the tracefile.
*/
  public class BeginService implements Activity {
    public void doThisNow(){
      int persServeNum;
      PersonalCustomer thisCust;
      while ((PersonalCustomer.personalCustomersWaitingForService.size() > 0)&&(HBC3.clerks.getFreeAmount() > 0)){
        Executive.setCStarted();
        thisCust = (PersonalCustomer)PersonalCustomer.personalCustomersWaitingForService.takeFromHead();
        Executive.schedule(thisCust, PersonalCustomer.serviceOver, PersonalCustomer.getServiceTime());
        persServeNum = PersonalCustomer.personalCustomersWaitingForService.getNumRemoved();
        HBC3.clerks.commitFreeResource(1);
        TraceFile.traceFile.println("Service " + persServeNum + " starts at " + Executive.getCurrentClockTime()
                        + " clerks free: " + HBC3.clerks.getAmount());
          System.out.println("Service " + persServeNum + " starts at " + Executive.getCurrentClockTime()
                        + " clerks free: " + HBC3.clerks.getAmount());
      }
    }

    @Override
    public String toString() {
        return "Begin Service";
    }
    
    
  }
