package de.thro.inf.prg3.a10.model;

/**
 * Dish instance prepared by a cook
 * @author Peter Kurfer
 */

public final class Dish {

    /* Multiplier to calculate the cooking time for the dish */
    private static final double MULTIPLIER = 60d;

    /* Minimum value to add to the calculated cooking time */
    private static final int MINIMUM_COOK_TIME = 600;

    private final int cookingTime;
    private final String mealName;

    public Dish(String mealDescription) {
        this.mealName = mealDescription;

        /* calculate a random cooking time between 600 and round about 2500 */
        this.cookingTime = (int)(Math.random() * MULTIPLIER * (double) mealDescription.length() + MINIMUM_COOK_TIME);
    }

    /**
     * Get the time required to cook this dish
     * @return time in ms
     */
    public final int getCookingTime() {
        return cookingTime;
    }

    /**
     * Get the name of the dish
     * @return name
     */
    public final String getMealName() {
        return mealName;
    }
}
