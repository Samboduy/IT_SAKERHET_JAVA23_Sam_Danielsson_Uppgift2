import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String BASEURL = "http://localhost:8080";
    private static JWT token = new JWT();
    private static final HashPassword hashPassword = new HashPassword();
    private static boolean programIsRunning = true;
    private static boolean loggedIn = false;
    private static List<Object> timecapsules = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Encryption encryption = new Encryption();
        encryption.genKey();
        try{
            encryption.genKey();
        }catch (Exception e){
            System.out.println(e.getMessage());;
        }



        System.out.println(token.getToken());
        Scanner scanner = new Scanner(System.in);
        while (programIsRunning) {
            System.out.printf("1.Register\n2.Login\n3.Create a timecapsule\n4.Read a timecapsule" +
                    "\n5.Quit\n");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    System.out.printf("Enter your email (only used to be able to login):\n");
                    String email = scanner.nextLine();
                    System.out.printf("Enter your password (only used to be able to login):\n");
                    String password = scanner.nextLine();
                    registerUser(email, password);
                    System.out.println("you can now Login!");
                    break;
                    case "2":
                        System.out.printf("Please write your email:\n");
                        email = scanner.nextLine();
                        System.out.printf("Please write your password:\n");
                        password = scanner.nextLine();
                        loginUser(email, password);
                        System.out.println(token.getToken());
                        break;
                        case "3":
                            if (loggedIn) {
                                System.out.printf("Write you message:\n");
                                String message = scanner.nextLine();
                                String encryptedMessage = encryption.encrypt(message);
                                HttpResponse<String> response = createNewTimecapsule(token.getToken(),encryptedMessage);

                            }else {
                                System.out.println("You are not logged in! Log in First");
                            }
                            break;
                case "4":
                    if (loggedIn){
                        getAllUserTimecapsules(token.getToken());
                        System.out.println("write the id of the timecapsule you want to read");
                        long id = Long.parseLong(scanner.nextLine());
                        HttpResponse<String> response =  getOneTimecapsuleMessage(id,token.getToken());
                        String message = encryption.decrypt(response.body());
                        System.out.println("message: " + message);
                    }
                    else {
                        System.out.println("You are not logged in! Log in First");
                    }
                    break;
                case "5":
                    programIsRunning = false;

            }
        }
       /* System.out.println("Hello world!");
        loginUser("sam.danielsson@gritacademy.se","hsdgsdgds");*/
    }


    private static HttpResponse<String> getOneTimecapsuleMessage(long id, String token) {
        try{
            String URL = BASEURL + "/get/one/timecapsules/message";
            String json = "{\"token\":" +"\""+ token +"\", " +"\"messageId\":" +"\""+ id +"\" }";
            HttpResponse<String> response = postTimecapsule(URL,json);
            return response;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    private static void getAllUserTimecapsules(String token) {
        try{
            String URL = BASEURL + "/get/all/timecapsules";
            String json = "{\"token\":" +"\""+ token +"\"}";

            HttpResponse<String> response = postTimecapsule(URL,json);
            assert response != null;
            timecapsules = Collections.singletonList(response.body());

            System.out.println(timecapsules);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static HttpResponse<String> postTimecapsule( String URL,String json) {
        try{
            HttpClient httpClient =HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            return response;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static HttpResponse<String> createNewTimecapsule(String token, String message) {
        try{
            String URL = BASEURL + "/timecapsule/create";
            String json = "{\"token\":" +"\""+ token +"\", " +"\"message\":" +"\""+ message +"\" }";

            HttpResponse<String> response = postTimecapsule(URL,json);
            System.out.println(response);
            return response;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void registerUser(String email,String password){

        String createUserURL = BASEURL  + "/createuser";
        String hashedPassword = hashPassword.generateHash(password);
        postUser(email,hashedPassword,createUserURL);
    }

    private static void loginUser(String email,String password){
        String loginURL = BASEURL  + "/user/login/";
        String hashedPassword = hashPassword.generateHash(password);
        System.out.println(email + " " + password);
       HttpResponse<String> response =  postUser(email,hashedPassword,loginURL);
        System.out.println(response.body());
        token.setToken(response.body());
        if (response.statusCode() == 200){
            loggedIn = true;
            System.out.println("Logged in!");
        }

    }

    private static HttpResponse<String> postUser(String email,String hashedPassword, String URL){
        try{
            String json = "{\"email\":" +"\""+ email +"\", " +"\"password\":" +"\""+ hashedPassword +"\" }";
            HttpClient httpClient =HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            return response;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}