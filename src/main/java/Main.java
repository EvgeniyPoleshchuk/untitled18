import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Arrays;

public class Main {
    public static Nasa nasa;

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request =
                new HttpGet("https://api.nasa.gov/planetary/apod?api_key=cjnqa21zqNcAgXs7EcsaswJfgDeoWVyKN8CPSBKi");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            nasa = mapper.readValue(response.getEntity().getContent(), Nasa.class);
            System.out.println(nasa);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (CloseableHttpResponse picture = httpClient.execute(new HttpGet(nasa.getUrl()))) {
            HttpEntity entity = picture.getEntity();
            String[] splitUrl = nasa.getUrl().split("/");
            String fileName = splitUrl[splitUrl.length - 1];
            if (entity != null) {
                FileOutputStream fos = new FileOutputStream(fileName);
                entity.writeTo(fos);
                fos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
