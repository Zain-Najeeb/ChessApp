import processing.core.PApplet;
import processing.core.PImage;


public class ChessBoard extends PApplet {
    public boolean hasStartedYet = false;
    public static PImage WhiteHorse, WhitePawn, BlackPawn, BlackKnight, WhiteRook, BlackRook, WhiteBishop, BlackBishop, WhiteQueen, BlackQueen, WhiteKing, BlackKing;
    public static int[][] squares = new int[64][2];
    public static boolean turn = true;
    public static ChessBoard instance;

    public static ChessPiece[] Pieces = new ChessPiece[32];

    public void settings() {
        BlackKing = loadImage("Images/BlackKing.png");
        WhiteKing = loadImage("Images/WhiteKing.png");
        BlackQueen = loadImage("Images/BlackQueen.png");
        WhiteQueen = loadImage("Images/WhiteQueen.png");
        WhiteBishop = loadImage("Images/WhiteBishop.png");
        WhiteRook = loadImage("Images/WhiteRook.png");
        BlackRook = loadImage("Images/BlackRook.png");
        BlackKnight = loadImage("Images/BlackKnight.png");
        WhiteHorse = loadImage("Images/WhiteHorse.png");
        WhitePawn = loadImage("Images/WhitePawn.png");
        BlackPawn = loadImage("Images/BlackPawn.png");
        BlackBishop = loadImage("Images/BlackBishop.png");
        BlackBishop.resize(600, 450);
        WhiteRook.resize(600, 450);
        WhiteHorse.resize(500, 375);
        BlackKnight.resize(550, 425);
        WhitePawn.resize(600, 450);
        BlackPawn.resize(600, 450);
        BlackRook.resize(600, 450);
        WhiteBishop.resize(600, 450);
        WhiteQueen.resize(600, 450);
        BlackQueen.resize(600, 450);
        WhiteKing.resize(600, 450);
        BlackKing.resize(600, 450);
        size(800, 800);
        if (instance == null) {
            instance = this;
        }
        for (int i = 0; i < 8; i++) {
            Pieces[i] = new Pawn(50 + (i * 100), 650);
        }
        for (int i = 16, s = 0; i < 24; i++, s++) {

            Pieces[i] = new Pawn(50 + (s * 100), 150);
        }
        for (int i = 10, s = 0; i < 12; i++, s++) {
            Pieces[i] = new Rook(50 + 700 * s, 750);
        }
        for (int i = 8, s = 0; i < 10; i++, s++) {
            Pieces[i] = new Knight(150 + 500 * s, 750);
        }
        for (int i = 12, s = 0; i < 14; i++, s++) {
            Pieces[i] = new Bishop(250 + 300 * s, 750);
        }
        Pieces[30] = new Queen(350, 50);
        Pieces[28] = new Bishop(250, 50);
        Pieces[29] = new Bishop(550, 50);
        Pieces[14] = new Queen(350, 750);
        Pieces[15] = new King(450, 750);
        Pieces[24] = new Knight(150, 50);
        Pieces[25] = new Knight(650, 50);
        Pieces[26] = new Rook(50, 50);
        Pieces[31] = new King(450, 50);
        Pieces[27] = new Rook(750, 50);
        int x = 50;
        int y = 50;
        for(int i = 0; i < 64; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1) {
                    squares[i][1] = y;
                    y += 100;
                    if (y == 850) {
                        y = 50;
                        x += 100;
                    }
                }
                else {
                    squares[i][0] = x;
                }
            }
        }
    }


    public void draw() {

        ellipseMode(CENTER);
        imageMode(CENTER);
        if (!hasStartedYet) {
            redrawEntireChessBoard();
            hasStartedYet = true;
            fill(color(89, 60, 31));

        }


    }

    public void mousePressed() {
        int x = mouseX;
        int y = mouseY;
        x = ((x / 100) * 100) + 50;
        y = ((y / 100) * 100) + 50;

        MouseClicked m = new MouseClicked(x, y);


    }

    public void redrawEntireChessBoard() {
      if (Main.InCheck) {

          if (PieceHelper.isKingAttacked(ChessPiece.checkLocation < 16, ChessPiece.checkLocation, false, true)) {

              ChessPiece.doubleCheck = true;
          }
      }


        int beige = color(207, 185, 151);
        int brown = color(89, 60, 31);
        rect(0, 700, 100, 100);
        rect(700, 0, 100, 100);
        int a = 100;
        int b = 100;
        boolean blue = true;
        for (int ii = 0; ii < 8; ii++) {
            fill(brown);
            if (blue) {
                fill(beige);
            }
            blue ^= true;
            for (int i = 0; i < 8; i++) {
                fill(beige);
                if (blue) {
                    fill(brown);
                }
                rect(a * i, b * ii, 100, 100);

                blue ^= true;
            }

        }


        image(BlackKing, Pieces[31].getXCord(), Pieces[31].getYCord());
        image(WhiteKing, Pieces[15].getXCord(), Pieces[15].getYCord());
        image(BlackQueen, Pieces[30].getXCord(), Pieces[30].getYCord());
        image(WhiteQueen, Pieces[14].getXCord(), Pieces[14].getYCord());
        image(BlackBishop, Pieces[28].getXCord(), Pieces[28].getYCord());
        image(BlackBishop, Pieces[29].getXCord(), Pieces[29].getYCord());
        image(WhiteBishop, Pieces[12].getXCord(), Pieces[12].getYCord());
        image(WhiteBishop, Pieces[13].getXCord(), Pieces[13].getYCord());
        image(BlackRook, Pieces[26].getXCord(), Pieces[26].getYCord());
        image(BlackRook, Pieces[27].getXCord(), Pieces[27].getYCord());
        image(WhiteRook, Pieces[10].getXCord(), Pieces[10].getYCord());
        image(WhiteRook, Pieces[11].getXCord(), Pieces[11].getYCord());
        image(BlackKnight, Pieces[24].getXCord(), Pieces[24].getYCord());
        image(BlackKnight, Pieces[25].getXCord(), Pieces[25].getYCord());
        image(WhiteHorse, Pieces[8].getXCord(), Pieces[8].getYCord());
        image(WhiteHorse, Pieces[9].getXCord(), Pieces[9].getYCord());
        for (int i = 0; i < 24; i++) {
            if (i < 8) {
                if (Pieces[i].getType().equalsIgnoreCase("QUEEN")) {
                    image(WhiteQueen, Pieces[i].getXCord(), Pieces[i].getYCord());
                } else {
                    image(WhitePawn, Pieces[i].getXCord(), Pieces[i].getYCord());

                }
            }
            if (i >= 16) {


                if (Pieces[i].getType().equalsIgnoreCase("QUEEN")) {
                    image(BlackQueen, Pieces[i].getXCord(), Pieces[i].getYCord());

                } else {
                    image(BlackPawn, Pieces[i].getXCord(), Pieces[i].getYCord());
                }
            }
        }
        int s = 31;
        if (ChessPiece.checkLocation >= 16 ) {
            s = 15;
        }
        if (Main.InCheck) {
            PieceHelper.getImage(s, ChessBoard.Pieces[s].getXCord(), ChessBoard.Pieces[s].getYCord());
        }
    }


}
