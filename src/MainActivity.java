public class MainActivity {
    public static void main(String[] args) throws Exception {
        Process p = new Process();
        p.processFile("/var/lib/dpkg/status");
        if(!p.isLinux){
            p.processFile("C:\\Users\\Bautista Cardenau\\Desktop\\data.txt");
        }

        p.createHtml();
    }
}