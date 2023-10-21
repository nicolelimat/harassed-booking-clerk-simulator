// HBC3.java
// Three phase simulation in java, with GUI-based input using awt.
// Can also use a run-time form developed in Borland JBuilder

package hbcsimulator;

import executive.Executive;
import HBCSim.events.BeginTalk;
import HBCSim.events.BeginService;
import java.io.*;
import java.lang.String;
import SimObs.*;
import java.util.Date;

/** Contains the Cs, initialisation and finalisation for a 3 phase simulation
* of the Harassed Booking Clerk problem introduced in Pidd (1998) Computer
* Simulation in MAnagement Science, John Wiley, Chichester.
* Contains the main() function of the java application.
*/
public class HBC3{
  /** The clerks who serve the customers*/
  public static Resource clerks;
  /** The simulation executive to be used to run the simulation*/
  public static Executive executive;
  /** An input screen for setting simulation parameters*/
  static HBCForm screen;
  /** A C to simulate serving a personal customer*/
  static BeginService beginService;
  /** A C to simulate answering the phone*/
  static BeginTalk beginTalk;
  /** A static object to represent a single observer*/
  static Observer observer;

  /**
  * Constructor that sets up the basic parameters of the simulation.
  * It instantiates an executive, sets up Debug.txt as a trace file, creates
  * 2 clerks as a resource pool, instantiates the first personal customer,
  * first phone call, and the observer.
  * Instantiates the Bs (these are classes via the Activity interface.
  * Reads the parameters from the data input screen, sets up the queues and
  * random number streams, schedules the arrivals of the first personal
  * customer and first phone call and adds the two Cs to the cList.
  */
  public HBC3() {
    executive = new Executive();  // necessita do contrutor ja que tudo e estatico. Nao compensar usar singleton?
    
    TraceFile debugFile = new TraceFile("debug.txt");  // file with the simulation output
    
    clerks = new Resource("clerks", 2);
    PersonalCustomer firstCustomer = new PersonalCustomer("First Personal Customer");
    PhoneEnquirer firstEnquirer = new PhoneEnquirer("First Phone Enquirer");
    observer = new Observer("The Observer");
    
    executive.addNewEntity(firstCustomer);
    executive.addNewEntity(firstEnquirer);
    executive.addNewEntity(observer);
    
    beginService = new BeginService(); // este erro gramatical me atormentou ;)
    beginTalk = new BeginTalk();  // este erro gramatical me atormentou ;)
    executive.setIsTraceable(true);

    // Get simulation parameters from input screen
    PersonalCustomer.setTimes(screen.getMeanInterArrivalTimePersonalEnquirer(), screen.getMeanServiceTimePersonalEnquirer());
    PhoneEnquirer.setTimes(screen.getMeanInterArrivalTimePhoneCallers(), screen.getMeanServiceTimePhoneCallers());
    executive.setSimulationDuration(screen.getSimulationDuration());
      
    Observer.setObservationDuration(screen.getObservationDuration());  // antes chamado setInterval

    // Establish static variables of customer classes
    PersonalCustomer.CreateQueues();
    PersonalCustomer.seedRandomNumberGenerators(9876, 5432);
    PhoneEnquirer.createQueues();
    PhoneEnquirer.seedRandomNumberGenerators(1234, 5678);

    // Schedule first arrivals & observation
    Executive.schedule(firstCustomer, PersonalCustomer.nextArrivalEvent, PersonalCustomer.getMeanInterArrivalTime());
    Executive.schedule(firstEnquirer, PhoneEnquirer.nextCallEvent, PhoneEnquirer.getMeanInterArrivalTime());
    observer.writeEntityOnTraceFile();
    Executive.schedule(observer, observer.nextObservation, Observer.getInterval());
    observer.writeEntityOnTraceFile();

    // Set up CList
    Executive.addC(beginService);
    Executive.addC(beginTalk);

    goForIt();
  }

  /**
  * Runs the simulation
  */
  void goForIt(){
    while (Executive.getCurrentClockTime() <= Executive.getSimulationDuration()){
        System.out.println("Current Executive Clock Time    "  + Executive.getCurrentClockTime());
     //   Date inicio = new Date();
      //  long inicioN = inicio.getTime();
      Executive.simulate();
     // Date fim = new Date(Executive.getCurrentClockTime() - inicioN);
     //   System.out.println(fim);
    }
  }


  /**
  * Could be used to write to the system screen if needed.
  */
  static void DisplayResults(String blurb, int res1, int res2) {
    System.out.println("\n" + blurb + ".. Personal: " + res1 + "   Phone: " + res2);
  }

  
  public static void main(String args[]) {
    screen = new HBCForm("Harassed booking clerk simulation");
  }
}