package demo.qlns.repository;

import demo.qlns.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmpRepositoryImpl implements EmpRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        Query query = entityManager.createQuery("select e from Employee e");
        return query.getResultList();
    }

    @Override
    public List<Employee> findbyName(String name, String employeeCode, String department, Double salaryFrom, Double salaryTo) {

        String sql = "select e from Employee e where 1=1 ";

        if (!"".equals(name)) {
            sql += " and (e.name like:name)";
        }
        if (!"".equals(employeeCode)) {
            sql += " and (e.employeeCode like:employeeCode)";
        }
        if (!"".equals(department)) {
            sql += " and (e.department like:department)";
        }
        if (salaryFrom != 0) {
            sql = sql + " and (e.salary >:salaryFrom)";
        }
        if (salaryTo != 0) {
            sql = sql + " and (e.salary <:salaryTo )";
        }
        Query query = entityManager.createQuery(sql);
        if (!"".equals(name)) {
            query.setParameter("name", "%" + name + "%");
        }
        if (!"".equals(employeeCode)) {
            query.setParameter("employeeCode", "%" + employeeCode + "%");
        }
        if (!"".equals(department)) {
            query.setParameter("department", "%" + department + "%");
        }
        if (salaryFrom != 0) {
            query.setParameter("salaryFrom", salaryFrom);
        }
        if (salaryTo != 0) {
            query.setParameter("salaryTo", salaryTo);
        }
        return query.getResultList();

    }

    @Override
    @Transactional
    public void create(Employee employee) {
        this.entityManager.persist(employee);
    }

    @Override
    public void edit(Employee employee) {
        this.entityManager.merge(employee);
    }

    @Override
    public void delete(Employee employee) {
        this.entityManager.remove(entityManager.merge(employee));
    }
}
