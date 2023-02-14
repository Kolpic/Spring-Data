import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IncreaseSalaries {
    private static final String UPDATE_QUERY_SALARY_BY_12 =
            "UPDATE Employee e set e.salary = e.salary * 1.12 WHERE e.department.id in (1, 2, 14,3)";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        int countOfUpdates = entityManager.createQuery(UPDATE_QUERY_SALARY_BY_12, Employee.class)
                .executeUpdate();

        if (countOfUpdates != 0) {
            entityManager.getTransaction().commit();

            entityManager.createQuery("select e from Employee e " +
                            "where e.department.name in " +
                            "('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                    .getResultList()
                    .forEach(e -> System.out.printf("%s %s ($%.2f)",e.getFirstName(), e.getLastName(), e.getSalary()));
        } else {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
