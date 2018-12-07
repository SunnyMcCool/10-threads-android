package de.thro.inf.prg3.a10;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Test;

import de.thro.inf.prg3.a10.util.NameGenerator;

import static de.thro.inf.prg3.a10.TestConstants.LOGGING_TAG;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NameGeneratorTests {

    @Test
    public void testGenerateRandomName() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        NameGenerator nameGenerator = new NameGenerator(appContext);
        String generatedName = nameGenerator.generateName();

        assertNotNull(generatedName);
        assertNotEquals(0, generatedName.length());
        Log.d(LOGGING_TAG, String.format("Generated name: %s", generatedName));
    }

    @Test
    public void testGetDishName() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        NameGenerator nameGenerator = new NameGenerator(appContext);

        for(int i = 0; i < 10000; i++) {
            String dishName = nameGenerator.getDishName();
            assertNotNull(dishName);
            Log.d(LOGGING_TAG, String.format("Got random dish: %s", dishName));
        }
    }
}
