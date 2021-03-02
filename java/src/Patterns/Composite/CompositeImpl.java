package Patterns.Composite;

public class CompositeImpl {
    public static void main(String[] args) {
        Department backEndDepartment = new BackEndDepartment(1, "Back End Department");
        Department frontEndDepartment = new FrontEndDepartment(2, "Front End Department");

        MainDepartment mainDepartment = new MainDepartment(3, "Main Department");

        mainDepartment.addDepartment(backEndDepartment);
        mainDepartment.addDepartment(frontEndDepartment);

        mainDepartment.printDepartmentName();

        mainDepartment.removeDepartment(frontEndDepartment);

        mainDepartment.printDepartmentName();
    }
}
