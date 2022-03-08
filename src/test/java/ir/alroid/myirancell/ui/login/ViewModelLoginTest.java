package ir.alroid.myirancell.ui.login;

import android.app.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ViewModelLoginTest {

    @Mock
    Application application;

    ViewModelLogin viewModelLogin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModelLogin = new ViewModelLogin(application);
    }

    @After
    public void shutdown() {
        viewModelLogin = null;
    }

    @Test
    public void isValidEmail_Normal() {
        // Arrange
        String email = "al.allahverdi@gmail.com";

        // Act
        boolean result = viewModelLogin.isValidEmail(email);

        // Assert
        assertEquals(result, true);
    }

    @Test
    public void isValidEmail_Empty() {
        // Arrange
        String email = "";

        // Act
        boolean result = viewModelLogin.isValidEmail(email);

        // Assert
        assertEquals(result, false);
    }

    @Test
    public void isValidEmail_Null() {
        // Arrange
        String email = null;

        // Act
        boolean result = viewModelLogin.isValidEmail(email);

        // Assert
        assertEquals(result, false);
    }

    @Test
    public void isValidEmail_Invalid_Input() {
        // Arrange
        String email = "!@321sfgkj";

        // Act
        boolean result = viewModelLogin.isValidEmail(email);

        // Assert
        assertEquals(result, false);
    }

    @Test
    public void isValidPassword_Normal() {
        // Arrange
        String password = "12345678";

        // in this case password just should be grater than 8 character ...
        boolean result = viewModelLogin.isValidPassword(password);

        // Assert
        assertEquals(result, true);
    }

    @Test
    public void isValidPassword_Empty() {
        // Arrange
        String password = "";

        // in this case password just should be grater than 8 character ...
        boolean result = viewModelLogin.isValidPassword(password);

        // Assert
        assertEquals(result, false);
    }

    @Test
    public void isValidPassword_Null() {
        // Arrange
        String password = null;

        // in this case password just should be grater than 8 character ...
        boolean result = viewModelLogin.isValidPassword(password);

        // Assert
        assertEquals(result, false);
    }

    @Test
    public void isValidPassword_Invalid_Input() {
        // Arrange
        String password = "1.@t_";

        // in this case password just should be grater than 8 character ...
        boolean result = viewModelLogin.isValidPassword(password);

        // Assert
        assertEquals(result, false);
    }

}