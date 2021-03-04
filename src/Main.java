import processing.core.*;


public class Main  {
    public static int oldX = -1;
    public static int oldY = -1;
    public static boolean IsPieceClicked = false;
    public static boolean RightRookWhiteMoved = false ;
    public static boolean LeftRookWhiteMoved = false ;
    public static boolean WhiteKingMoved = false ;
    public static boolean BlackKingMoved = false;
    public static boolean LeftBlackRookMoved = false;
    public static boolean RightBlackRookMoved = false;
    public static boolean InCheck = false;
    public static boolean isWhiteKingChecked = false;
    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
        ChessBoard Sketch = new ChessBoard();



     
        PApplet.runSketch(processingArgs, Sketch);
    }
}