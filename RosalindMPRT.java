import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RosalindMPRT: Created by uidr3144 on 12/01/2016.
 */
public class RosalindMPRT {

    private static final String LINK = "http://www.uniprot.org/uniprot/";
    private static final String EXTENSION = ".fasta";

    public static void main(String[] args) {

        for (int i = 0; i < args.length; ++i) {
            String proteinID = args[i];

            try {
                URL url = new URL(LINK + proteinID + EXTENSION);

                InputStream inputStream = url.openStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                String allText = "";
                boolean isFirst = false;
                line = bufferedReader.readLine(); //first line is not interesting
                while ((line = bufferedReader.readLine()) != null) {
                    allText += line;
                }

                Pattern pattern = Pattern.compile("N[^P](S|T)[^P]");
                Matcher matcher = pattern.matcher(allText);

                while (matcher.find()) {
                    System.out.println(matcher.start());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
