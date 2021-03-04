import processing.core.PImage;

public class King extends ChessPiece {
    static ChessBoard graph = ChessBoard.instance;
    public  static boolean Castle = false;
    public King(int x, int y) {
        super(x, y);
    }

    @SuppressWarnings("ConstantConditions")
    public boolean getValidMoves(int x, int y, int location, int a, boolean ForCheque, int skip, boolean ForPinned) {

        int kingX = ChessBoard.Pieces[location].getXCord();
        int kingY = ChessBoard.Pieces[location].getYCord();





        boolean colour = false;
        if (a == 10){
            colour = true;
        }

        if (kingX == x && kingY - 100 == y) {
            if (ForCheque) {
                return  true;
            }
            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 0, -100, true, colour, false) == null || (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 0, -100, false, colour, false))) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 0, -100, true, colour,false) == null)) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;

            }


        }


        if (kingX - 100 == x && kingY - 100 == y) {
            if (ForCheque) {
                return  true;
            }
            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, -100, true, colour, false) == null || PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, -100, false, colour, false) ) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, -100, true, colour, false) == null)) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;
            }
        }



        if (kingX + 100 == x && kingY - 100 == y) {
            if (ForCheque) {
                return  true;
            }

            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, -100, true, colour, false) == null || PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, -100, false, colour, false))  {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, -100, true,colour, false) == null) ) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }

                return true;
            }
        }
        if (kingX + 100 == x && kingY == y) {
            if (ForCheque) {
                return true;
            }
            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, +100, 0, true, colour, false) == null || PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, 0, false, colour, false)) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, 0, true, colour, false) == null)) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;
            }
        }

        if (kingX - 100 == x && kingY == y) {

            if (ForCheque) {
                return true;
            }

            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 0, true, colour, false) == null || PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 0, false, colour, false)) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 0, true, colour, false) == null)) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;
            }
        }

        if (kingX - 100 == x && kingY + 100 == y) {
            if (ForCheque) {
                return  true;
            }
            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 100, true, colour, false) == null || PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 100, false, colour, false) ) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 100, true, colour, false) == null) ) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;
            }
        }
        if (kingX == x && kingY + 100 == y) {
            if (ForCheque) {
                return  true;
            }
            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 0, 100, true, colour, false) == null ||  PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 0, 100, false, colour, false) ) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 0, 100, true, colour, false) == null) ) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;
            }
        }

    if (kingX + 100 == x && kingY + 100 == y) {
            if (ForCheque) {
                return  true;
            }
            if (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, +100, +100, true, colour, false) == null || PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, +100, +100, false, colour, false) ) {
                if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, +100, +100, true, colour, false) == null)) {
                    int index = PieceHelper.getIndex();

                    ChessBoard.Pieces[index] = new Pawn(1000000000, 100000000);
                }
                return true;
            }
        }

  if (!Main.InCheck) {
    if (!ForCheque) {
        if (PieceHelper.Castling(colour, "right")) {
            if ((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, 0, false, colour, false) != null) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 200, 0, false, colour, false) != null) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 100, 0, false, colour, false) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, 200, 0, false, colour, false)))) {

                if ((x == 650 || x == 750) && (y == 750 && a == 100) || (y == 50 && a == 10)) {
                    Castle = true;
                    return true;
                }

            }
        }
        if (PieceHelper.Castling(colour, "left")) {
            if (((PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 0, false, colour, false) != null) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -200, 0, false, colour, false) != null) & (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -300, 0, false, colour, false) != null)) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -100, 0, false, colour, false) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -200, 0, false, colour, false) && (PieceHelper.KingBlocksOrCapturesOrMoves(kingX, kingY, -300, 0, false, colour, false))))) {

                if ((x == 350 || x == 250 || x == 50 || x == 150) && (y == 750 && a == 100) || (y == 50 && a == 10)) {
                    Castle = true;
                    return true;
                }

            }
        }
    }


    }


        return false;


    }
    @SuppressWarnings("ConstantConditions")
    public void draw(int x, int y, boolean highlight, int location, int colour) {
        int s = -100;
        ChessBoard c = ChessBoard.instance;
        int black = c.color(0,0,0);
        boolean forblack = false;

        if (black == colour) {
            forblack = true;

        }    if (highlight) {

            for (int i = 0; i < 8; i++) {

                if (i % 3 == 0 && i != 6) {
                    s = -100;
                }
                if (i == 5) {
                    s = -100;
                }

                if (i < 3) {
                    PieceHelper.KingBlocksOrCapturesOrMoves(x, y, s, -100, false, forblack, false);
                    s += 100;
                } else if (i < 5) {

                    PieceHelper.KingBlocksOrCapturesOrMoves(x, y, s, 0, false, forblack, false);
                    s += 200;
                } else {

                    PieceHelper.KingBlocksOrCapturesOrMoves(x, y, s, 100, false, forblack, false);
                    s += 100;
                }
            }

            if (!Main.InCheck) {
                if (PieceHelper.Castling(forblack, "right")) {
                    if ((PieceHelper.KingBlocksOrCapturesOrMoves(x, y, 200, 0, false, forblack, true) && (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, 100, 0, false, forblack, true) != null)) && (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, 200, 0, false, forblack, true) != null) && (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, 100, 0, false, forblack, true))) {
                        if (!forblack) {
                            PieceHelper.getImage(11, 750, 750);
                            c.ellipse(650, 750, 30, 30);

                        } else {
                            PieceHelper.getImage(27, 750, 50);
                            c.ellipse(650, 50, 30, 30);
                        }
                    }

                }
                if (PieceHelper.Castling(forblack, "left")) {
                    if (((PieceHelper.KingBlocksOrCapturesOrMoves(x, y, -100, 0, false, forblack, true) != null) && (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, -200, 0, false, forblack, true) != null) & (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, -300, 0, false, forblack, true) != null)) && ((PieceHelper.KingBlocksOrCapturesOrMoves(x, y, -100, 0, false, forblack, true)) && (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, -200, 0, false, forblack, true)) && (PieceHelper.KingBlocksOrCapturesOrMoves(x, y, -300, 0, false, forblack, true)))) {

                        if (!forblack) {
                            PieceHelper.getImage(10, 50, 750);
                            c.fill(0, 255, 0);
                            c.ellipse(150, 750, 30, 30);
                            c.ellipse(250, 750, 30, 30);
                        } else {
                            PieceHelper.getImage(26, 50, 50);
                            c.fill(0, 255, 0);
                            c.ellipse(150, 50, 30, 30);
                            c.ellipse(250, 50, 30, 30);
                        }
                    }

                }

            }
        }




    else

    {
        if (!Castle) {
            ChessBoard.Pieces[location] = new King(x, y);
            PieceHelper.CastleChecker(location);
        } else {
            if (x >= 650 && y == 750) {
                ChessBoard.Pieces[location] = new King(650, 750);
                ChessBoard.Pieces[11] = new Rook(550, 750);

            } else if (x < 650 && y == 750){
                ChessBoard.Pieces[location] = new King(250, 750);
                ChessBoard.Pieces[10] = new Rook(350, 750);

            }
            else if (x >= 650 && y == 50){

                ChessBoard.Pieces[location] = new King(650, 50);
                ChessBoard.Pieces[27] = new Rook(550, 50);

            }
            else if (x < 650 && y == 50){
                ChessBoard.Pieces[location] = new King(250, 50);
                ChessBoard.Pieces[26] = new Rook(350, 50);

            }
            Castle = false;
        }
        if (location <= 16) {
            Main.isWhiteKingChecked = false;
        }
        ChessBoard.turn = !ChessBoard.turn;
        doubleCheck = false;
        Main.InCheck = false;
        MouseClicked.location = -1;

    }


}


    public static int Highlight(int x, int y, boolean forBlack) {

        ChessBoard c = ChessBoard.instance;
        PImage gKing;
        if (Main.InCheck) {
            c.fill(255, 0, 0);

        } else {
            c.fill(0, 255, 0);
        }

        c.rect(x - 50, y - 50, 100, 100);
        if (forBlack) {

            gKing = c.loadImage("Images/BlackKing.png");
            gKing.resize(600, 450);
            c.image(gKing, x, y);

        } else {
            gKing = c.loadImage("Images/WhiteKing.png");

            gKing.resize(600, 450);
            c.image(gKing, x, y);

        }


        return 1;
    }
    public String getType() {
        return "KING";
    }
}

