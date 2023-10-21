// PhoneEnq.java
// Phone caller class for harassed booking clerk simulation

package hbcsimulator;

import HBCSim.events.Call;
import HBCSim.events.EndTalk;
import SimObs.*;

/**
 * Class to represent customers who phone in to the theatre booking
 * office in the harassed booking clerk problem. These are represented as entities
 * and therefore extend the GEntity class.
*/
public class PhoneEnquirer extends Entity {
  /** Personal customers waiting for sevice */
  public static Queue phoneEnquirersWaitingForService;
  /** Personal customers who have been served */
  public static Queue phoneEnquirersServed;
  /** The unique numeric ID of a customer */
  private int phoneEnquirerID;
  /** Counter for the number of instances created so far */
  private static int numberOfInstances = 0;
  /** Mean inter-arrival time */
  public static int meanIntervalArrivalTime;
  /** Mean service time */
  private static int meanServiceTime;
  /** Samples taken for actual inter-arrival and service times from (negative)
  * exponential distributions */
  public static Sample interArrivalTime, serviceTime;
  /** The next arrival event */
  public static Call nextCallEvent;
  /** The next service event */
  public static EndTalk serviceOver;

  /**
  * Constructor that names the entity, increments the Counter, and
  * instantiates the arrival and endof service Bs
  */
  public PhoneEnquirer(String ID) {
    super(ID);
    phoneEnquirerID = ++numberOfInstances;
    nextCallEvent = new Call(this);
    serviceOver = new EndTalk(this);
  }

  /**
  * Seeds both random number generators used by Sample for personal customers
  */
  public static void seedRandomNumberGenerators(long seed1, long seed2) {
    interArrivalTime = new Sample(seed1);
    serviceTime = new Sample(seed2);
  }

  /** Sets the mean inter-arrival and service times. These may have been taken
  * from an input screen.
  */
  public static void setTimes(int iat, int servetime) {
        meanIntervalArrivalTime = iat;
        meanServiceTime = servetime;
  }

  /**
  * Returns with the mean inter arrival time.
  */
  public static int getMeanInterArrivalTime() {
    return meanIntervalArrivalTime;
  }

  /**
  * Returns with the mean service time.
  */
  public static int getST() {
    return meanServiceTime;
  }

  /**
  * Computes and returns a random service time taken from a (negative)
  * exponential distribution.
  */
  public static int getServiceTime() {
    return serviceTime.getSampleFromNegativeExponentialDistribution(meanServiceTime);
  }

  /**
  * Instantiates queues pre and post service.
  */
  public static void createQueues() {
    phoneEnquirersWaitingForService = new Queue();
    phoneEnquirersServed = new Queue();
  }

    /**
     * @return the number
     */
    public int getNumber() {
        return phoneEnquirerID;
    }

    @Override
    public String toString() {
        return super.toString() + "  + Phone Enquirer";
    }

  /**
  * Implements the Activity interface to establish a B that simulates the
  * arrival of a phonecall. It adds the phone call to the tail of QIn,
  * instantiates the next phone call, informs the executive of this and
  * then schedules this to arrive after some time interval taken as a sample
  * from a (negative) exponential distribution.
  */
//  class Call implements Activity {
//    public void doThisNow() {
//      phoneEnquirersWaitingForService.addToTail(Executive.getCurrEnt());
//      Trace.traceFile.println("PhoneEnq " + number + " arrives at " + Executive.getClock());
//      PhoneEnq nextEnq = new PhoneEnq("PhoneEnq");
//      Executive.addNewEntity(nextEnq);
//      Executive.schedule(nextEnq, nextCallEvent, interArrivalTime.negExp(meanIntervalArrivalTime));
//    }
//  }

  /**
  * Implements the Activity interface to establish a B that simulates the
  * end of a phone conversation. Adds the phone call entity to the tail
  * of QOut and releases the clerk.
  */
//  class EndTalk implements Activity {
//    public void doThisNow() {
//      phoneEnquirersServed.addToTail(Executive.getCurrEnt());
//      Trace.traceFile.println("PhoneEnq " + getNumber() + " leaves at " + Executive.getClock());
//      HBC3.clerks.makeAvail(1);
//      Executive.destroyEntity(Executive.getCurrEnt());
//    }
//  }
    
    
    
}
