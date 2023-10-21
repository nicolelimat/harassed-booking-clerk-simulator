// PersCust.java
// Defines personal customer class for harassed booking clerk simulation

package hbcsimulator;

import HBCSim.events.Arrive;
import HBCSim.events.EndServe;
import SimObs.*;

/**
 * Class to represent customers who arrive in person at the theatre booking
 * office in the harassed booking clerk problem. These are represented as entities
 * and therefore extend the GEntity class.
*/
public class PersonalCustomer extends Entity {
   
  public static Queue personalCustomersWaitingForService;
  
  public static Queue personalCustomersServed;
   
  private int customerID;
   
  private static int numberOfPersonalCustomersInstances = 0;
   
  public static int meanIntervalArrivalTime;
  /** Mean service time */
  private static int meanServiceTime;
  /** Samples taken for actual inter-arrival and service times from (negative)
  * exponential distributions */
  public static Sample interArrivalTime, serviceTime;
  
  public static Arrive nextArrivalEvent;
  /** The next service event */
  public static EndServe serviceOver;

  /**
  * Constructor that names the entity, increments the Counter, and
  * instantiates the arrival and endof service Bs
  */
  public PersonalCustomer(String customerName){
    super(customerName);
    customerID = ++numberOfPersonalCustomersInstances;
    nextArrivalEvent = new Arrive(this);
    serviceOver = new EndServe(this);
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
  public static void setTimes(int meanInterArrivalTime, int meanServiceTime) {
    meanIntervalArrivalTime = meanInterArrivalTime;
    meanServiceTime = meanServiceTime;
  }
  
   
  public static int getMeanInterArrivalTime() {
    return meanIntervalArrivalTime;
  }

  /**
  * Returns with the mean service time.
  */
  public static int getMeanOfServiceTime() {
    return meanServiceTime;
  }

  /**
  * Computes and returns a random service time taken from a (negative)
  * exponential distribution.
  */
  public static int getServiceTime() {
    return serviceTime.getSampleFromNegativeExponentialDistribution(meanServiceTime);
  }

  
  public static void CreateQueues() {
    personalCustomersWaitingForService = new Queue();
    personalCustomersServed = new Queue();
  }

    /**
     * @return the number
     */
    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return super.toString() + "\n toString() superclasse + PersonalCustomer.ToString";
    }
    
    

  /**
  * Implements the Activity interface to establish a B that simulates the
  * arrival of a personal customer. It adds the customer to the tail of QIn,
  * instantiates the next personal customer, informs the executive of this and
  * then schedules this to arrive after some time interval taken as a sample
  * from a (negative) exponential distribution.
  */
//  class Arrive implements Activity {
//    public void doThisNow() {
//      qIn.addToTail(Executive.getCurrEnt());
//      qIn.writeEntityOnTraceFile();
//      Trace.traceFile.println("PersCust " + number + " arrives at " + Executive.getClock());
//      PersCust nextCust = new PersCust("PersCust");
//      Executive.addNewEntity(nextCust);
//      Executive.schedule(nextCust, nextArrival, interArrTime.negExp(iAT));
//      nextCust.writeEntityOnTraceFile();
//    }
//  }

  /**
  * Implements the Activity interface to establish a B that simulates the
  * end of service of a personal customer. Adds the customer entity to the tail
  * of QOut and releases the clerk.
  */
//  class EndServe implements Activity {
//    public void doThisNow() {
//      qOut.addToTail(Executive.getCurrEnt());
//      Trace.traceFile.println("PersCust " + getNumber() + " leaves at " + Executive.getClock());
//      HBC3.clerks.makeAvail(1);
//      Executive.destroyEntity(Executive.getCurrEnt());
//    }
//  }
}
