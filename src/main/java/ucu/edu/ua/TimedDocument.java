package ucu.edu.ua;

import java.io.IOException;
import java.sql.SQLException;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class TimedDocument implements Document {
    public String path;

    @Override
    public String parse() throws IOException, SQLException {

        String smartTxt = calculatingTime(new SmartDocument(path));
        String cachedTxt = calculatingTime(new CachedDocument(path));

        return cachedTxt != null ? cachedTxt : smartTxt;
    }

    private String calculatingTime(Document txt) throws IOException, SQLException {
        
        long beginning = System.currentTimeMillis();
        String res = txt.parse();
        long ending = System.currentTimeMillis();

        String type;
        if (txt instanceof SmartDocument) {
            type = "SmartDocument";
        } else {
            type = "CachedDocument";
        }

        System.out.println("Time of " + type + ": " + (ending - beginning) / 1000.0);

        return res;
    }
}