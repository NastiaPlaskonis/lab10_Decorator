package ucu.edu.ua;

import java.io.IOException;
import java.sql.SQLException;

public interface Document {
    String parse() throws IOException, SQLException;
}