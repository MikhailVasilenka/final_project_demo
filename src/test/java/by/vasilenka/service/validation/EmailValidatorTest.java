package by.vasilenka.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {
    @Test
    public void isValid(){
        ValidationService emailValidator = new EmailValidator();
        String validEmail1="test@mail.ru";
        String validEmail2="test@gmail.com";
        String invalidEmail1="1234567";
        String invalidEmail2="test@mail.c";
        String invalidEmail3="@mail.";
        assertTrue(emailValidator.isValid(validEmail1));
        assertTrue(emailValidator.isValid(validEmail2));
        assertFalse(emailValidator.isValid(invalidEmail1));
        assertFalse(emailValidator.isValid(invalidEmail2));
        assertFalse(emailValidator.isValid(invalidEmail3));


    }

}