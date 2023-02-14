import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int idWanted = Integer.parseInt(scanner.nextLine());

        String employee = entityManager.createQuery(
                "select e from Employee as e where e.id = :id", Employee.class)
                .setParameter("id", idWanted)
                .getSingleResult()
                .toString();

        System.out.println(employee);
    }
}
