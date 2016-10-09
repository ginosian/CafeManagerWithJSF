package validator;

import db.DBImitation;
import util.Utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

/**
 * Created by marta.ginosyan on 9/28/2016.
 */
@FacesValidator("UniversalValidator")
public class UniversalValidator implements Validator{

    private String errorMessage;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        switch (uiComponent.getId()){
            case "name":
                if(!newEmployeeNameIsValid(o)) validationError(errorMessage);
                break;
            case "position":
                if (!positionIsValid(o)) validationError(errorMessage);
                break;
            case "birthDate":
                if (!birthdayDateIsValid(o)) validationError(errorMessage);
                break;
        }
    }

    private void validationError(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(message);
        facesContext.addMessage(null, msg);
        throw new ValidatorException(msg);
    }



    // region Validation
    private boolean newEmployeeNameIsValid(Object o){
        errorMessage = null;
        if(nullValue(o)) {
            errorMessage = ErrorTypes.NULL_VALUE;
            return false;
        }
        if(stringDataIsEmpty(o)) errorMessage = ErrorTypes.EMPTY_EMPLOYEE_NAME;
        if(employeeExist(o)) errorMessage =  ErrorTypes.EMPLOYEE_NAME_EXIST;
        if(notAlphabetic(o)) errorMessage =  ErrorTypes.NOT_ALPHABETIC_EMPLOYEE_NAME;
        return errorMessage == null;
    }

    private boolean birthdayDateIsValid(Object o){
        errorMessage = null;
        if(nullValue(o)) {
            errorMessage = ErrorTypes.NULL_VALUE;
            return false;
        }
        if(notADate(o)) errorMessage = ErrorTypes.EMPTY_BIRTHDAY_DATE;
        if(isAfterToday(o)) errorMessage =  ErrorTypes.INVALID_BIRTHDAY_DATE;
        return errorMessage == null;
    }

    private boolean positionIsValid(Object o){
        if(nullValue(o)) {
            errorMessage = ErrorTypes.NULL_VALUE;
            return false;
        }
        if(stringDataIsEmpty(o)) errorMessage = ErrorTypes.EMPTY_POSITION;
        return errorMessage == null;
    }
    // endregion



    // region Checks
    private boolean nullValue(Object o){
        return o == null;
    }

    private boolean stringDataIsEmpty(Object o){
        return !(o instanceof String) || !Utils.isValidString((String)o);
    }

    private boolean employeeExist(Object o){
        return DBImitation.getInstance().dataExist((String) o);
    }

    private boolean notAlphabetic(Object o){
        return !Utils.isAlphabeticData((String) o);
    }

    private boolean notADate(Object o){
        return !(o instanceof Date);
    }

    private boolean isAfterToday(Object o){
        if(!Utils.isBeforeToday((Date) o) || Utils.dayIsToday((Date) o)) return true;
        return false;
    }
    // endregion
}
