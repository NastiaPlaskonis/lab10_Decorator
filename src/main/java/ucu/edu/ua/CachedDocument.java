package ucu.edu.ua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import java.io.IOException;

@AllArgsConstructor


public class CachedDocument implements Document {
    

    private final String path;

    @Override

    public String parse() throws SQLException, IOException {
        String text = gettingTxt(path);

        if (text == null) {
            text = new SmartDocument(path).parse();
            savingTxt(text);
        }

        return text;
    }


    public String gettingTxt(String path) throws SQLException {

        String selection = "selection the text";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement selectStatement = conn.prepareStatement(selection)) {
            selectStatement.setString(1, path);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getString("text") : null;
            }
        }
    }

    public void savingTxt(String text) throws SQLException {

        String insertion = "insertion the text";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        
             PreparedStatement insertStatement = conn.prepareStatement(insertion)) {
            insertStatement.setString(1, path);
            insertStatement.setString(2, text);
            insertStatement.executeUpdate();
        }
    }
}
