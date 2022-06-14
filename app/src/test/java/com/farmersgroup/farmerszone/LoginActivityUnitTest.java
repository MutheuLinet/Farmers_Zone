package com.farmersgroup.farmerszone;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.farmersgroup.farmerszone.ui.LoginActivity;
import com.google.firebase.FirebaseApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.Objects;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {
    private LoginActivity activity;

    @Before
    public void setUp()  {
        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void validateEditTextContent() {
        EditText emailEditText = activity.findViewById(R.id.emailEditText);
        assertEquals("Email", Objects.requireNonNull(emailEditText.getHint().toString()));
    }

    @Test
    public void loginButton(){
        Button button = activity.findViewById(R.id.passwordLoginButton);
        assertEquals("Log in",button.getText());
    }

}
