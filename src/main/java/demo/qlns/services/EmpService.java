package demo.qlns.services;

import demo.qlns.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
    List<Employee> findAll();

    List findbyName(String name,String employeeCode, String department, Double salaryFrom, Double salaryTo);

    void create(Employee employee);

    void edit(Employee employee);

    void delete(Employee employee);
}
