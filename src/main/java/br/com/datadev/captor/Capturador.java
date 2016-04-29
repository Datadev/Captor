package br.com.datadev.captor;

import br.com.datadev.captor.util.FormatosEnum;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fcsilva
 */
public class Capturador implements Runnable {

    private final int intervalo;
    private final String destino;
    private final FormatosEnum formato;
    private boolean executar = true;

    public Capturador(int intervalo, String destino, FormatosEnum formato) {
        this.intervalo = intervalo;
        this.destino = destino;
        this.formato = formato;
    }

    public void setExecutar(boolean executar) {
        this.executar = executar;
    }

    @Override
    public void run() {
        try {
            final Captura captura = new Captura(destino, formato);
            Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

                @Override
                public void eventDispatched(AWTEvent event) {
                    captura.capturar();
                }
            }, AWTEvent.MOUSE_EVENT_MASK);
            while (executar) {
                captura.capturar();
                TimeUnit.SECONDS.sleep(intervalo);
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
