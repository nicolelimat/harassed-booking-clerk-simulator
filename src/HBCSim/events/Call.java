/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HBCSim.events;

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
  * arrival of a phonecall. It adds the phone call to the tail of QIn,
  * instantiates the next phone call, informs the executive of this and
  * then schedules this to arrive after some time interval taken as a sample
  * from a (negative) exponential distribution.
  */
  public class Call implements Activity {
    private PhoneEnquirer phoneEnq;

    public Call(PhoneEnquirer phoneEnq) {
        this.phoneEnq = phoneEnq;
    }
      
    public void doThisNow() {
      PhoneEnquirer.phoneEnquirersWaitingForService.addToTail(Executive.getCurrentEntity()); // era chamado de getCurrentEvent
      TraceFile.traceFile.println("PhoneEnq " + phoneEnq.getNumber() + " arrives at " + Executive.getCurrentClockTime());
        System.out.println("PhoneEnq " + phoneEnq.getNumber() + " arrives at " + Executive.getCurrentClockTime());
      PhoneEnquirer nextEnq = new PhoneEnquirer("PhoneEnq");
      Executive.addNewEntity(nextEnq);
      Executive.schedule(nextEnq, PhoneEnquirer.nextCallEvent, PhoneEnquirer.interArrivalTime.getSampleFromNegativeExponentialDistribution(PhoneEnquirer.meanIntervalArrivalTime));
    }

    @Override
    public String toString() {
        return "Call.toString()";
    }
    
    
  }