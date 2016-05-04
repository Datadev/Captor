package br.com.datadev.captor.gui;

import java.awt.Image;
import java.awt.SystemTray;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

/**
 *
 * @author fcsilva
 */
public class TrayIcone implements Runnable {

    private volatile boolean executar = true;
    private boolean flag;
    private final Image img1;
    private final Image img2;
    private static final int INTERVALO = 1;

    public TrayIcone() {
        this.flag = true;
        this.img1 = new ImageIcon(this.getClass().getClassLoader().getResource("Image-Capture-icon.png")).getImage();
        this.img2 = new ImageIcon(this.getClass().getClassLoader().getResource("exclamacao.png")).getImage();
    }

    public void setExecutar(boolean executar) {
        this.executar = executar;
    }

    @Override
    public void run() {
        java.awt.TrayIcon tray = SystemTray.getSystemTray().getTrayIcons()[0];
        while (executar) {
            if (SystemTray.isSupported()) {
                if (flag) {
                    tray.setImage(img2);
                    flag = false;
                } else {
                    tray.setImage(img1);
                    flag = true;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(INTERVALO);
            } catch (InterruptedException ex) {
                tray.setImage(img1);
                executar = false;
            }
        }
    }
}
