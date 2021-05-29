package de.thro.inf.prg3.a10.kitchen;

import android.util.Log;

import de.thro.inf.prg3.a10.ProgressReporter;
import de.thro.inf.prg3.a10.model.Dish;

public class Waiter implements Runnable{
    private static final String LOGGER_TAG = Waiter.class.getName();
    String name;
    ProgressReporter progressReporter;
    KitchenHatch kitchenHatch;

    public Waiter (String n, ProgressReporter pr, KitchenHatch kh)
    {
        name = n;
        progressReporter = pr;
        kitchenHatch = kh;
    }

    @Override
    public void run() {
        Dish d;
        do {
            /* remove a prepared dish from the kitchen hatch */
            d = kitchenHatch.dequeueDish(5000);
            if(d != null){
                try {
                    /* simulate serving of the dish */
                    Thread.sleep((long)(Math.random() * 1000));
                    Log.i(LOGGER_TAG, String.format("Waiter %s serviced meal %s", name, d.getMealName()));

                    /* update kitchen hitch fill level in the UI */
                    progressReporter.updateProgress();
                }catch (InterruptedException e){
                    Log.e(LOGGER_TAG, String.format("Failed to deliver meal %s by waiter %s", d.getMealName(), name), e);
                }
            }
        }while (kitchenHatch.getOrderCount() > 0 || d != null);

        /* notify the progress reporter that the waiter is leaving */
        progressReporter.notifyWaiterLeaving();
        Log.i(LOGGER_TAG, String.format("Seems there's nothing to do anymore - %s going home", name));
    }
}
