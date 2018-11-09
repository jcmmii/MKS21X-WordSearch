public class JDriver {
  public static void main(String[] args) {
    WordSearch A = new WordSearch(5,5);
    System.out.println("Initial Wordsearch: \n" + A);
    System.out.println("Adding \"hey\" @ 0,0 horizontally(T): " + A.addWordHorizontal("hey",0,0));
    System.out.println(A);

    System.out.println("Adding \"hello\" @ 0,0 vertically(T): " + A.addWordVertical("hello",0,0));
    System.out.println(A);

    System.out.println("Adding \"good\" @ 3,0 horizontally(F): " + A.addWordHorizontal("good",0,0));
    System.out.println(A);

    System.out.println("Adding \"no\" @ 4,3 horizontally(T): " + A.addWordHorizontal("no",4,3));
    System.out.println(A);

    System.out.println("Adding \"yeah\" @ 0,2 vertically(T): " + A.addWordVertical("yeah",0,2));
    System.out.println(A);

    System.out.println("Adding \"notgonnawork\" @ 10,2 vertically(F): " + A.addWordVertical("yeah",10,2));
    System.out.println(A);

    System.out.println("Adding \"notgonnawork\" @ 10,2 vertically(F): " + A.addWordVertical("yeah",10,2));
    System.out.println(A);

    System.out.println("Adding \"test\" @ 5,0 horizontally(F): " + A.addWordHorizontal("test",5,0));
    System.out.println(A);


    System.out.println("Adding \"eeHH\" @ 0,1 diagonally(T): " + A.addWordDiagonal("eeHH",0,1));
    System.out.println(A);

    System.out.println("Adding \"yolo\" @ 0,2 diagonally(F): " + A.addWordDiagonal("yolo",0,2));
    System.out.println(A);

    System.out.println("Adding \"octopus\" @ 4,0 diagonally(F): " + A.addWordDiagonal("ocotopus",4,0));
    System.out.println(A);

    System.out.println("Adding \"yes\" @ 0,2 diagonally(F): " + A.addWordDiagonal("yes",0,2));
    System.out.println(A);
  }
}
