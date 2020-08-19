import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MainActivity {
    public static void main (String[] args) {
        ArrayList<String> packages = new ArrayList<String>();
        ArrayList<String> descriptions = new ArrayList<String>();
        HashMap<String, ArrayList<String>> dependencies = new HashMap<>();
        HashMap<String, ArrayList<String>> revDependencies = new HashMap<>();
        int i = 0;
        String packageStart = "Package: ";
        String descriptionStart = "Description: ";
        String dependenciesStart = "Depends: ";

        String packageName = "";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "/var/lib/dpkg/status"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);

                if (line.startsWith(packageStart)) {
                    packageName = line.substring(packageStart.length()).trim();
                    packages.add(packageName);
                }
                if (line.startsWith(descriptionStart)) {
                    descriptions.add(line.substring(descriptionStart.length()).trim());
                }
                if (line.startsWith(dependenciesStart)) {
                    String subline = line.substring(dependenciesStart.length());
                    String[] dependenciesWithVersion = subline.split(", ");
                    ArrayList<String> dependenciesWOVersion = new ArrayList<String>();
                    String currentDependency;

                    for (String dependencyWithVersion : dependenciesWithVersion) {
                        ArrayList<String> currentRevDependencies = new ArrayList<String>();
                        int index = dependencyWithVersion.indexOf("(");
                        currentRevDependencies.clear();

                        //Take the version out of the package name
                        if (index != -1) {
                            currentDependency = dependencyWithVersion.substring(0, index);
                        } else {
                            currentDependency = dependencyWithVersion;
                        }
                        currentDependency.trim();
                        dependenciesWOVersion.add(currentDependency);

                        int j =0;
                        //Check for previous reverse dependencies and save them
                        if (revDependencies.get(currentDependency) != null){
                            currentRevDependencies = revDependencies.get(currentDependency);
                        }

                        currentRevDependencies.add(packageName);
                        revDependencies.put(currentDependency, currentRevDependencies);

//                        System.out.print("Package: " + packageName + " Dependency: " + currentDependency + " RevDepencencies: " + revDependencies.get(currentDependency) +  "\n");

//                        System.out.print("Current Rev Dep: " + revDependencies.get(currentDependency) + "\n");

                    }
                    dependencies.put(packageName, dependenciesWOVersion);
//                    System.out.print("Dependencies of " + packageName + " are: " +  dependencies.get(packageName) + "\n");

                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String packageExample;
        int j = 0;
        while (j < 10){
            Random random = new Random();
            packageExample = packages.get(random.nextInt(100));
            System.out.print("Rev dependencies of " + packageExample + " are: " + revDependencies.get(packageExample) + "\n");
            j++;

        }

    }
}
