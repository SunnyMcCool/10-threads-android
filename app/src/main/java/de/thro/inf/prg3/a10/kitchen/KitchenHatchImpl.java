package de.thro.inf.prg3.a10.kitchen;

import android.util.Log;

import java.util.Deque;
import java.util.LinkedList;

import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;

public class KitchenHatchImpl implements KitchenHatch {
    private static final String LOGGER_TAG = KitchenHatch.class.getName();
    private int maxMeals;
    private Deque<Order> orders;
    private Deque<Dish> dishes;

    public KitchenHatchImpl(int maxMeals, Deque<Order> orders) {
        this.maxMeals = maxMeals;
        this.orders = orders;
        dishes = new LinkedList<>();
    }

    @Override
    public int getMaxDishes() {
        return maxMeals;
    }

    @Override
    public Order dequeueOrder(long timeout) {
        Order o = null;
        /* synchronize to avoid unpredictable behavior when multiple cooks are dequeuing orders at the same time */
        synchronized (orders) {
            if (orders.size() >= 1) {
                o = orders.pop();
            }
        }
        return o;
    }

    @Override
    public int getOrderCount() {
        /* synchronize to avoid unpredictable behavior when multiple waiters are checking if there are still orders available */
        synchronized (orders) {
            return orders.size();
        }
    }

    @Override
    public Dish dequeueDish(long timeout) {
        long currentTimeStamp = System.nanoTime();
        /* synchronize to avoid unpredictable behavior when multiple waiters are dequeuing dishes at the same time */
        synchronized (dishes) {
            while (dishes.size() == 0) {
                try {
                    Log.i(LOGGER_TAG, "Kitchen hatch is empty. I can wait");
                    /* wait until new dishes are enqueued or timeout is reached - blocks the thread */
                    dishes.wait(timeout);
                } catch (InterruptedException e) {
                    Log.e(LOGGER_TAG, "Error when waiting for new dishes", e);
                }

                /* break condition as a waiter is leaving if it is getting a null result and waited for a long time */
                if (timeout > 0 && dishes.size() == 0 && System.nanoTime() - currentTimeStamp > timeout * 1000) {
                    Log.i(LOGGER_TAG, "Kitchen hatch still empty. Going home now");
                    /* notify all waiters to re-enable them */
                    dishes.notifyAll();
                    return null;
                }
            }
            /* dequeue a completed dish */
            Dish result = dishes.pop();
            Log.i(LOGGER_TAG, String.format("Taking %s out of the kitchen hatch", result));

            /* re-enable all waiting waiters */
            dishes.notifyAll();
            return result;
        }


    }

    @Override
    public void enqueueDish(Dish m) {
        /* synchronize to avoid unpredictable behavior when multiple cooks are enqueuing dishes at the same time */
        synchronized (dishes) {
            while (dishes.size() >= maxMeals) {
                try {
                    /* wait if the kitchen hatch is full at the moment */
                    Log.i(LOGGER_TAG, "Kitchen hatch is full, waiting...");
                    dishes.wait();
                } catch (InterruptedException e) {
                    Log.e(LOGGER_TAG, "Error while waiting for enough place to place meal");
                }
            }

            Log.e(LOGGER_TAG, String.format("Putting %s into the kitchen hatch", m));

            /* enqueue the prepared dish */
            dishes.push(m);

            /* re-enable all waiting cooks */
            dishes.notifyAll();
        }

    }

    @Override
    public int getDishesCount() {
        synchronized (dishes) {
            return dishes.size();
        }
    }
}
