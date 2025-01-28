package com.company;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    Scanner scr = new Scanner(System.in);
    GuessWord wordGame = new GuessWord(scr, 5, "odpowiedz");

    GuessNumber numberGame = new GuessNumber(scr, 9, 6);

    wordGame.play();
    numberGame.play();
    scr.close();
  }
}
