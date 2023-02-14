import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    private static final String SELECT_FIRST_NAME_WHERE_SALARY_BIGGER_THAN_50000 =
            "select e.firstName from Employee e where e.salary > 50000";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(SELECT_FIRST_NAME_WHERE_SALARY_BIGGER_THAN_50000);
        List resultList = query.getResultList();

        resultList.stream().forEach(System.out::println);

        entityManager.close();
    }
}
