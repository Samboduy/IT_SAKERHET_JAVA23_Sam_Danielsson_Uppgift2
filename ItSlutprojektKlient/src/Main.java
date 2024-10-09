import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    private static final String BASEURL = "http://localhost:8080";

    public static void main(String[] args) {
        System.out.println("Hello world!");
        registerUser("samdan","kotlin");
    }

    private static void registerUser(String email,String password){
        HashPassword hashPassword = new HashPassword();
        String createUserURL = BASEURL  + "/createuser";
        String hashedPassword = hashPassword.generateHash(password);
        try{
            HttpClient httpClient =HttpClient.newHttpClient();
            String json = "{\"email\":" +"\""+ email +"\", " +"\"password\":" +"\""+ hashedPassword +"\" }";
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(createUserURL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            assert(response.statusCode() == 201);

            httpClient.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}