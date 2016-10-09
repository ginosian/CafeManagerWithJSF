package validator;

/**
 * Created by marta.ginosyan on 9/28/2016.
 */
public class ErrorTypes {

    // region General
    static final String NULL_VALUE = "Something went wrong, a null value occur";
    // endregion

    // region New employee name
    static final String EMPTY_EMPLOYEE_NAME = "No name entered";
    static final String EMPLOYEE_NAME_EXIST = "Employee already exist";
    static final String NOT_ALPHABETIC_EMPLOYEE_NAME = "Employee name should consist of alphabetic symbols";
    // endregion

    // region Employee birthday date
    static final String INVALID_BIRTHDAY_DATE = "Birthday date should be before current date";
    static final String EMPTY_BIRTHDAY_DATE = "No birthday date added";
    // endregion

    // region Employee position
    static final String EMPTY_POSITION = "The dialog opened to add position so add one";
    // endregion
}
