package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by marta.ginosyan on 9/29/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequiredTypes {

    String SerializedName() default "";

    boolean id() default false;

    boolean name() default false;

    boolean position() default false;

    boolean date() default false;


}
