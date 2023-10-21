/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HBCSim.events;

import hbcsimulator.HBC3;
import hbcsimulator.PhoneEnquirer;
import executive.Executive;
import SimObs.Activity;
import SimObs.TraceFile;

/**
  * The second C, begin serve, representing the start of the service of a
  * phoner caller.
  * Checks whether calls are waiting in the queue and a clerk is free, if so
  * reduces the number of free clerks by 1 and schedules the conversation to end, using
  * the distribution set up in the PhoneEnq class. Writes to the tracefile.
  */

public class BeginTalk implements Activity {
    public void doThisNow() {
      int phoneConvNum;
      PhoneEnquirer thisCall;
      while ((PhoneEnquirer.phoneEnquirersWaitingForService.size() > 0)&&(HBC3.clerks.getFreeAmount() > 0)){
        Executive.setCStarted();
        thisCall = (PhoneEnquirer)PhoneEnquirer.phoneEnquirersWaitingForService.takeFromHead();
        Executive.schedule(thisCall, PhoneEnquirer.serviceOver, PhoneEnquirer.getServiceTime());
        phoneConvNum = PhoneEnquirer.phoneEnquirersWaitingForService.getNumRemoved();
        HBC3.clerks.commitFreeResource(1);
        TraceFile.traceFile.println("Conversation " + phoneConvNum + " starts at " + Executive.getCurrentClockTime()
                        + " clerks free: " + HBC3.clerks.getAmount());
          System.out.println("Conversation " + phoneConvNum + " starts at " + Executive.getCurrentClockTime()
                        + " clerks free: " + HBC3.clerks.getAmount());
      }
    }
    
    @Override
    public String toString() {
        return "Begin Talking";
    }
}
