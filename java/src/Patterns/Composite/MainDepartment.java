package Patterns.Composite;

import java.util.ArrayList;
import java.util.List;

public class MainDepartment implements Department {
    private Integer id;
    private String name;

    private List<Department> otherDepartments;

    public MainDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.otherDepartments = new ArrayList<>();
    }

    public void printDepartmentName() {
        otherDepartments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department) {
        otherDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        otherDepartments.remove(department);
    }
}
