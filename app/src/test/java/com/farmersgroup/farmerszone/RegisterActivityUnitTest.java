package com.farmersgroup.farmerszone;

import static org.junit.Assert.assertEquals;

import android.widget.Button;
import android.widget.EditText;

import com.farmersgroup.farmerszone.ui.LoginActivity;
import com.farmersgroup.farmerszone.ui.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.Objects;

@RunWith(RobolectricTestRunner.class)
public class RegisterActivityUnitTest {
    private RegisterActivity activity;

    @Before
    public void setUp()  {
        activity = Robolectric.buildActivity(RegisterActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void validateEditTextContent() {
        EditText nameEditText = activity.findViewById(R.id.nameEditText);
        assertEquals("Full Name", Objects.requireNonNull(nameEditText.getHint().toString()));
    }
    @Test
    public void registerButton(){
        Button button = activity.findViewById(R.id.createUserButton);
        assertEquals("Sign up",button.getText());
    }

}
