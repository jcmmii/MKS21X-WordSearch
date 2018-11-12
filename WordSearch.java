import java.util.*; //Random, Scanner, ArrayList
import java.io.*; //File, FileNotFoundException

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
     public WordSearch (int rows, int cols, String filename) {
       if (rows < 0 || cols < 0) {
         throw new IllegalArgumentException();
       }
       data = new char[rows][cols];
       clear();
       Random randSeed = new Random();
     }

     public WordSearch (int rows, int cols, String filename, int randSeed) {
       if (rows < 0 || cols < 0) {
         throw new IllegalArgumentException();
       }
       data = new char[rows][cols];
       clear();
     }

   /**Set all values in the WordSearch to underscores'_'*/
    public void clear() {
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

//
//add list of words after words AND THE SEED
//
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
       ret = ret + "Words: ";
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

    private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement) {
      int length = data[0].length;
      int height = data.length;
      int Strlen = word.length();
      if (col >= length || row >= height || row < 0 || col < 0) return false;
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


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical location of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
     private boolean addWordHorizontal(String word, int row, int col) {
       int len1 = data[0].length;
       int len2 = data.length;
       if (row >= len2 || col >= len1 || row < 0 || col < 0 || word.length() <= 0) {
        return false; // out of bounds
       }
       int Strlen = word.length();
       if (Strlen + col > len1) return false;
       for (int x = 0; x < Strlen; x++) {
         if (data[row][col+x] != '_' && data[row][col+x] != word.charAt(x)) {
            return false;
          }
        }
       for (int y = 0; y < Strlen; y++) {
          data[row][col+y] = word.charAt(y);
        }
      return true;
      }

     /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added from top to bottom, must fit on the WordGrid, and must
    *have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@return true when the word is added successfully. When the word doesn't fit,
    *or there are overlapping letters that do not match, then false is returned.
    *and the board is NOT modified.
    */
   private boolean addWordVertical(String word,int row, int col){
     int len1 = data[0].length;
     int len2 = data.length;
     if (row >= len2 || col >= len1 || row < 0 || col < 0 || word.length() <= 0) {
       return false; // out of bounds
     }
     int Strlen = word.length();
     if (Strlen + row > len2) return false;
     for (int x = 0; x < Strlen; x++) {
       if (data[row+x][col] != '_' && data[row+x][col] != word.charAt(x)) {
          return false;
        }
     }
     for (int y = 0; y < Strlen; y++) {
        data[row+y][col] = word.charAt(y);
      }
      return true;
   }

   /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added from top left to bottom right, must fit on the WordGrid,
    *and must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@return true when the word is added successfully. When the word doesn't fit,
    *or there are overlapping letters that do not match, then false is returned.
    *and the board is NOT modified.
    */
   private boolean addWordDiagonal(String word,int row, int col){
     int len1 = data[0].length;
     int len2 = data.length;
     if (row >= len2 || col >= len1 || row < 0 || col < 0 || word.length() <= 0) {
       return false; // out of bounds
     }
     int Strlen = word.length();
     if (Strlen + row > len2 || Strlen + col > len1) return false;
     for (int x = 0; x < Strlen; x++) {
       if(data[row+x][col+x] != '_' && data[row+x][col+x] != word.charAt(x)) return false;
     }
     for (int y = 0; y < Strlen; y++) {
       data[row+y][col+y] = word.charAt(y);
     }
     return true;
     }
 }
