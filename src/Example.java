import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {
        ArrayList<String> PackageNames = new ArrayList<String>();
        ArrayList<String> PackageDescriptions = new ArrayList<String>();
        try {
            String result = "";
            FileReader fr = new FileReader("/var/lib/dpkg/status");
            int r = 0;
            while ((r = fr.read()) != -1) {
                result += (char) r;  //prints the content of the file
            }
            String[] splitResult = result.split("Package: ");
            System.out.println(splitResult.toString());  //Chequear que es lo que esta dividiendo

            Pattern p = Pattern.compile("Package: \"(.*?)\" Status:");
            Matcher m = p.matcher(splitResult[0]);
            int i = 0;

            while (m.find()) {
                PackageNames.add(m.group(1));
                System.out.println("Package: " + PackageNames.get(i));
                i++;
            }

            p = Pattern.compile("Description: \"(.*?)\" Homepage:");
            m = p.matcher(splitResult[0]);
            i = 0;

            while (m.find()) {
                PackageDescriptions.add(m.group(1));
                System.out.println("Description: " + PackageDescriptions.get(i));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
