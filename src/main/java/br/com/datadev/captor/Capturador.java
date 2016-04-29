package br.com.datadev.captor;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fcsilva
 */
public class Capturador implements Runnable {

    @Override
    public void run() {
        try {
            final Captura captura = new Captura();
            Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

                @Override
                public void eventDispatched(AWTEvent event) {
                    captura.capturar();
                }
            }, AWTEvent.MOUSE_EVENT_MASK);
            while (true){
                captura.capturar();
                TimeUnit.SECONDS.sleep(10);
            }
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
