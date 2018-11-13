import java.util.*;

public class JDriver {
  public static void main(String[] args) {
    try {
      if (args.length == 4) {
        WordSearch Test = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
        System.out.println(Test);
      }
      else if (args.length == 3) {
        WordSearch Test = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        System.out.println(Test);
      }
      else {
        System.out.println("Make sure you have correct arguments after 'java JDriver' (row) (cols) (filename) (randSeed)");
      }
    }
    catch(Exception E) {
      E.printStackTrace();
    }
  }
}
