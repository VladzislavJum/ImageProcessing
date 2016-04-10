package by.jum.imageprocessing.ui;

import by.jum.imageprocessing.processing.FilterLoader;
import by.jum.imageprocessing.processing.ImageLoaderSaver;
import by.jum.imageprocessing.processing.ImageScanner;
import by.jum.imageprocessing.utils.constants.MenuConstants;
import by.jum.imageprocessing.utils.constants.Path;
import org.apache.log4j.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainWindow {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class);
    private final ImageLoaderSaver loaderSaver = new ImageLoaderSaver();
    private JFrame window;
    private BufferedImage image;
    private JLabel originImageLabel = new JLabel();
    private JLabel newImageLabel = new JLabel();
    private FilterLoader filterLoader = new FilterLoader();

    public MainWindow() {
        loaderSaver.loadImage(Path.DEFAULT_IMAGE.getPath());
        image = loaderSaver.getImage();
        originImageLabel.setIcon(new ImageIcon(image));
        newImageLabel.setIcon(originImageLabel.getIcon());
    }

    public MainWindow createWindow() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        window = new JFrame("Image Processing");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        window.setLocationRelativeTo(null);
        window.setResizable(true);

        window.setVisible(true);
        return this;
    }

    public void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu filtersMenu = new JMenu(MenuConstants.PROCESSING.getName());
        JMenu fileMenu = new JMenu(MenuConstants.FILE.getName());
        JMenu segmentationMenu = new JMenu(MenuConstants.SEGMENT.getName());


        JMenuItem loadItem = new JMenuItem(MenuConstants.LOAD_IMAGE.getName());
        JMenuItem saveItem = new JMenuItem(MenuConstants.SAVE_IMAGE.getName());
        addFileChooser(loadItem);

        JMenuItem filter1Item = new JMenuItem(MenuConstants.FILTER_1.getName());
        JMenuItem filter2Item = new JMenuItem(MenuConstants.FILTER_2.getName());
        JMenuItem filter3Item = new JMenuItem(MenuConstants.FILTER_3.getName());
        JMenuItem anaglifItem = new JMenuItem(MenuConstants.ANAGLIF.getName());
        JMenuItem segmentationItem20 = new JMenuItem("20");
        JMenuItem segmentationItem60 = new JMenuItem("60");
        JMenuItem segmentationItem100 = new JMenuItem("100");

        segmentationItem20.setActionCommand("20");
        segmentationItem60.setActionCommand("60");
        segmentationItem100.setActionCommand("100");

        filter1Item.setActionCommand(Path.FILTER1.getPath());
        filter2Item.setActionCommand(Path.FILTER2.getPath());
        filter3Item.setActionCommand(Path.FILTER3.getPath());

        filter1Item.addActionListener(e -> {
            ImageScanner imageScanner = new ImageScanner(image);
            newImageLabel.setIcon(new ImageIcon(imageScanner.negativ()));});

        filter2Item.addActionListener(e -> setFilter(e.getActionCommand()));

        filter3Item.addActionListener(e -> setFilter(e.getActionCommand()));

        anaglifItem.addActionListener(e -> {
            ImageScanner imageScanner = new ImageScanner(image);
            newImageLabel.setIcon(new ImageIcon(imageScanner.anaglyphImage()));
        });

        segmentationItem20.addActionListener(e -> {
            ImageScanner imageScanner = new ImageScanner(image);
            newImageLabel.setIcon(new ImageIcon(imageScanner.segmentationImage(Integer.valueOf(e.getActionCommand()))));
        });

        segmentationItem60.addActionListener(e -> {
            ImageScanner imageScanner = new ImageScanner(image);
            newImageLabel.setIcon(new ImageIcon(imageScanner.segmentationImage(Integer.valueOf(e.getActionCommand()))));
        });

        segmentationItem100.addActionListener(e -> {
            ImageScanner imageScanner = new ImageScanner(image);
            newImageLabel.setIcon(new ImageIcon(imageScanner.segmentationImage(Integer.valueOf(e.getActionCommand()))));
        });

        fileMenu.add(loadItem);
//        fileMenu.add(saveItem);
        filtersMenu.add(filter1Item);
        filtersMenu.add(filter2Item);
        filtersMenu.add(filter3Item);
        filtersMenu.add(anaglifItem);

        segmentationMenu.add(segmentationItem20);
        segmentationMenu.add(segmentationItem60);
        segmentationMenu.add(segmentationItem100);


        menuBar.add(fileMenu);
        menuBar.add(filtersMenu);
        menuBar.add(segmentationMenu);

        window.setJMenuBar(menuBar);
    }


    private void addFileChooser(JMenuItem loadItem) {
        loadItem.addActionListener(e -> {
            boolean isLoaded;
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("E:\\TestImages\\"));
            int result = jFileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                isLoaded = loaderSaver.loadImage(jFileChooser.getSelectedFile().getPath());
                if (isLoaded) {
                    image = loaderSaver.getImage();
                    ImageIcon imageIcon = new ImageIcon();
                    imageIcon.setImage(image);
                    originImageLabel.setIcon(imageIcon);
                    newImageLabel.setIcon(originImageLabel.getIcon());
                }
            } else {
                LOGGER.info("Loaded nothing");
            }
        });
    }

    public void addImagePane() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        JScrollPane originScrollPane = new JScrollPane(originImageLabel);
        JScrollPane newScrollPane = new JScrollPane(newImageLabel);

        panel.add(originScrollPane);
        panel.add(newScrollPane);
        window.add(panel, BorderLayout.CENTER);
    }

    public void addToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.setFloatable(false);

        window.add(toolBar, BorderLayout.NORTH);
    }

    private void setFilter(String pathFilter) {
        ImageScanner imageScanner = new ImageScanner(image);
        newImageLabel.setIcon(new ImageIcon(imageScanner.filterImage(
                filterLoader.getFilter(pathFilter), filterLoader.getDiv()
        )));
    }
}
