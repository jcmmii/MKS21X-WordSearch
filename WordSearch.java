import java.util.*; //Random, Scanner, ArrayList
import java.io.*; //File, FileNotFoundException

//handle exceptions!
  //ArrayIndexOutOfBounds
  //IllegalArgumentException

public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String> wordsToAdd;
    private ArrayList<String> wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
     public WordSearch (int rows, int cols, String filename, int randSeed, boolean answers) throws FileNotFoundException {
       if (rows < 0 || cols < 0) throw new IllegalArgumentException();
       data = new char[rows][cols];
       wordsToAdd = new ArrayList<String>();
       wordsAdded = new ArrayList<String>();
       ListWordsToAdd(filename);
       seed = randSeed;
       randgen = new Random(randSeed);
       clear();
       addAllWords();
       if (answers) {
         replaceUnderscores();
       } else {
         fillRandomLetters();
       }
      }

      // account for if randSeed arg is there

    private void ListWordsToAdd(String filename) {
      try {
        File z = new File(filename);
        Scanner in = new Scanner(z);
        while(in.hasNext()) {
          this.wordsToAdd.add(in.next().toUpperCase());
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

//this needs fixing
    private void fillRandomLetters() {
      String CharList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      for (int x = 0; x < data.length; x++) {
        for (int y = 0; y < data[x].length; y++) {
          int z = randgen.nextInt(26);
          if (data[x][y] == '_') data[x][y] = CharList.charAt(z);
        }
      }
    }


    private void replaceUnderscores() {
      for (int x = 0; x < data.length; x++) {
        for (int y = 0; y < data[x].length; y++) {
          if (data[x][y] == '_')  data[x][y] = ' ';
        }
      }
    }

//this is good
   /**Set all values in the WordSearch to underscores'_'*/
    private void clear() {
      for (int x = 0; x < data.length; x++) {
        for (int y = 0; y < data[x].length; y++) {
          data[x][y] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
    *@return a String with each character separated by spaces, and rows
    *separated by newlines.
    *'|' are used as boundaries of the grid
    *The words in the puzzle and the seed are listed at the botom
    */

//this is good
//get rid of brackets
    public String toString(){
      String ret = "";
       for (int x = 0; x < data.length; x++) {
         for (int y = 0; y < data[x].length; y++) {
           if (y == 0) ret = ret + "|";
           ret = ret + data[x][y] + " ";
           if (y == data[x].length-1) ret = ret + "|";
         }
         ret = ret + "\n";
       }
       ret = ret + "Words: " + wordsAdded + "(Seed: " + seed + ")";
       return ret;
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
        *The word is added in the direction rowIncrement,colIncrement
        *Words must have a corresponding letter to match any letters that it overlaps.
        *
        *@param word is any text to be added to the word grid.
        *@param row is the vertical locaiton of where you want the word to start.
        *@param col is the horizontal location of where you want the word to start.
        *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
        *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
        *@return true when: the word is added successfully.
        *        false when: the word doesn't fit, OR  rowIncrement and colIncrement are both 0,
        *        OR there are overlapping letters that do not match
        */
        /*[rowIncrement,colIncrement] examples:
         *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
         *[ 1,0] would add downwards because (row+1), with no col change
         *[ 0,-1] would add towards the left because (col - 1), with no row change
         */

//this should be good
//this is wack
    private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement) {
      int length = data[0].length;
      int height = data.length;
      int Strlen = word.length();
      if (col >= length || row >= height || row < 0 || col < 0 || word.length() == 0) return false;
      if (rowIncrement == 0 && colIncrement == 0 || colIncrement > 1 || colIncrement < -1 || rowIncrement > 1 || colIncrement < -1) return false;
      if (rowIncrement == -1 && row - Strlen < -1) return false;
      if (colIncrement == -1 && col - Strlen < -1) return false;
      if (rowIncrement == 1 && row + Strlen > height) return false;
      if (colIncrement == 1 && col + Strlen > length) return false;
      for (int x = 0; x < Strlen; x++) {
        if (data[row+(x*rowIncrement)][col+(x*colIncrement)] != '_' && data[row+(x*rowIncrement)][col+(x*colIncrement)] != word.charAt(x)) return false;
      }
      for (int y = 0; y < Strlen; y++) {
        data[row+(y*rowIncrement)][col+(y*colIncrement)] = word.charAt(y);
      }
      return true;
    }

//this might need fixing
    private void addAllWords() {
      int[] dir = new int[3];
      dir[0] = -1;
      dir[1] = 0;
      dir[2] = 1;
      int Attempts = 200;
      int Tries = 0;
      int worked = 0;
      String RWord;
      int a, b, x, RDir, CDir, o, p;
      while (Tries < Attempts && wordsToAdd.size() > 0) {

           a = randgen.nextInt(3);
           b = randgen.nextInt(3);
           RDir = dir[a]; // gets random dir
           CDir = dir[b];
           x = randgen.nextInt(wordsToAdd.size());
           RWord = wordsToAdd.get(x); // gets random word


           o = randgen.nextInt(data.length); // gets random start position
           p = randgen.nextInt(data[0].length);


        if (addWord(RWord,o,p,RDir,CDir)) {
          wordsToAdd.remove(RWord);
          wordsAdded.add(RWord);
          worked = 0;
          ++Tries;
        } else {
          ++Tries;
          worked = 1;
        }
      }
    }

    public static void Instructions() {
      System.out.println("This is the correct format for creating the WordSearch:");
      System.out.println("\tjava WordSearch (Int # of rows) (Int # of cols) (String filename) (Int seed) (key)");
      System.out.println("\t# of rows, # of cols, and filename are REQUIRED parameters. Seed and key are optional");
      System.out.println("\tThe seed must be from 0 to 10000 inclusive, rows and columns must be greater than 0");
      System.out.println("\tPrint the answer key (without random letters) by typing in the word \"key\" as the fifth parameter");
    }

    public static void main(String[] args) {
      try {
        WordSearch WSAnswers = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), true);
        WordSearch WSNoAnswers = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
        if (args.length == 5) {
          if (args[4].equals("key") && (Integer.parseInt(args[3]) >= 0 && Integer.parseInt(args[3]) <= 10000) && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
            System.out.println(WSAnswers);
            }
          if (!args[4].equals("key") && (Integer.parseInt(args[3]) >= 0 && Integer.parseInt(args[3]) <= 10000) && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
            System.out.println(WSNoAnswers);
          }
        }
        else if (args.length == 4) {
          if (Integer.parseInt(args[3]) >= 0 && Integer.parseInt(args[3]) <= 10000 && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
            System.out.println(WSNoAnswers);
            }
        }
        else if (args.length == 3) {
          if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
            Random rng = new Random();
            int ZeroTo10K = rng.nextInt(10000);
            WordSearch WSRandom = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], ZeroTo10K, false);
            System.out.println(WSRandom);
          }
        }
        else {
          if (args.length > 5) System.out.println("Error: You have too many arguments!");
          if (args.length < 3) System.out.println("Error: You are missing some arguments!");
          Instructions();
        }
      }
        catch(Exception E) {
          E.printStackTrace();
          if (Integer.parseInt(args[3]) < 0 || Integer.parseInt(args[3]) > 10000) System.out.println("Error: Seed must be between 0 and 10000 inclusive!");
          if (Integer.parseInt(args[0]) <= 0) System.out.println("Error: rows must be greater than 0");
          if (Integer.parseInt(args[1]) <= 0) System.out.println("Error: columns must be greater than 0");
          Instructions();
        }
      }
    }



//ILLEGAL ARGUMENT FORMAT?
//pos rows

//fillRandomLetters, replaceUnderscores()

//get rid of this
  /*   public WordSearch (int rows, int cols, String filename) throws FileNotFoundException {
       long clock = System.currentTimeMillis();
       int time = (int)clock;
       this(rows,cols,filename,time);
     }
 // K is testing 0 constructors, methods
 // helper methods ok
 // One constructor okay -> can test if seed, boolean is there

 // Wordsearch(filename, rows,cols, seeds,  boolean key
 // main can call in constructor to fill in approriate values
 // constructor:
//  wordsToAdd = getWords(filename);
// if (!key) {
    //fillRandomLetters();
}

// make everything caps
// main method calls constructors and bases calls off args length

// no exceptions in shell
// all errors should be caught and handled
// if things don't parse, args length not right, print errors
*/
