package demo.qlns.controller;

import demo.qlns.model.Employee;
import demo.qlns.services.EmpService;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

//@Controller
@SessionScoped
@Named
@Join(path = "/", to="list.jsf")
public class EmpController {
    @Autowired
    EmpService empService;

    private List<Employee> employees;

    private Employee employee = new Employee();

    private String name;
    private String salaryFrom;
    private String salaryTo;

    @PostConstruct
    public void init() {
        employees = empService.findAll();
    }
    public String searchName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo) {
        employees = empService.findbyName(name, employeeCode, department, salaryFrom, salaryTo);
        return "list.xhtml?faces-redirect=true";
    }
    public String save() {
        this.empService.create(employee);
        employees = empService.findAll();
        return "/list.xhtml?faces-redirect=true";
    }

    public String add() {
        this.employee = new Employee();
        return "/create.xhtml?faces-redirect=true";
    }
    public String delete(Employee employee) {
        this.empService.delete(employee);
        this.init();
        return "/list.xhtml?faces-redirect=true";
    }
    public String viewEdit(Employee employee){
        this.employee = employee;
        return "/edit.xhtml?faces-redirect=true";
    }
    public String edit(Employee employee){
        this.empService.edit(employee);
        this.init();
        return "/list.xhtml?faces-redirect=true";
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(String salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public String getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(String getSalaryTo) {
        this.salaryTo = getSalaryTo;
    }
}
