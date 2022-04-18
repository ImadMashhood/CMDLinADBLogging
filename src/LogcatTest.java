import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LogcatTest {
    public static void main(String[] args) {
        adbCommandBuilder();
    }

    public static void adbCommandBuilder(){
        String command = "";
        Scanner sc= new Scanner(System.in);
        System.out.println("             Welcome to the ADB Log Tool              ");
        System.out.println("Would you like to search for devices on network? (Y/N)");
        if(sc.nextLine().equalsIgnoreCase("y")){
            adbCommandProccess("adb devices");
        }
        System.out.println("Enter device IP would like to connect to: ");
        String ip = sc.nextLine();
        command = "adb connect "+ ip;
        adbCommandProccess(command);
        System.out.println("Would you like to record Logs into a text file? (Y/N)");
        if(sc.nextLine().equalsIgnoreCase("y")){
            command = "cd Documents";
            System.out.println("Enter file name: ");
            String fileName = sc.nextLine();
            command = "adb logcat > "+fileName+".txt";
        }
        else{
            command = "adb logcat";
        }
        adbCommandProccess(command);
    }

    public static void adbCommandProccess(String command) {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", command);
        try {
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
            p.destroy();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
