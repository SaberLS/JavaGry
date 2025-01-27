package com.company;

import java.util.Scanner;

public interface GuessInterface<T> {

  public int triesLeft();

  public void guess(T guessed);

  public String attemptMessage();

  public T getUserInput(Scanner scr);

  public void attempt(Scanner scr);
}
