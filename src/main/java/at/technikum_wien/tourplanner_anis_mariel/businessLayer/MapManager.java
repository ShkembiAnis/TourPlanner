package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.image.BufferedImage;
import org.json.JSONObject;

import javax.imageio.ImageIO;

public class MapManager {
    private final static ILoggerWrapper logger = LoggerFactory.getLogger();

    public static String requestRoute(String start, String end) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL("http://www.mapquestapi.com/directions/v2/route?key=wrq6qJ05GD4w8ZMjbTsZ25C5matuLpNw&from=" + escapeCharacters(start) + "&to=" + escapeCharacters(end));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(0));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes("");
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            logger.error("Request route failed");
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static BufferedImage requestRouteImage(String start, String end) {
        String jsonString = requestRoute(start,end);
        if (jsonString == null){
            logger.error("JsonObject is null");
            return null;
        }
        JSONObject obj = new JSONObject(jsonString);
        if(!obj.getJSONObject("route").has("sessionId") && !obj.getJSONObject("route").has("boundingBox")) {
            logger.error("Wrong JsonObject");
            return null;
        }
        String session = obj.getJSONObject("route").getString("sessionId");
        JSONObject boundingBox = obj.getJSONObject("route").getJSONObject("boundingBox");
        try {
            String params;
            params = "&size=700,300";
            params += "&defaultMarker=none";
            params += "&zoom=11";
            params += "&rand=737758036";
            params += "&session="+session;
            String box = boundingBox.getJSONObject("lr").getFloat("lat") + "," + boundingBox.getJSONObject("lr").getFloat("lng") + "," + boundingBox.getJSONObject("ul").getFloat("lat") + "," + boundingBox.getJSONObject("ul").getFloat("lng");
            params += "&boundingBox="+box;

            URL url = new URL("http://www.mapquestapi.com/staticmap/v5/map?key=wrq6qJ05GD4w8ZMjbTsZ25C5matuLpNw" + params);
            InputStream is;
            try {
                is = url.openStream();
            }
            catch (Exception e){
                logger.error("Cant open Stream: " + e.getMessage());
                return null;
            }
            return ImageIO.read(is);

        } catch (IOException e) {
            logger.error("Cant create URL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static String escapeCharacters(String string) {
        string = string.replaceAll(" ","");
        string = string.replaceAll("ä","ae");
        string = string.replaceAll("ü","ue");
        string = string.replaceAll("ö","oe");
        string = string.replaceAll("Ä","Ae");
        string = string.replaceAll("Ü","Ue");
        string = string.replaceAll("Ö","Oe");
        string = string.replaceAll("\\.","");
        string = string.replaceAll(",","");
        string = string.replaceAll("/","");
        return string;
    }


}
