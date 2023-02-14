import java.sql.*;

public class GetVillainsNames {
    private static final String GET_VILLAIN_NAMES =
            "select v.name, count(distinct m.id) as count " +
            "from villains as v " +
            "join minions_villains as mv on v.id = mv.villain_id " +
            "join minions m on mv.minion_id = m.id " +
            "group by v.name " +
            "having count > ? " +
            "order by count desc;";

    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_MINIONS_COUNT = "count";


    public static void main(String[] args) throws SQLException {

//        Properties  properties = new Properties();
//
//        properties.setProperty("user","root");
//        properties.setProperty("password","1234");
//
//        Connection connection = DriverManager
//                .getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement query = connection.prepareStatement(GET_VILLAIN_NAMES);

        query.setInt(1,15);

        final ResultSet resultSet = query.executeQuery();

        if (resultSet.next()) {
            String villain = resultSet.getString(COLUMN_LABEL_NAME);
            int count = resultSet.getInt(COLUMN_LABEL_MINIONS_COUNT);

            System.out.printf("%s %d",villain, count);
        }
        connection.close();
    }
}
