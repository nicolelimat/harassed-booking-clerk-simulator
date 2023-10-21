// Observer.java
// Source code for observer entity, harassed booking clerk model

package hbcsimulator;

import SimObs.*;
import java.util.ArrayList;
import java.util.List;

/**
* Class to represent an observer noting quote lenghts at regular intervals
*/
public class Observer extends Entity {
  /** Observations of the length of the personal queue*/
  public static List personalCustomerQueue;// List<PersonalCustomer> da pau no metodo dothisnow de Observe que insere um inteiro nesta fila
  /** Observations of the length of the phone queue*/
  public static List phoneEnquirerQueue; // List<PhoneEnquirer> da pau no metodo dothisnow de Observe que insere um inteiro nesta fila
  public static int latestObservationNumber;
  public static int intervalBetweenObservation;
  public static Observe nextObservation;

  /**
  * Straightforward constructor.
  * Establishes the queue records, puts the counters to zero and starts with a
  * zero intervalBetweenObservation between observations.
  */
  Observer(String ID) {
    super(ID);
    personalCustomerQueue = new ArrayList<>();
    phoneEnquirerQueue = new ArrayList<>();
    latestObservationNumber = 0;
    intervalBetweenObservation = 0;
    nextObservation = new Observe();
  }

  /**
  * Sets in the observation intervalBetweenObservation
  */
  static public void setObservationDuration(int thisInterval) {
    intervalBetweenObservation = thisInterval;
  }

  /**
  * Returns with the current observation intervalBetweenObservation.
  */
  static public int getInterval() {
    return intervalBetweenObservation;
  }

    @Override
    public String toString() {
        return "Observer";
    }
  
  

  /** Implements the Activity interface to create the Observer's only B.
  * The B gets the observer entity, increments the observation counter,
  * notes and saves the suzes of the 2 queues, schedules the next observation
  * and writes to the tracefile.
  */
//  class Observe implements Activity {
//    public void doThisNow() {
//      Observer thisObserver = (Observer)Executive.getCurrEnt();
//      latestObservationNumber++;
//      Integer obs1 = new Integer(PersCust.qIn.size());
//      personalCustomerQueue.addElement(obs1);
//      Integer obs2 = new Integer(PhoneEnq.qIn.size());
//      phoneEnquirerQueue.addElement(obs2);
//      Executive.schedule(thisObserver, nextObservation, intervalBetweenObservation);
//      Trace.traceFile.println("Observing queue lengths, intervalBetweenObservation is " + Observer.intervalBetweenObservation);
//    }
//  }
  
  
}