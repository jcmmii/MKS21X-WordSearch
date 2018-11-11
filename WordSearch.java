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
     public WordSearch (int rows, int cols) {
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
    */
//add list of words after words AND THE SEED
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
     public boolean addWordHorizontal(String word, int row, int col) {
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
   public boolean addWordVertical(String word,int row, int col){
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
    */
   public boolean addWordDiagonal(String word,int row, int col){
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

//debugs: if word.length() is <= 0
