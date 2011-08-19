package de.bloodink.timers;

import javax.ejb.Stateless;
import javax.ejb.Timer;

/**
 * This is a first Tryout Timer, which is executed ever 10 seconds, during 8 am
 * - 3 am, Mon - Fri, every day.
 * 
 * @author Franz Mathauser
 */
@Stateless
public class MyTimer {

    /**
     * Default constructor.
     */
    public MyTimer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * This Method prints a Date message to the Server log.
     * 
     * @param t
     *            timer
     */
    @SuppressWarnings("unused")
    // @Schedule(second="*/10", minute="*", hour="8-3", dayOfWeek="Mon-Fri",
    // dayOfMonth="*", month="*", year="*", info="MyTimer")
    private void scheduledTimeout(final Timer t) {
        System.out.println("@Schedule called at: " + new java.util.Date());
    }
}
