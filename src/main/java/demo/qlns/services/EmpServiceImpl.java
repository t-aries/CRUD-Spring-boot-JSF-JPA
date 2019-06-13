package demo.qlns.services;

import demo.qlns.model.Employee;
import demo.qlns.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpRepository empRepository;

    public EmpServiceImpl(EmpRepository empRepository){ this.empRepository = empRepository;}

    @Override
    public List<Employee> findAll() {
        return empRepository.findAll();
    }

    @Override
    public List<Employee> findbyName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo) {
        return empRepository.findbyName(name,employeeCode,department, salaryFrom, salaryTo);
    }

    @Override
    public void create(Employee employee) {
        empRepository.create(employee);
    }

    @Override
    public void edit(Employee employee) {
        empRepository.edit(employee);
    }

    @Override
    public void delete(Employee employee) {
        empRepository.delete(employee);
    }
}
