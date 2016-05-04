package br.com.datadev.captor.gui;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fcsilva
 */
public class Mensagem implements Runnable {

    private volatile boolean executar = true;
    private final String destino;
    private static final int INTERVALO = 180;

    public Mensagem(String destino) {
        this.destino = destino;
    }

    public void setExecutar(boolean executar) {
        this.executar = executar;
    }

    @Override
    public void run() {
        while (executar) {
            if (SystemTray.isSupported()) {
                TrayIcon tray = SystemTray.getSystemTray().getTrayIcons()[0];
                tray.displayMessage("Captor", "Sua atividade está sendo registrada em " + destino, TrayIcon.MessageType.INFO);
            }
            try {
                TimeUnit.SECONDS.sleep(INTERVALO);
            } catch (InterruptedException ex) {
                executar = false;
            }
        }
    }
}
