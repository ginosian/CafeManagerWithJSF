package util.form;

import util.db.DBImitation;
import util.entity.EmployeeDTO;

import javax.faces.bean.ManagedBean;
import java.util.Collection;
import java.util.Date;

/**
 * Created by marta.ginosyan on 9/27/2016.
 */
@ManagedBean(name = "EmployeeForm")
public class EmployeeForm {

    private String name;
    private Date birthDate;

    public EmployeeForm() {
    }

    public void addRecord() {
        long id = DBImitation.getInstance().generateId();
        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, birthDate, "");
        DBImitation.getInstance().addRecord(employeeDTO);
        name = null;
        birthDate = null;
    }

    public Collection getEmployees(){
        return DBImitation.getInstance().getEmployees();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
