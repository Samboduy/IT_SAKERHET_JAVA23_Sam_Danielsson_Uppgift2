import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private static final String MY_URI = "http://localhost:8080/users/user/sam.danielsson@gritacademy.se";
    public static void main(String[] args) {
        requestUserUsingEmail();

    }
    private static void requestUserUsingEmail(){
        HttpClient httpClient = HttpClient.newHttpClient();
        try{
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(MY_URI)).GET().build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
            JSOn myObject = new JSONObject


            System.out.println("Response: " + httpResponse.statusCode() + " " +  httpResponse.body());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
