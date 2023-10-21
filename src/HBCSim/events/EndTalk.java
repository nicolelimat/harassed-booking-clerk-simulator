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
 *
 * @author ivan
 */
/**
  * Implements the Activity interface to establish a B that simulates the
  * end of a phone conversation. Adds the phone call entity to the tail
  * of QOut and releases the clerk.
  */
  public class EndTalk implements Activity {
     private PhoneEnquirer phoneEnq;

    public EndTalk(PhoneEnquirer phoneEnq) {
        this.phoneEnq = phoneEnq;
    }
     
     
    public void doThisNow() {
      PhoneEnquirer.phoneEnquirersServed.addToTail(Executive.getCurrentEntity());
      TraceFile.traceFile.println("PhoneEnq " + phoneEnq.getNumber() + " leaves at " + Executive.getCurrentClockTime());
        System.out.println("PhoneEnq " + phoneEnq.getNumber() + " leaves at " + Executive.getCurrentClockTime());
      HBC3.clerks.makeResourceAvailable(1);
      Executive.destroyEntity(Executive.getCurrentEntity());
    }

    @Override
    public String toString() {
        return "End Talk";
    }
    
    
  }