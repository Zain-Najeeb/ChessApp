import processing.core.PApplet;

abstract class ChessPiece {
    private int x;
    private int y;
    public static int location = -1;
    public static int checkLocation = -1;
    public static boolean doubleCheck  = false;
    public ChessPiece(int x, int y) {
            this.x = x;
            this.y = y;
    }
    public int getXCord() {
        return  x;
    }
    public int getYCord() {
        return y;
    }


        abstract boolean getValidMoves(int x, int y, int location, int a, boolean ForCheque, int skip, boolean ForPinned);
        public abstract void draw(int x, int y, boolean highlight, int location, int colour);
        abstract String getType();



}

