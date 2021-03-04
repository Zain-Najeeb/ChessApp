import processing.core.PImage;

public class Knight extends ChessPiece {
    static boolean draw = false;
    static boolean inCheck = false;

    public Knight(int x, int y) {
        super(x, y);
    }

    public boolean getValidMoves(int x, int y, int location, int a, boolean ForCheque, int skip, boolean ForPinned) {

        int knightX = ChessBoard.Pieces[location].getXCord();
        int knightY = ChessBoard.Pieces[location].getYCord();

        KnightHelper[] KnightMoves = {new KnightHelper(knightX + 100, knightY - 200), new KnightHelper(knightX - 100, knightY - 200), new KnightHelper(knightX + 200, knightY - 100), new KnightHelper(knightX - 200, knightY - 100), new KnightHelper(knightX - 100, knightY + 200), new KnightHelper(knightX + 200, knightY + 100), new KnightHelper(knightX - 200, knightY + 100), new KnightHelper(knightX + 100, knightY + 200)};
        boolean KnightHightlight, KnightHightlight2, KnightHighlight3;
        if (doubleCheck &&!ForCheque){
            return  false;
        }
        if (ForPinned) {

            return false;

        }

        if (!ForCheque && (PieceHelper.IfPinned(knightX, knightY, location))  && !Main.InCheck || (draw && (PieceHelper.IfPinned(knightX, knightY, location))))  {

               return false;
        }

        if (Main.InCheck && !inCheck && !doubleCheck && !ForCheque) {
                inCheck = true;
                if (PieceHelper.forCheck(location, x,y, a, knightX, knightY)) {
                    inCheck = false;
                    return  true;
                }
                inCheck = false;


        }
        if   (!Main.InCheck || (Main.isWhiteKingChecked && a == 10) ||(!Main.isWhiteKingChecked && a == 100) || ((ForCheque&& (a == 100 || a ==10)))) {
            for (KnightHelper move : KnightMoves) {
                KnightHightlight2 = KnightBlocks(move.GetX(), move.GetY(), true);
                KnightHighlight3 = KnightBlocks(move.GetX(), move.GetY(), false);
                if (x == move.GetX() && y == move.GetY() && (KnightHightlight2 || KnightHighlight3)) {
                    if (a == 100) {

                        for (int ii = 16; ii < 32; ii++) { //NEEDS TO BE CHANGED


                            if (ChessBoard.Pieces[ii].getXCord() == move.GetX() && ChessBoard.Pieces[ii].getYCord() == move.GetY()) {
                                if (!ForCheque && !draw) {
                                    ChessBoard.Pieces[ii] = new Pawn(10000004, 10000000);

                                }
                                return true;
                            }
                        }
                    } else {
                        for (int ii = 0; ii < 16; ii++) {


                            if (ChessBoard.Pieces[ii].getXCord() == move.GetX() && ChessBoard.Pieces[ii].getYCord() == move.GetY()) {
                                if (!ForCheque && !draw) {
                                    ChessBoard.Pieces[ii] = new Pawn(10000004, 10000000);

                                }
                                return true;
                            }
                        }

                    }
                }
            }


            boolean black = false;
            if (a == 10) {
                black = true;
            }

            for (KnightHelper knightMove : KnightMoves) {
                KnightHightlight = KnightBlocks(knightMove.GetX(), knightMove.GetY(), black);

                if (x == knightMove.GetX() && y == knightMove.GetY() && !KnightHightlight) {

                    return true;


                }
            }
        }





        return false;
    }


    public void draw(int x, int y, boolean highlight, int location, int colour) {
        ChessBoard c = ChessBoard.instance;
        int white = c.color(255, 255, 255);
        if (highlight) {
            if (!Main.InCheck && !doubleCheck) {
                int a;
                int b = 16;
                int k = 32;
                if (colour == white) {
                    a = 100;
                } else {
                    a = 10;
                    b = 0;
                    k = 16;
                }
                inCheck = true;
                draw = true;
                for (int i = 0; i < 64; i++) {

                    if (getValidMoves(ChessBoard.squares[i][0], ChessBoard.squares[i][1], location, a, false, 0, false)) {
                        c.fill(0, 255, 0);
                        c.ellipse(ChessBoard.squares[i][0], ChessBoard.squares[i][1], 30, 30);
                        for (int ii = b; ii < k; ii++) {
                            if (ChessBoard.squares[i][0] == ChessBoard.Pieces[ii].getXCord() && ChessBoard.squares[i][1] == ChessBoard.Pieces[ii].getYCord()) {
                                PieceHelper.getImage(ii, ChessBoard.Pieces[ii].getXCord(), ChessBoard.Pieces[ii].getYCord());
                            }
                        }
                    }

                }
                draw = false;


            }  if (Main.InCheck && !doubleCheck) {
                PieceHelper.forCheckHighlight(x,y,location,colour);
            }
        } else {
            ChessBoard.Pieces[location] = new Knight(x, y);
            MouseClicked.location = -1;
            ChessBoard.turn = !ChessBoard.turn;
            if (PieceHelper.isKingAttacked(location < 16, location, false, false)) {
                ChessPiece.checkLocation = location;
                Main.InCheck = true;
                int s = 31;
                if (location >= 16) {
                    s = 15;
                }

                PieceHelper.getImage(s, ChessBoard.Pieces[s].getXCord(), ChessBoard.Pieces[s].getYCord());



            }

        }
    }

    public static int Highlight(int x, int y, boolean forBlack) {
        ChessBoard c = ChessBoard.instance;
        PImage gKnight;
        c.fill(0, 255, 0);
        c.rect(x - 50, y - 50, 100, 100);
        if (forBlack) {

            gKnight = c.loadImage("Images/BlackKnight.png");
            gKnight.resize(550, 425);
            c.image(gKnight, x, y);

        } else {
            gKnight = c.loadImage("Images/WhiteHorse.png");
            gKnight.resize(500, 375);
            c.image(gKnight, x, y);

        }
        return 1;

    }

    boolean KnightBlocks(int x, int y, boolean forBlack) {
        boolean KnightBlocks;
        if (!forBlack) {
            for (int i = 0; i < 16; i++) {
                KnightBlocks = PieceHelper.KnightBlockOrCaptrues(x, y, i, false);
                if (KnightBlocks) {
                    return true;
                }
            }
        } else {
            for (int i = 16; i < 32; i++) {
                KnightBlocks = PieceHelper.KnightBlockOrCaptrues(x, y, i, false);
                if (KnightBlocks) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getType() {
        return "KNIGHT";
    }

}
