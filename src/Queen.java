import processing.core.PImage;

public class Queen extends ChessPiece {

    static boolean inCheck = false;
    public Queen(int x, int y) {
        super(x, y);
    }

    public boolean getValidMoves(int x, int y, int location, int a, boolean forCheque, int skip, boolean ForPinned) {

        int queenX = ChessBoard.Pieces[location].getXCord();
        int quuenY = ChessBoard.Pieces[location].getYCord();
        if (doubleCheck && !forCheque) {
            return false;
        }


        if (forCheque) {
            if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 100, -100, a, true, skip, ForPinned)) {
                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 100, 100, a, true, skip, ForPinned)) {
                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, -100, -100, a, true, skip, ForPinned)) {
                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, -100, 100, a, true, skip, ForPinned)) {
                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 0, -100, a, true, skip, ForPinned)) {
                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 0, 100, a, true, skip, ForPinned)) {

                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 100, 0, a, true, skip, ForPinned)) {

                return true;
            }
            return PieceHelper.RookBishopMoves(x, y, queenX, quuenY, -100, 0, a, true, skip, ForPinned);
        }
        if (Main.InCheck && !inCheck) {
            inCheck = true;
            if (PieceHelper.forCheck(location, x, y, a, queenX, quuenY)) {
                inCheck = false;
                return true;
            }
            inCheck = false;
        }

            if (!(PieceHelper.IfPinned(queenX, quuenY, location))) {

                if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 100, -100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 100, 100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, -100, -100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, -100, 100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 0, -100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 0, 100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, 100, 0, a, false, skip, ForPinned)) {
                    return true;
                }
                return PieceHelper.RookBishopMoves(x, y, queenX, quuenY, -100, 0, a, false, skip, ForPinned);
            } else {
                if (PieceHelper.RookBishopMoves(x, y, queenX, quuenY, PieceHelper.getDirectionX(), PieceHelper.getDirectionY(), a, false, skip, ForPinned)) {
                    return true;
                }
                return PieceHelper.RookBishopMoves(x, y, queenX, quuenY, PieceHelper.getDirectionX() * -1, PieceHelper.getDirectionY() * -1, a, false, skip, ForPinned);
            }




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
                    PieceHelper.RookBishopHighlight(x, y, 0, -100, colour);
                    PieceHelper.RookBishopHighlight(x, y, 0, 100, colour);
                    PieceHelper.RookBishopHighlight(x, y, 100, 0, colour);
                    PieceHelper.RookBishopHighlight(x, y, -100, 0, colour);
                } else {
                    PieceHelper.RookBishopHighlight(x, y, PieceHelper.getDirectionX(), PieceHelper.getDirectionY(), colour);
                    PieceHelper.RookBishopHighlight(x, y, PieceHelper.getDirectionX() * -1, PieceHelper.getDirectionY() * -1, colour);
                }


            } if (Main.InCheck &&!doubleCheck) {
            PieceHelper.forCheckHighlight(x,y,location,colour);

            }
        } else {
            ChessBoard.Pieces[location] = new Queen(x, y);
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
        PImage gQueen;
        c.fill(0, 255, 0);

        c.rect(x - 50, y - 50, 100, 100);
        if (forBlack) {

            gQueen = c.loadImage("Images/BlackQueen.png");
            gQueen.resize(600, 450);
            c.image(gQueen, x, y);

        } else {
            gQueen = c.loadImage("Images/WhiteQueen.png");
            gQueen.resize(600, 450);
            c.image(gQueen, x, y);

        }


        return 1;
    }

    public String getType() {
        return "QUEEN";
    }

}


