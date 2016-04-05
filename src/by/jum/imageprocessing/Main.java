package by.jum.imageprocessing;

import by.jum.imageprocessing.ui.MainWindow;
import by.jum.imageprocessing.utils.constants.Path;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        PropertyConfigurator.configure(Path.LOG4J_CONF_PATH.getPath());
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.createWindow().createMenu();
            mainWindow.addImagePane();
            mainWindow.addToolBar();
        });
    }
}
