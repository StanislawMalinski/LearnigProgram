package Util;

import BackEnd.LearningModePackage.Flashcards;

import java.awt.*;

public abstract class Information {
    // MONITOR SIZE
    public static final double HEIGHT_OF_MONITOR = getHeight();
    public static final double WIDTH_OF_MONITOR = getWidth();

    // ANSWER PLATFORM SIZE
    private static final double RATIO_W_ANSWER_PLATFORM_PANE = 700.0 / 1440;
    private static final double RATIO_H_OF_ANSWER_PLATFORM_PANE = 770.0 / 900;

    public static final double WIDTH_OF_ANSWER_PLATFORM_PANE = WIDTH_OF_MONITOR * RATIO_W_ANSWER_PLATFORM_PANE;
    public static final double HEIGHT_OF_ANSWER_PLATFORM_PANE = HEIGHT_OF_MONITOR * RATIO_H_OF_ANSWER_PLATFORM_PANE;

    public static final double RATIO_Y_OF_ANSWER_PLATFORM_PANE = 50.0 / 1440;
    public static final double RATIO_X_OF_ANSWER_PLATFORM_PANE = 30.0 / 90;

    public static final double Y_OF_ANSWER_PLATFORM_PANE = WIDTH_OF_MONITOR * RATIO_X_OF_ANSWER_PLATFORM_PANE;
    public static final double X_OF_ANSWER_PLATFORM_PANE = HEIGHT_OF_MONITOR * RATIO_Y_OF_ANSWER_PLATFORM_PANE;

    // FLASHCARD SIZE
    private static final double RATIO_W_FLASHCARD_PANE = 250.0/700.0;
    private static final double RATIO_H_FLASHCARD_PANE = 120.0/770.0;

    public static final double WIDTH_OF_FLASHCARD_PANE = WIDTH_OF_ANSWER_PLATFORM_PANE * RATIO_W_FLASHCARD_PANE;
    public static final double HEIGHT_OF_FLASHCARD_PANE =  HEIGHT_OF_ANSWER_PLATFORM_PANE * RATIO_H_FLASHCARD_PANE;

    private static final double RATIO_X_FLASHCARD_PANE = 222.0/700.0;
    private static final double RATIO_Y_FLASHCARD_PANE = 246.0/770.0;

    public static final double X_OF_FLASHCARD_PANE = WIDTH_OF_ANSWER_PLATFORM_PANE * RATIO_X_FLASHCARD_PANE;
    public static final double Y_OF_FLASHCARD_PANE =  HEIGHT_OF_ANSWER_PLATFORM_PANE * RATIO_Y_FLASHCARD_PANE;

        //FLASHCARDS

    private static double getHeight(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return size.getHeight();
    }

    private static double getWidth(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return size.getWidth();
    }

    public static String locationOfTheSubjects = "FileKeeper/Subjects/";

    public static String loactionOfTheStats = "FileKeeper/Stats/";
}


