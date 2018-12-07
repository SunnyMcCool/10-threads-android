package de.thro.inf.prg3.a10.model;

/**
 * Order containing the name of the dish a cook has to prepare
 * @author Peter Kurfer
 */

public final class Order {

    private final String mealName;

    public Order(String mealName) {
        this.mealName = mealName;
    }

    public final String getMealName() {
        return mealName;
    }
}
