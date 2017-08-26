/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientefirebase;

import com.google.android.gcm.server.Endpoint;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author lrey
 */
public class MessageSender extends Sender {

    private final String key;

    public MessageSender(String key) {
        super(key, Endpoint.FCM);
        this.key = key;
    }

    @Override
    protected HttpURLConnection getConnection(String url) throws IOException {
        String fcmUrl = "https://fcm.googleapis.com/fcm/send";
        HttpURLConnection cnn = (HttpURLConnection) new URL(fcmUrl).openConnection();
        cnn.setRequestProperty("Content-type", "application/json");
        cnn.setRequestProperty("Authorization", "key=" + key);

        return cnn;
    }
}
