package by.jum.imageprocessing.processing;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class FilterLoader {
    private static final Logger LOGGER = Logger.getLogger(FilterLoader.class);
    private final Random random = new Random();
    private double filter[][];
    private int div = 3;

    public double[][] getFilter(String filePath) {

        Scanner filterScanner = null;
        try {
            filterScanner = new Scanner(new BufferedReader(new FileReader(filePath)));
            LOGGER.info("FilterLoader " + filePath + " loaded");
            int length = filterScanner.nextInt();
            div = filterScanner.nextInt();
            filter = new double[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    filter[i][j] = filterScanner.nextDouble();
                }
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot read file filter");
        }

        return filter;
    }

    public double[][] getRandomFilter(int length) {
        filter = new double[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                filter[i][j] = Math.random()*6-3;
            }
        }
        return filter;
    }

    public int getDiv() {
        return div;
    }
}
