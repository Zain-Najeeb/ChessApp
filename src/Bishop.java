import processing.core.PImage;

public class Bishop extends ChessPiece {

    static boolean inCheck;

    public Bishop(int x, int y) {
        super(x, y);
    }

    public boolean getValidMoves(int x, int y, int location, int a, boolean ForCheck, int skip, boolean ForPinned) {
        int bishopX = ChessBoard.Pieces[location].getXCord();
        int bishopY = ChessBoard.Pieces[location].getYCord();
        if (doubleCheck && !ForCheck) {
            return false;
        }
        if (ForCheck) {

            if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, 100, -100, a, true, skip, ForPinned)) {
                return true;
            }
            if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, 100, 100, a, true, skip, ForPinned)) {
                return true;
            }
            if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, -100, -100, a, true, skip, ForPinned)) {
                return true;
            }
            return PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, -100, 100, a, true, skip, ForPinned);
        }
        if (Main.InCheck && !inCheck) {
            inCheck = true;
            if (PieceHelper.forCheck(location, x, y, a, bishopX, bishopY)) {
                inCheck = false;
                return true;
            }
            inCheck = false;
        }

            if (!PieceHelper.IfPinned(bishopX, bishopY, location)) {


                if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, 100, -100, a, false, skip, ForPinned)) {
                    return true;
                }
                if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, 100, 100, a, false, skip, ForPinned)) {
                    return true;
                }
                if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, -100, -100, a, false, skip, ForPinned)) {
                    return true;
                }
                return PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, -100, 100, a, false, skip, ForPinned);
            } else {
                if (PieceHelper.getDirectionY() != 0 && PieceHelper.getDirectionX() != 0) {
                    if (PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, PieceHelper.getDirectionX(), PieceHelper.getDirectionY(), a, false, skip, ForPinned)) {
                        return true;
                    }
                    return PieceHelper.RookBishopMoves(x, y, bishopX, bishopY, PieceHelper.getDirectionX() * -1, PieceHelper.getDirectionY() * -1, a, false, skip, ForPinned);
                }
            }



        return false;


    }

    public void draw(int x, int y, boolean highlight, int location, int colour) {
        ChessBoard c = ChessBoard.instance;

        if (highlight) {
            if (!Main.InCheck && !doubleCheck) {
                if (!PieceHelper.IfPinned(x, y, location)) {
                    PieceHelper.RookBishopHighlight(x, y, 100, -100, colour);
                    PieceHelper.RookBishopHighlight(x, y, 100, 100, colour);
                    PieceHelper.RookBishopHighlight(x, y, -100, -100, colour);
                    PieceHelper.RookBishopHighlight(x, y, -100, 100, colour);
                } else {
                    if (PieceHelper.getDirectionY() != 0 && PieceHelper.getDirectionX() != 0) {
                        PieceHelper.RookBishopHighlight(x, y, PieceHelper.getDirectionX(), PieceHelper.getDirectionY(), colour);
                        PieceHelper.RookBishopHighlight(x, y, PieceHelper.getDirectionX() * -1, PieceHelper.getDirectionY() * -1, colour);
                    }
                }
            } if (Main.InCheck &&!doubleCheck) {
                PieceHelper.forCheckHighlight(x,y,location,colour);
           }


        } else {
            ChessBoard.Pieces[location] = new Bishop(x, y);
            MouseClicked.location = -1;
            if (PieceHelper.isKingAttacked(location < 16, location, false, false)) {
                ChessPiece.checkLocation = location;
                Main.InCheck = true;


            }
            ChessBoard.turn = !ChessBoard.turn;

        }
    }


    public static int Highlight(int x, int y, boolean forBlack) {

        ChessBoard c = ChessBoard.instance;
        PImage gBishop;
        c.fill(0, 255, 0);

        c.rect(x - 50, y - 50, 100, 100);
        if (forBlack) {

            gBishop = c.loadImage("Images/BlackBishop.png");
            gBishop.resize(600, 450);
            c.image(gBishop, x, y);

        } else {
            gBishop = c.loadImage("Images/WhiteBishop.png");
            gBishop.resize(600, 450);
            c.image(gBishop, x, y);

        }


        return 1;
    }

    public String getType() {
        return "BISHOP";
    }
}
