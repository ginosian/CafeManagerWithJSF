package util.form;

import util.db.DBImitation;

import javax.faces.bean.ManagedBean;
import java.util.Collection;

/**
 * Created by marta.ginosyan on 9/27/2016.
 */
@ManagedBean(name = "EmployeesPopupForm")
public class EmployeesPopupForm {

    private String position;
    private String name;

    public EmployeesPopupForm() {
        System.out.println();
    }

    public Collection getNames() {
        return DBImitation.getInstance().getEmployeesNames();
    }

    public void updatePosition() {
        DBImitation.getInstance().updatePosition(name, position);
        position = null;
        name = null;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
