package testheap;
import storage.Authenticator;
import java.util.Scanner;

public class AuthenticatorClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean tryAgainFlag =  false;

        System.out.println("Enter Authenticator code words : ");
        String codes = scanner.nextLine();

        Authenticator auth = new Authenticator(codes.split(" "));

        do {
            System.out.println("Enter index to get code : ");
            int i = scanner.nextInt();
            System.out.println("code for index " + i + " : " + auth.getCode(i));

            System.out.print("Do you want to try again (y/n) ? : ");
            String userChoice = scanner.next();
            tryAgainFlag = "y".equalsIgnoreCase(userChoice) ? true : false;
        } while (tryAgainFlag);

        scanner.close();
    }
}