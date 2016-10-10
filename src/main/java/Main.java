import util.entity.EmployeeDTO;
import util.Utils;

import java.util.Date;

/**
 * Created by marta.ginosyan on 9/29/2016.
 */
public class Main {

    public static void main (String [] args){
        EmployeeDTO employeeDTO = new EmployeeDTO(12345, "TestName", new Date(54645646), "developer");

        String value = Utils.acquireField(employeeDTO, "date");

        System.out.println(value);
    }
}
