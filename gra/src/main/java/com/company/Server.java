package com.company;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {

  static GameInterface gry[];
  static String gryWidok[] = new String[1000];

  public static void main(String[] args) throws IOException {

    Server.gry = new GameInterface[2];

    // --------------------------------------------------------------------------
    Server.gry[0] = new GuessNumber(9, 6);
    Server.gry[1] = new GuessNumber(9, 6);

    for (GameInterface game : Server.gry) {
      System.out.println(game.nextStep("2"));
    }
    // ----------------------------------------------------------------------------

    // http://localhost:8080/home?n=<id>_<param>
    // http://localhost:8080/home?n=3_2
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); // localhost:8080/home lub
                                                                           // http://127.0.0.1:8080 :5555
    server.createContext("/home", new MyHandler());
    System.out.println(">> Server start");
    server.start();
  }

  static class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

      String query = exchange.getRequestURI().getQuery();
      System.out.println(query);

      String param[] = new String[2];
      int id = 0;
      String val = "";
      if (query != null) {
        param = query.substring(2).split("_");

        if (param.length == 2) {
          id = Integer.parseInt(param[0]);
          val = param[1];
        } else {
          id = -1;
          val = "";
        }
      }
      System.out.println("Param: " + val + " Id: " + id);

      int i = 0;
      for (GameInterface g : Server.gry) {
        if (id == i) {
          Server.gryWidok[i] = Server.gry[i].nextStep(val);
        } else {
          Server.gryWidok[i] = Server.gry[i].nextStep("");
        }
        i++;
      }

      String response = "<html>" +
          "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">"
          +
          "<div class=\"w3-panel w3-red\"><h1>Strona z gramia</h1></div>" +
          "<div class=\"w3-container\" style=\"display: flex; flex-flow: row wrap;" +
          "justify-content: center;\">";

      i = 0;
      for (GameInterface g : Server.gry) {
        response += "<div class=\"w3-card-4 w3-margin\" style=\"width:300px;\">" +
            "<div class=\"w3-panel w3-green w3-padding-16\">" + Server.gryWidok[i] +
            "</div>" +
            "<div class=\"w3-panel w3-blue w3-padding\"><input type=\"text\" id=\"id_" +
            i + "\"></div>" +
            "<div class=\"w3-panel w3-blue w3-padding\"><button class=\"w3-button" +
            " w3-orange w3-round\" onclick=\"dalej("
            + i + ")\">";
        switch (g.isPlaying()) {
          case true:
            response += "Dalej";
            break;
          case false:
            response += "Restart";
            break;
        }
        response += "</button></div>" +
            "</div>";
        i++;
      }

      response += "</div>" +
          "<script>" +
          "function dalej(i){ " +
          "var nextUrl = \"/home?n=\" ;" +
          "var number = i.toString() + \"_\" + document.getElementById(\"id_\"+" +
          "i).value; " +
          "window.location.href = nextUrl + number;" +
          "}" +
          "</script>" +
          "</html>";

      System.out.println(response);
      exchange.sendResponseHeaders(200, response.length());
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }

}
