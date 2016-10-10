package util.db;

import util.entity.EmployeeDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by marta.ginosyan on 9/27/2016.
 */
public class DBImitation {
    private static DBImitation instance = new DBImitation();
    private static long id;

    private static boolean defaultsAreSet;
    private static ConcurrentHashMap<String, EmployeeDTO> employees = new ConcurrentHashMap<>();

    public static DBImitation getInstance() {
        if(!defaultsAreSet){
            EmployeeDTO employee1 = new EmployeeDTO(instance.generateId(), "DefaultName1", new Date(11111), "");
            EmployeeDTO employee2 = new EmployeeDTO(instance.generateId(), "DefaultName2", new Date(22222), "");

            employees.put(employee1.getName(), employee1);
            employees.put(employee2.getName(), employee2);

            defaultsAreSet = true;
        }
        return instance;
    }

    private DBImitation() {
    }

    public Collection getEmployees() {
        return employees.values();
    }

    public Collection getEmployeesNames() {
        Enumeration<String> namesSet = employees.keys();
        Collection<String> names = new ArrayList<>();
        while (namesSet.hasMoreElements()){
            names.add(namesSet.nextElement());
        }
        return names;
    }

    public boolean dataExist(String name){
        return employees.containsKey(name);
    }

    public void addRecord(EmployeeDTO employee){
        employees.put(employee.getName(), employee);
    }

    public void updatePosition (String employeeName, String position){
        EmployeeDTO employee = employees.get(employeeName);
        employee.setPosition(position);
        employees.replace(employeeName, employee);
    }

    public long generateId(){
        return ++id;
    }
}
