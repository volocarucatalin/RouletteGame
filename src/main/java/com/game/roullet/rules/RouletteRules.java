package com.game.roullet.rules;

import java.util.Arrays;
import java.util.List;

public interface RouletteRules {
    int MIN = 0;
    int MAX = 36;
    String ROW = "row";
    String COLUMN = "column";
    String RED = "red";
    String BLACK = "black";
    String ODD = "odd";
    String EVEN = "even";
    String NUMBER = "number";
    List<Integer> red = Arrays.asList(1, 3, 5, 9, 7, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);

    boolean isRow(int spinNumber, int betTypeValue);

    boolean isColumn(int spinNumber, int betTypeValue);

    boolean isOdd(int spinNumber);

    boolean isTheSameNumber(int spinNumber, int betTypeValue);

    boolean isRed(int spinNumber);
}
