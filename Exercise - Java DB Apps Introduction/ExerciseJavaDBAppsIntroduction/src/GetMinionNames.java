import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {

    private static final String GET_MINION_NAMES =
            "select v.name,m.name, m.age, mv.villain_id " +
            "from minions as m " +
            "join minions_villains as mv on m.id = mv.minion_id " +
                    "join villains v on v.id = mv.villain_id " +
            "where villain_id = ?;";

    private static final String NO_VILLAIN_FORMAT = "No villain with ID %d exists in the database.";
    private static int counter = 1;

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement query = connection.prepareStatement(GET_MINION_NAMES);

        System.out.print("Type villain id: ");
        int villainId = Integer.parseInt(scanner.next());

        query.setInt(1, villainId);

        final ResultSet resultSet = query.executeQuery();

        if (!resultSet.next()) {
            System.out.printf(NO_VILLAIN_FORMAT,villainId);
            connection.close();
            return;
        }

        String villainString = resultSet.getString("v.name");

        System.out.printf("Villain: %s%n",villainString);

        while(resultSet.next()) {
            String name = resultSet.getString("m.name");
            int age = resultSet.getInt("m.age");

            System.out.printf("%d. %s %d%n", counter++, name, age);
        }
        connection.close();
    }
}
