package demo.qlns.repository;

import demo.qlns.model.Employee;

import java.util.List;

public interface EmpRepository {
    List<Employee> findAll();

    List findbyName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo);

    void create(Employee employee);

    void edit(Employee employee);

    void delete(Employee employee);
}
