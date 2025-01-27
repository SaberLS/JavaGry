package com.company;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GuessWord guessNumber = new GuessWord(5, "jeb");

        guessNumber.play();
    }
}
