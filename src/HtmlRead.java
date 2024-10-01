import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {
        try {
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            String line;
            while ( (line = reader.readLine()) != null ) {
                while(line.contains("href")){
                    int end;
                    String link = "";
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


                    if((end = link.indexOf("\"")) >5) {
                        link = link.substring(0,end);
                    }
                    if((end = link.indexOf("'")) >5) {
                        link = link.substring(0,end);
                    }
                    if((end = link.indexOf(")")) >5) {
                        link = link.substring(0,end);
                    }
                    if((end = link.indexOf(" ")) >5) {
                        link = link.substring(0,end);
                    }
//                    System.out.println(link);
                    System.out.println(link);
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
