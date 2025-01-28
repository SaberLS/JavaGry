package com.company;

import java.util.Scanner;

public abstract class GAME implements GameInterface {
  State state;
  String name;
  String description;
  Scanner scanner;

  public GAME(Scanner scanner, String name, String description) {
    this.scanner = scanner;
    this.name = name;
    this.description = description;
    this.state = State.PLAYING;
  }

  public void lose() {
    this.state = State.LOSE;
  }

  public void win() {
    this.state = State.WIN;
  }

  public void startGame() {
    System.out.println("Zaczynamy: " + description);
    this.state = State.PLAYING;
  }

  public void endGame() {
    System.out.println("\n\t" + this.endMessage());
  }

  public Boolean isPlaying() {
    return this.state == State.PLAYING;
  }

  private String endMessage() {
    switch (this.state) {
      case LOSE:
        return "Przegranko";
      case WIN:
        return "Gratulacje twoje jest wygranko";
      default:
        return "Żegnam";
    }
  }
}
