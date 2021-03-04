import processing.core.PImage;

public class Rook extends ChessPiece {
    static ChessBoard graph = ChessBoard.instance;
    static boolean inCheck = false;
    static boolean test = false;

    public Rook(int x, int y) {
        super(x, y);
    }

    public boolean getValidMoves(int x, int y, int location, int a, boolean ForCheque, int skip, boolean ForPinned) {
        int rookX = ChessBoard.Pieces[location].getXCord();
        int rookY = ChessBoard.Pieces[location].getYCord();
        if (doubleCheck && !ForCheque) {
            return false;
        }
        if (ForCheque) {
            if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, 0, -100, a, true, skip, ForPinned)) {
                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, 100, 0, a, true, skip, ForPinned)) {

                return true;
            } else if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, 0, 100, a, true, skip, ForPinned)) {

                return true;
            }

            return PieceHelper.RookBishopMoves(x, y, rookX, rookY, -100, 0, a, true, skip, ForPinned);
        }
        if (Main.InCheck && !inCheck) {
            inCheck = true;
            if (PieceHelper.forCheck(location, x, y, a, rookX, rookY)) {
                inCheck = false;
                return true;
            }
            inCheck = false;
        }


            if (!PieceHelper.IfPinned(rookX, rookY, location)) {
                if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, 0, -100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, 0, 100, a, false, skip, ForPinned)) {
                    return true;
                } else if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, 100, 0, a, false, skip, ForPinned)) {
                    return true;
                }
                return PieceHelper.RookBishopMoves(x, y, rookX, rookY, -100, 0, a, false, skip, ForPinned);
            } else {

                if (!(PieceHelper.getDirectionY() != 0 && PieceHelper.getDirectionX() != 0)) {

                    if (PieceHelper.RookBishopMoves(x, y, rookX, rookY, PieceHelper.getDirectionX(), PieceHelper.getDirectionY(), a, false, skip, ForPinned)) {
                        return true;
                    }
                    return PieceHelper.RookBishopMoves(x, y, rookX, rookY, PieceHelper.getDirectionX() * -1, PieceHelper.getDirectionY() * -1, a, false, skip, ForPinned);
                }

            }

        return false;
    }


    public void draw(int x, int y, boolean highlight, int location, int colour) {

        ChessBoard c = ChessBoard.instance;
        if (highlight) {
            if (!Main.InCheck && !doubleCheck) {
                if (!PieceHelper.IfPinned(x, y, location)) {
                    PieceHelper.RookBishopHighlight(x, y, 0, -100, colour);
                    PieceHelper.RookBishopHighlight(x, y, 0, 100, colour);
                    PieceHelper.RookBishopHighlight(x, y, 100, 0, colour);
                    PieceHelper.RookBishopHighlight(x, y, -100, 0, colour);
                } else {

                    if (!(PieceHelper.getDirectionY() != 0 && PieceHelper.getDirectionX() != 0)) {
                        PieceHelper.RookBishopHighlight(x, y, PieceHelper.getDirectionX(), PieceHelper.getDirectionY(), colour);
                        PieceHelper.RookBishopHighlight(x, y, PieceHelper.getDirectionX() * -1, PieceHelper.getDirectionY() * -1, colour);
                    }
                }
            }
            if (Main.InCheck && !doubleCheck) {
                PieceHelper.forCheckHighlight(x,y,location,colour);
            }


        } else {
            ChessBoard.Pieces[location] = new Rook(x, y);
            PieceHelper.CastleChecker(location);
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
                if (PieceHelper.isKingAttacked(location < 16, location, false, true)) {
                    doubleCheck = true;
                }
            }
        }

    }

    public static int Highlight(int x, int y, boolean forBlack) {

        ChessBoard c = ChessBoard.instance;
        PImage gRook;
        c.fill(0, 255, 0);

        c.rect(x - 50, y - 50, 100, 100);
        if (forBlack) {

            gRook = c.loadImage("Images/BlackRook.png");
            gRook.resize(600, 450);
            c.image(gRook, x, y);

        } else {
            gRook = c.loadImage("Images/WhiteRook.png");
            gRook.resize(600, 450);
            c.image(gRook, x, y);

        }


        return 1;
    }

    public String getType() {
        return "ROOK";
    }
}
