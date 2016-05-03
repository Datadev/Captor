package br.com.datadev.captor;

import br.com.datadev.captor.util.CursorEnum;
import br.com.datadev.captor.util.FormatoEnum;
import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author fcsilva
 */
public class Captura {

    private final String destino;
    private final FormatoEnum formato;

    public Captura(String destino, FormatoEnum formato) {
        this.destino = destino;
        this.formato = formato;
    }

    public void capturar() {
        this.capturar(CursorEnum.vermelho);
    }

    public void capturar(CursorEnum cursorEnum) {
        try {
            String resourceName = cursorEnum.getLabel();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Image cursor = ImageIO.read(loader.getResourceAsStream(resourceName));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] screens = ge.getScreenDevices();

            Rectangle allScreenBounds = new Rectangle();
            for (GraphicsDevice screen : screens) {
                Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();

                allScreenBounds.width += screenBounds.width;
                allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
            }

            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);

            Graphics2D graphics2D = screenShot.createGraphics();
            graphics2D.drawImage(cursor, x, y, 16, 16, null); // cursor.gif com 16x16.

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date now = new Date();
            String strDate = sdfDate.format(now);

            String saida = strDate + "." + formato.name();

            ImageIO.write(screenShot, formato.name(), new File(destino + File.separator + saida));
        } catch (AWTException | HeadlessException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
