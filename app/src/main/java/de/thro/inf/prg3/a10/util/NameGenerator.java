package de.thro.inf.prg3.a10.util;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.thro.inf.prg3.a10.R;

/**
 * Random cook and waiter name generator
 * furthermore it provides a method to get a random dish name
 * @author Peter Kurfer
 */


public class NameGenerator {

    private final Random random = new Random();
    private final List<String> adjectives;
    private final List<String> names;
    private final List<String> dishNames;

    /**
     * Default constructor
     * fills two the lists of names and adjectives to generate random names
     * @param ctx context - required to access string resources
     */
    public NameGenerator(Context ctx) {
        adjectives = new ArrayList<>();
        names = new ArrayList<>();
        dishNames = new ArrayList<>();

        Resources resources = ctx.getResources();
        adjectives.addAll(Arrays.asList(resources.getStringArray(R.array.adjectives)));
        names.addAll(Arrays.asList(resources.getStringArray(R.array.names)));
        dishNames.addAll(Arrays.asList(resources.getStringArray(R.array.dishes)));
    }

    /**
     * Generate a random name for a pilot
     * @return random name in the format '[adjective] [well known person]'
     */
    public String generateName(){
        String randomAdjective = adjectives.get(random.nextInt(adjectives.size() - 1));
        String randomName = names.get(random.nextInt(names.size() - 1));
        return String.format("%s %s", randomAdjective, randomName);
    }

    /**
     * Get a name of random dish
     * @return random dish name
     */
    public String getDishName(){
        return dishNames.get(random.nextInt(dishNames.size() - 1));
    }
}
