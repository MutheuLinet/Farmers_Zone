package com.farmersgroup.farmerszone;

import static org.junit.Assert.assertEquals;

import android.widget.Button;

import com.farmersgroup.farmerszone.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
    public class MainActivityUnitTest {

        private MainActivity mainActivity;

        @Before
        public void setUp(){
            mainActivity = Robolectric.buildActivity(MainActivity.class)
                    .create()
                    .start()
                    .resume()
                    .get();
        }
        @Test
        public void welcomeButton(){
            Button button = mainActivity.findViewById(R.id.textViewWelcome);
            assertEquals("Welcome To Farmer's Zone!", button.getText());
        }
        @Test
        public void validateButton(){
            Button button = mainActivity.findViewById(R.id.buttonStart);
            assertEquals("Click Here To Start",button.getText());
        }
    }

