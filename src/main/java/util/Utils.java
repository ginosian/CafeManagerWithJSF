package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marta.ginosyan on 9/27/2016.
 */
public class Utils {

    public static String acquireField(Object object, String type) {
        try {
            Object value;
            Field fields[] = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                RequiredTypes annotation = fields[i].getAnnotation(RequiredTypes.class);
                if (annotation != null) {
                    Method requestField = annotation.getClass().getMethod(type);
                    if ((Boolean) (requestField.invoke(annotation, null))) {
                        fields[i].setAccessible(true);
                        value = fields[i].get(object);
                        return value.toString();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public static boolean dayIsToday(Date date){
        if(date.compareTo(getCurrentDate()) != 0) return false;
        return true;
    }

    public static boolean isBeforeToday(Date date){
        return date.before(Utils.getCurrentDate());
    }

    public static boolean isAlphabeticData(String data){
        return data.chars().allMatch(Character::isLetter);
    }

    public static boolean isValidString(String data){
        return data != null && !data.isEmpty();
    }
}
