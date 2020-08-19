import java.util.ArrayList;

public class Package {
    String packageName;
    String packageDescription;
    ArrayList<String> packageDependencies = new ArrayList<>();
    ArrayList<String> packageRevDependencies = new ArrayList<>();

    public Package(String name, String description, ArrayList<String> dependencies, ArrayList<String> revDependencies){
        name = packageName;
        description = packageDescription;
        dependencies = packageDependencies;
        revDependencies = packageRevDependencies;
    }


}
