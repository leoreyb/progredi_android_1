/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientefirebase;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 *
 * @author lrey
 */
public class ClienteFirebase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String serverKey = "AAAAqpFGsII:APA91bEk10ClpFW24lRXQLjvmNrx9cdW65aTEt0zR0CnaIqlLWjiUsZAg7FkKJ3fL_oE9zXGGnNlq6fbGRxVW8SzqrpAfaKFcMjXa2uO45jckr8Ez69gTYyggxZlbJy1OXEbuoXGUoOb";
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Sender sender = new MessageSender(serverKey);
                    Message message = new Message.Builder()
                            .collapseKey("message")
                            .timeToLive(3)
                            .delayWhileIdle(true)
                            .addData("message", "Notification from Java application")
                            .build();

                    Result result = sender.send(message, "eydf92NSFdo:APA91bFzZ4EoNW23YA8gd4EuWErc-tu5oQBQpH-ycfqJ8R0lMceQHVlBJzH6dJtzGnDGFHNnhbDixXmH01F2yVuanUCA5c6IyyQLI_fRKMXbbenVIu_qB0bLx0-7wCqg74D1t0c-oB9H", 1);
                    System.out.println("Result: " + result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }
}
