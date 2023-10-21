/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hbcsimulator;

import executive.Executive;
import SimObs.Activity;
import SimObs.TraceFile;

/**
 *
 * @author ivan
 */
/**
 * Implements the Activity interface to create the Observer's only B. The B gets
 * the observer entity, increments the observation counter, notes and saves the
 * suzes of the 2 queues, schedules the next observation and writes to the
 * tracefile.
 */
class Observe implements Activity {

    public void doThisNow() {
        Observer thisObserver = (Observer) Executive.getCurrentEntity();  // era chamado getEvent antes do refactoring
        Observer.latestObservationNumber++;
        Integer obs1 = new Integer(PersonalCustomer.personalCustomersWaitingForService.size());
        Observer.personalCustomerQueue.add(obs1);
        Integer obs2 = new Integer(PhoneEnquirer.phoneEnquirersWaitingForService.size());
        Observer.phoneEnquirerQueue.add(obs2);
        Executive.schedule(thisObserver, Observer.nextObservation, Observer.intervalBetweenObservation);
        TraceFile.traceFile.println("Observing queue lengths, interval is " + Observer.intervalBetweenObservation);
        System.out.println("Observing queue lengths, interval is " + Observer.intervalBetweenObservation);
    }

    @Override
    public String toString() {
        return "Observe.toString()";
    }
    
    
}