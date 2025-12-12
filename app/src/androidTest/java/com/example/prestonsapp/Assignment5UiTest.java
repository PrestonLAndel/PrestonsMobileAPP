package com.example.prestonsapp;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Assignment5UiTest {

    private static final String PACKAGE_NAME = "com.example.prestonsapp";
    private static final long LAUNCH_TIMEOUT = 5000L;

    private UiDevice device;

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        device.pressHome();

        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
        }
    }

    @Test
    public void testStartActivityExplicitlyShowsChallenge() throws UiObjectNotFoundException {

        UiObject explicitButton =
                device.findObject(new UiSelector().textContains("Start Activity Explicitly"));
        explicitButton.click();

        UiObject challengeText =
                device.findObject(new UiSelector().textContains("Device fragmentation"));

        assertTrue("Second activity does not display the expected challenge text.",
                challengeText.exists());
    }
}
