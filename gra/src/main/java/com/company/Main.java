package com.company;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GuessNumber guessNumber = new GuessNumber(4, 888);

        guessNumber.play();
    }
}
