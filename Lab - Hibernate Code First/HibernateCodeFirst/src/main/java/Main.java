import entities.Bike;
import entities.Car;
import entities.Plane;
import entities.Vehicle;
import hasEntities.Article;
import hasEntities.PlateNumber;
import hasEntities.Truck;
import hasEntities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("relations");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

//        Vehicle car = new Car("Ford Focus", "Petrol", 5);
//        Vehicle bike = new Bike();
//        Vehicle plane = new Plane("Airbus", "Petrol", 200);
//
//        entityManager.persist(car);
//        entityManager.persist(bike);
//        entityManager.persist(plane);
//
//        Car fromDB = entityManager.find(Car.class, 1L);
//        System.out.println(fromDB.getSeats() + " " + fromDB.getModel());
//
//        PlateNumber plateNumber = new PlateNumber("123");
//        Truck truck1 = new Truck(plateNumber);
//        Truck truck2 = new Truck(plateNumber);
//
//        entityManager.persist(plane);
//        entityManager.persist(truck1);
//        entityManager.persist(truck2);

        Article article = new Article("Vestnik");
        User author = new User("Galin");
        author.addArticle(article);
        article.setAuthor(author);

        entityManager.persist(author);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
