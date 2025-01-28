package com.company;

import java.util.Scanner;

public abstract class GAME {
  enum State {
    PLAYING,
    LOSE,
    WIN
  }

  State state;
  String name;
  String description;

  public GAME(String name, String description) {
    this.name = name;
    this.description = description;
    this.state = State.PLAYING;
  }

  public String start() {
    return "Zaczynamy: " + description;
  }

  public String quitMessage() {
    switch (state) {
      case LOSE:
        return "Przegranko";
      case WIN:
        return "Gratulacje twoje jest wygranko";
      default:
        return "Żegnam";
    }
  }
  // można coś więcej
}
