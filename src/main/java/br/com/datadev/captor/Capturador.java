package br.com.datadev.captor;

import br.com.datadev.captor.gui.Mensagem;
import br.com.datadev.captor.gui.TrayIcone;
import br.com.datadev.captor.util.CursorEnum;
import br.com.datadev.captor.util.FormatoEnum;
import java.util.concurrent.TimeUnit;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

/**
 *
 * @author fcsilva
 */
public class Capturador implements Runnable {

    private final int intervalo;
    private final String destino;
    private final FormatoEnum formato;
    private volatile boolean executar = true;

    public Capturador(int intervalo, String destino, FormatoEnum formato) {
        this.intervalo = intervalo;
        this.destino = destino;
        this.formato = formato;
    }

    public void setExecutar(boolean executar) {
        this.executar = executar;
    }

    @Override
    public void run() {
        final Captura captura = new Captura(destino, formato);

        GlobalMouseAdapter globalMouseAdapter = new GlobalMouseAdapter() {
            @Override
            public void mousePressed(GlobalMouseEvent event) {
            }

            @Override
            public void mouseReleased(GlobalMouseEvent event) {
                captura.capturar(CursorEnum.azul);
            }

            @Override
            public void mouseMoved(GlobalMouseEvent event) {
            }

            @Override
            public void mouseWheel(GlobalMouseEvent event) {
            }
        };

        GlobalMouseHook mouseHook = new GlobalMouseHook();
        mouseHook.addMouseListener(globalMouseAdapter);

        TrayIcone trayIcone = new TrayIcone();
        Thread threadTrayIcone = new Thread(trayIcone);
        threadTrayIcone.start();

        Mensagem mensagem = new Mensagem(destino);
        Thread threadMensagem = new Thread(mensagem);
        threadMensagem.start();

        while (executar) {
            captura.capturar(CursorEnum.vermelho);
            try {
                TimeUnit.SECONDS.sleep(intervalo);
            } catch (InterruptedException ex) {
                threadTrayIcone.interrupt();
                threadMensagem.interrupt();
                executar = false;
            }

        }

        mouseHook.removeMouseListener(globalMouseAdapter);
    }
}
