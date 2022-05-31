package com.farmersgroup.farmerszone;

import static org.junit.Assert.assertEquals;

import android.widget.Button;
import android.widget.EditText;

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
        public void validateStartInt(){
            EditText textInputLayout = mainActivity.findViewById(R.id.editTextName);
            assertEquals("Enter Your Name",textInputLayout.getHint());
        }

        @Test
        public void validateButton(){
            Button button = mainActivity.findViewById(R.id.buttonName);
            assertEquals("Submit",button.getText());
        }
    }

