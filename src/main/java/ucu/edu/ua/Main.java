package ucu.edu.ua;

import java.io.IOException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        try {

            String res = parsering(new CachedDocument("gs://cv-examples/wiki.png"));
            System.out.println(res);

            parsering(new TimedDocument("gs://cv-examples/wiki.png"));
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String parsering(Document txt) throws SQLException, IOException {
        return txt.parse();
    }
}
