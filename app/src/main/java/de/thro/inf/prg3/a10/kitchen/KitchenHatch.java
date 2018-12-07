package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;

/**
 * Kitchen hatch instance
 * synchronizes the access between the cooks and waiters
 * @author Peter Kurfer
 */

public interface KitchenHatch {

    /**
     * Get the count how many meals can be placed in the hatch
     * @return max count
     */
    int getMaxDishes();

    /**
     * Dequeue an outstanding order
     * @return an order or null if all orders are done
     */
    default Order dequeueOrder() {
        return dequeueOrder(1000);
    }

    /**
     * Dequeue an outstanding order
     * @param timeout timeout to pass to the wait call if no orders are present
     * @return an order or null if all orders are done
     */
    Order dequeueOrder(long timeout);

    /**
     * Get the remaining count of orders
     * @return count of orders
     */
    int getOrderCount();

    /**
     * Dequeue a completed dish
     * @return hopefully hot dish to serve to a guest
     */
    default Dish dequeueDish() {
        return dequeueDish(1000);
    }

    /**
     * Dequeue a completed dish
     * @param timeout timeout to pass to the wait call if no meals are present
     * @return hopefully hot dish to serve to a guest
     */
    Dish dequeueDish(long timeout);

    /**
     * Enqueue a new completed dish to be served by a waiter
     * @param m Dish to enqueue
     */
    void enqueueDish(Dish m);

    /**
     * Get the total count of dishes in the kitchen hatch
     * @return total count of dishes
     */
    int getDishesCount();
}
