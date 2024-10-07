import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {
    }
    public ArrayList readLink(String urlParam, String filerTerm) {
        String link = "";
        ArrayList<String> links = new ArrayList<String>();
        try {
            URL url = new URL(urlParam);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            String line;
            String keyword = "";
            while ((line = reader.readLine()) != null) {
                while (line.contains("href") && line.contains(keyword)) {
                    int end;
                    if (line.contains("href=\'//")) {
                        int start = line.indexOf("href=\'//") + 8;
                        link = line.substring(start);
                        line = line.substring(start);
                    } else if (line.contains("href=\'")) {
                        int start = line.indexOf("href=\'") + 6;
                        link = line.substring(start);
                        line = line.substring(start);

                    } else if (line.contains("href=\"")) {
                        int start = line.indexOf("href=\"") + 6;
                        link = line.substring(start);
                        line = line.substring(start);
                    }


                    if ((end = link.indexOf("\"")) > 5) {
                        link = link.substring(0, end);
                    }
                    if ((end = link.indexOf("'")) > 5) {
                        link = link.substring(0, end);
                    }
                    if ((end = link.indexOf(")")) > 5) {
                        link = link.substring(0, end);
                    }
                    if ((end = link.indexOf(" ")) > 5) {
                        link = link.substring(0, end);
                    }
                    if(link.contains(filerTerm)) {
                        links.add(link);
                    }

                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return links;
    }

}
