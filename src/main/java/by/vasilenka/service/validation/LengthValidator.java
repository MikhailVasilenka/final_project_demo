package by.vasilenka.service.validation;

public class LengthValidator extends FormValidator implements ValidationService {
    public LengthValidator() {
        super.pattern = "^.{0,40}$";
    }
}
