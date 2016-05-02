package br.com.datadev.captor.gui;

import br.com.datadev.captor.util.Janela;
import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author fcsilva
 */
public class Tray {

    private TrayIcon trayIcon;
    private Principal principal;

    public void Tray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = new ImageIcon(this.getClass().getClassLoader().getResource("Image-Capture-icon.png")).getImage();

            ActionListener actionListener = (ActionEvent e) -> {
                abrePrincipal();
            };

            ActionListener sairListener = (ActionEvent e) -> {
                if (Janela.cfmSimNao("Deseja realmente sair?", "Sair") == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            };

            PopupMenu popup = new PopupMenu();

            MenuItem executarItem = new MenuItem("Abrir tela principal");
            executarItem.setShortcut(new MenuShortcut('a'));
            executarItem.addActionListener(actionListener);
            popup.add(executarItem);

            popup.addSeparator();
            
            MenuItem sairItem = new MenuItem("Sair");
            sairItem.setShortcut(new MenuShortcut('s'));
            sairItem.addActionListener(sairListener);
            popup.add(sairItem);

            trayIcon = new TrayIcon(image, "Captor", popup);

            trayIcon.setImageAutoSize(true);

            trayIcon.addActionListener(actionListener);

            try {
                tray.add(trayIcon);
                trayIcon.displayMessage("Captor", "Clique aqui para abrir a janela principal", TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                System.err.println("Captor não pode ser adicionado no sistema.");
            }
        } else {
            System.err.println("Bandeja do sistema não é suportado.");
        }
    }

    private void abrePrincipal() {
        if (principal != null && principal.isVisible()) {
            principal.setState(Frame.NORMAL);
            principal.requestFocus();
        } else if (principal != null) {
            principal.setVisible(true);
            principal.requestFocus();
        } else {
            java.awt.EventQueue.invokeLater(() -> {
                principal = new Principal();
                principal.setVisible(true);
            });
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        new Tray().Tray();
    }
}
