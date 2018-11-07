public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
     public WordSearch (int rows, int cols) {
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
    public String toString(){
      String ret = "";
       for (int x = 0; x < data.length; x++) {
         for (int y = 0; y < data[x].length; y++) {
           ret = ret + data[x][y] + " ";
         }
         ret = ret + "\n";
       }
       return ret;
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */

   }