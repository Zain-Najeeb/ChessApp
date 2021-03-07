import processing.core.PApplet;

public class PieceHelper extends PApplet {
    static ChessBoard graph = ChessBoard.instance;
    public static int HowManySpaces = -1;
    private static int index = 0;
    private static int directionX = 0;
    private static int directionY = 0;

    public static boolean IsPieceClicked(int x, int y, int i) {
        if (x == ChessBoard.Pieces[i].getXCord() && y == ChessBoard.Pieces[i].getYCord() && !Main.IsPieceClicked) {

            Main.IsPieceClicked = true;

            return true;

        }

        ChessPiece.location = -1;
        return false;
    }


    public static boolean IsPieceInFrontPawn(int x, int y, int a, boolean forPawn) {

        for (int i = 0; i < 32; i++) {
            if (x == ChessBoard.Pieces[i].getXCord() && y - a == ChessBoard.Pieces[i].getYCord()) {
                return true;
            }
        }
        if (forPawn) {
            for (int i = 0; i < 32; i++) {
                if (x == ChessBoard.Pieces[i].getXCord() && y - a == ChessBoard.Pieces[i].getYCord()) {
                    HowManySpaces = 1;
                    break;
                }
            }
        }

        return false;

    }

    public static boolean PawnPinnedCaptures(int location, int x, int y, boolean forBlack) {
        int origX = ChessBoard.Pieces[location].getXCord();
        int origY = ChessBoard.Pieces[location].getYCord();


        int s = 16;
        int g = 32;
        if (forBlack) {

            g = s;
            s = 0;
        }


        for (int ii = s; ii < g; ii++) {
            ChessBoard.Pieces[location] = new Pawn (origX  +x, origY + y);

            if (ChessBoard.Pieces[ii].getXCord() == origX + x && ChessBoard.Pieces[ii].getYCord() == origY + y) {
                PieceHelper.getPiece(ii, false, 0, 0);

                if (!(PieceHelper.isKingAttacked(location > 16, location, false, false))){
                    PieceHelper.getPiece(ii, true, origX + x, origY + y);
                    return true;
                }
                PieceHelper.getPiece(ii, true, origX +x, origY +y);
            }

        }
        return false;
    }

    public static void pawnPinndHighligths(int location, int x, int y, boolean forBlack) {
        int origX = ChessBoard.Pieces[location].getXCord();
        int origY = ChessBoard.Pieces[location].getYCord();


        int s = 16;
        int g = 32;
        int a = -200;
        if (x == -100) {
            a = 200;
        }
        if (forBlack) {

            g = s;
            s = 0;
        }


            for (int ii = s; ii < g; ii++) {
                ChessBoard.Pieces[location] = new Pawn (origX  +x, origY + y);

                if (ChessBoard.Pieces[ii].getXCord() == origX + x && ChessBoard.Pieces[ii].getYCord() == origY + y) {
                    PieceHelper.getPiece(ii, false, 0, 0);

                    if (!(PieceHelper.isKingAttacked(location > 16, location, false, false)) || directionX == x + a && directionY*-1 ==y) {
                        PieceHelper.getPiece(ii, true, origX + x, origY + y);

                        PieceHelper.getImage(ii, origX  +x, origY +y);
                        break;
                    }
                    PieceHelper.getPiece(ii, true, origX +x, origY +y);
                }

            }

    }

    public static void getPiece(int location, boolean Replace, int x, int y) {
        int s = 1004234300;
        int g = 100000;
        if (Replace) {
            s = x;
            g = y;
        }

        if(ChessBoard.Pieces[location].getType().equalsIgnoreCase("QUEEN")) {
            ChessBoard.Pieces[location] = new Queen(s , g);
        }
        else if(ChessBoard.Pieces[location].getType().equalsIgnoreCase("KNIGHT")) {
            ChessBoard.Pieces[location] = new Knight(s , g);
        }
        else if(ChessBoard.Pieces[location].getType().equalsIgnoreCase("PAWN")) {
            ChessBoard.Pieces[location] = new Pawn(s , g);
        }
        else if(ChessBoard.Pieces[location].getType().equalsIgnoreCase("BISHOP")) {
            ChessBoard.Pieces[location] = new Bishop(s , g);
        }
        else if(ChessBoard.Pieces[location].getType().equalsIgnoreCase("ROOK")) {
            ChessBoard.Pieces[location] = new Rook(s , g);
        }


    }
    public static boolean PawnCaptures(int x, int y, int pawnX, int pawnY, int i, int a, boolean forHighlight) {
        if (!forHighlight) {
            return ((pawnX + 100 == x || pawnX - 100 == x) && pawnY - a == y && x == ChessBoard.Pieces[i].getXCord() && y == ChessBoard.Pieces[i].getYCord());
        }
        return ((pawnX + 100 == ChessBoard.Pieces[i].getXCord() || pawnX - 100 == ChessBoard.Pieces[i].getXCord()) && pawnY - a == ChessBoard.Pieces[i].getYCord());
    }

    public static boolean KnightBlockOrCaptrues(int x, int y, int i, boolean captures) {
        if (!captures) {
            return (x == ChessBoard.Pieces[i].getXCord() && y == ChessBoard.Pieces[i].getYCord());
        }
        return false;
    }

    public static boolean isKingAttacked(boolean forBlack, int location, boolean forPinned, boolean Doublecheck) {
        int s ;
      if (!Doublecheck) {
          if (!forBlack) {
              for (int i = 16; i < 32; i++) {


                  if (ChessBoard.Pieces[i].getValidMoves(ChessBoard.Pieces[15].getXCord(), ChessBoard.Pieces[15].getYCord(), i, 10, true, location, forPinned)) {


                      Main.isWhiteKingChecked = true;

                      return true;

                  }
              }
          } else {

              for (int i = 0; i < 16; i++) {

                  if (ChessBoard.Pieces[i].getValidMoves(ChessBoard.Pieces[31].getXCord(), ChessBoard.Pieces[31].getYCord(), i, 100, true, location, forPinned)) {
                      return true;
                  }
              }
          }
      } else {
          if (!forBlack) {
              for (int i = 31; i >= 16; i--) {

                  if (ChessBoard.Pieces[i].getValidMoves(ChessBoard.Pieces[15].getXCord(), ChessBoard.Pieces[15].getYCord(), i, 10, true, location, forPinned)) {
                      s = i;

                      return s != ChessPiece.checkLocation;

                  }
              }
          } else {

              for (int i = 15; i >=0; i--)
              {

                  if (ChessBoard.Pieces[i].getValidMoves(ChessBoard.Pieces[31].getXCord(), ChessBoard.Pieces[31].getYCord(), i, 100, true, location, forPinned)) {
                      s = i;

                      return s != ChessPiece.checkLocation;
                  }
              }
          }
      }

        return false;
    }

    public static Boolean KingBlocksOrCapturesOrMoves(int x, int y, int s, int g, boolean highlight, boolean ForBlack, boolean castle) {
        ChessBoard c = ChessBoard.instance;
        int l = 0;
        int m = 16;
        int n = 16;
        int d = 32;
        int e = 16;
        int a = 100;
        int b = 15;
        int origX;
        int origY;
        if (ForBlack) {
            l = 16;
            d = 16;
            e = 0;
            m = 32;
            n = -16;
            a = 10;
            b = 31;
        }
        origX = ChessBoard.Pieces[b].getXCord();
        origY = ChessBoard.Pieces[b].getYCord();

        for (int i = l; i < m; i++) {
            if ((ChessBoard.Pieces[i].getXCord() == x + s && ChessBoard.Pieces[i].getYCord() == y + g) || ChessBoard.Pieces[i + n].getValidMoves(x + s, y + g, i + n, a, true, 0, false)) {

                return false;
            }
            ChessBoard.Pieces[b] = new King(x + s, y + g);
            if(PieceHelper.isKingAttacked(b > 16, b, false, false)) {
                ChessBoard.Pieces[b] = new King(origX, origY);
                return  false;
            }

            ChessBoard.Pieces[b] = new King(origX, origY);
        }

        for (int i = e; i < d; i++) {
            if ((ChessBoard.Pieces[i].getXCord() == x + s && ChessBoard.Pieces[i].getYCord() == y + g)) {
                if (highlight) {
                    index = i;
                }
                if (!castle) {
                    PieceHelper.getImage(i, ChessBoard.Pieces[i].getXCord(), ChessBoard.Pieces[i].getYCord());
                    return null;

                }
            }
        }
        if (!castle) {
            c.fill(0, 255, 0);

            c.ellipse(x + s, y + g, 30, 30);

        }
        return true;
    }


    public static boolean Castling(boolean ForBlack, String sDirection) {

        boolean RightRook = Main.RightRookWhiteMoved;
        boolean king = Main.WhiteKingMoved;
        boolean leftRook = Main.LeftRookWhiteMoved;

        if (ForBlack) {
            RightRook = Main.RightBlackRookMoved;
            king = Main.BlackKingMoved;
            leftRook = Main.LeftBlackRookMoved;
        }

        if (sDirection.equalsIgnoreCase("right")) {
            if (!RightRook && !king) {
                return true;
            }
        }
        if (sDirection.equalsIgnoreCase("left")) {
            return !leftRook && !king;
        }


        return false;
    }

        public static void RookBishopHighlight(int x, int y, int g, int s, int colour) {
            ChessBoard c = ChessBoard.instance;
            int white = c.color(255, 255, 255);
            int black = c.color(0, 0, 0);
            outer:
            for (int i = 1; i < 8; i++) {
                for (int ii = 0; ii < 32; ii++) {
                    if (x + g * i == ChessBoard.Pieces[ii].getXCord() && y + s * i == ChessBoard.Pieces[ii].getYCord()) {
                        if (ii > 16 && colour == white) {
                            PieceHelper.getImage(ii, ChessBoard.Pieces[ii].getXCord(), ChessBoard.Pieces[ii].getYCord());
                        }
                        if (ii < 16 && colour == black) {
                            PieceHelper.getImage(ii, ChessBoard.Pieces[ii].getXCord(), ChessBoard.Pieces[ii].getYCord());
                        }
                        break outer;
                    }
                }
            c.fill(0, 255, 0);
            c.ellipse(x + g * i, y + s * i, 30, 30);

        }
    }

    public static boolean RookBishopMoves(int x, int y, int pieceX, int pieceY, int g, int s, int a, boolean ForCheque, int skip, boolean ifPinned) {
        boolean forPinned = false;

        outer:
        for (int i = 1; i < 8; i++) {

            for (int ii = 0; ii < 32; ii++) { //NEEDS TO BE CHANGED
                if (pieceX + g * i == ChessBoard.Pieces[ii].getXCord() && pieceY + s * i == ChessBoard.Pieces[ii].getYCord()) {
                    if (ii == 15 && a == 10) {

                        return true;
                    }
                    if ( (ii == 15 && ForCheque && !ifPinned && a == 100 && !Main.InCheck) || (ii == 31 && ForCheque && !ifPinned && a == 10) || (ii == skip && ifPinned ) || ((forPinned && ii == 15 && a == 10 && Main.InCheck)  || (forPinned && ii == 31 && a == 100))) {

                        if (ii == skip && ifPinned ) {

                            forPinned = true;
                            directionX = g;
                            directionY = s;
                        }

                        break;
                    }

                    if (ii >= 16 && a == 100 || ForCheque) {

                            if (x == ChessBoard.Pieces[ii].getXCord() && y == ChessBoard.Pieces[ii].getYCord()  ) {
                                if (!ForCheque ) {
                                    ChessBoard.Pieces[ii] = new Pawn(1000000003, 100000000);
                                }

                                return true;

                        }
                    }
                    if (ii < 16 && a == 10) {


                            if (x == ChessBoard.Pieces[ii].getXCord() && y == ChessBoard.Pieces[ii].getYCord()) {
                                if (!ForCheque ) {
                                ChessBoard.Pieces[ii] = new Pawn(1000000003, 100000000);

                            }

                                return true;
                        }
                    }

                    break outer;
                }
            }

            if (x == pieceX + g * i && y == pieceY + s * i) {

                 return true;
            }
        }
        return false;
    }

    public static void CastleChecker(int location) {

        if (location == 10) {
            Main.LeftRookWhiteMoved = true;

        }
        if (location == 11) {
            Main.RightRookWhiteMoved = true;

        }
        if (location == 26) {
            Main.LeftBlackRookMoved = true;
        }
        if (location == 27) {
            Main.RightBlackRookMoved = true;
        }
        if (location == 15) {
            Main.WhiteKingMoved = true;
        }
        if (location == 31) {
            Main.BlackKingMoved = true;
        }

    }

    public static boolean IfPinned(int x, int y, int location) {

        boolean forblack = false;
        if (location >= 16) {
            forblack = true;

        }


        if (isKingAttacked(forblack, location, true, false)) {

            return true;
        }


        return false;
    }

    public static void PieceMoving(int x, int y, int location, boolean highlight) {
        ChessBoard c = ChessBoard.instance;
        int black = c.color(0, 0, 0);
        int white = c.color(255, 255, 255);
        int colour;
        ChessPiece p = getObj(location,x,y);

        int a = 100;
        if (location >= 16) {
            a = 10;
            colour = black;
        } else {
            colour = white;
        }
        if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("pawn")) {
            if (location >= 16) {
                a = 350;

            } else {
                a = 200;
            }
        }
        if (highlight) {
            p.draw(x,y,true,location,colour);
        } else {
            if (p.getValidMoves(x,y,location,a,false,0,false)) {
                p.draw(x,y,false,location,colour);
            } else{
                graph.redrawEntireChessBoard();
                MouseClicked.location = -1;
                PieceHelper.HowManySpaces = -1;
                Main.IsPieceClicked = false;
            }
        }

    }

    public static void getImage(int location, int x, int y) {

        if (location < 8 || (location >= 16 && location < 24)) {
            if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("PAWN")) {
                Pawn.Highlight(x, y, location >= 16);
            }
            if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("QUEEN")) {
                Queen.Highlight(x, y, location >= 16);
            }

        } else if (location < 10 || location == 24 || location == 25) {
            Knight.Highlight(x, y, location >= 24);
        } else if (location < 12 || location == 26 || location == 27) {
            Rook.Highlight(x, y, location >= 12);
        } else if (location < 14 || location == 28 || location == 29) {
            Bishop.Highlight(x, y, location >= 14);
        } else if (location == 14 || location == 30) {
            Queen.Highlight(x, y, location == 30);
        } else if (location == 15 || location == 31) {
            King.Highlight(x, y, location == 31);
        }
    }
    public static ChessPiece getObj(int location, int x, int y) {
        ChessPiece p = new Pawn(x,y);

        if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("knight")) {
            p = new Knight(x, y);
        }
        if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("Rook")) {
            p = new Rook(x, y);
        }
        if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("Bishop")) {
            p = new Bishop(x, y);
        }
        if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("Queen")) {
            p = new Queen(x, y);
        }
        if (ChessBoard.Pieces[location].getType().equalsIgnoreCase("King")) {
            p = new King(x, y);
        }
    return p;
    }
    public static boolean forCheck(int location, int x, int y, int a, int pieceX, int pieceY) {

        int origX =ChessBoard.Pieces[ChessPiece.checkLocation].getXCord();
        int origY = ChessBoard.Pieces[ChessPiece.checkLocation].getYCord();
        ChessPiece p = getObj(location,x,y);

        if (p.getValidMoves(x,y,location,a,true,0,false)) {
            ChessBoard.Pieces[location] = p;
            if (ChessBoard.Pieces[location].getXCord() == ChessBoard.Pieces[ChessPiece.checkLocation].getXCord() && ChessBoard.Pieces[location].getYCord() == ChessBoard.Pieces[ChessPiece.checkLocation].getYCord() ) {
                PieceHelper.getPiece(ChessPiece.checkLocation, false,0,0);
                if ( !(PieceHelper.isKingAttacked(location>=16,location,false, false))) {
                    Main.InCheck = false;

                    return true;
                } else {
                    ChessBoard.Pieces[location] =  getObj(location,pieceX,pieceY);

                    PieceHelper.getPiece(ChessPiece.checkLocation, true, origX, origY) ;
                    return false;
                }
            }

            if(!(PieceHelper.isKingAttacked(location>=16,location,false, false))) {
                Main.InCheck =false;

                return true;
            } else {
                ChessBoard.Pieces[location] =getObj(location,pieceX,pieceY);
                return false;
            }

        } else {

            return  false;
        }

    }
    public static void forCheckHighlight(int x, int y, int location, int colour) {
        ChessPiece p = getObj(location,x,y);
        ChessBoard c = ChessBoard.instance;
        for (int i = 0; i < 64; i++ ) {
            int a = 100;
            int white = c.color(255, 255, 255);
            if (colour != white) {
                a = 10;
            }


            if (p.getValidMoves(ChessBoard.squares[i][0], ChessBoard.squares[i][1], location, a, true, 0, false)) {

                ChessBoard.Pieces[location] = getObj(location,ChessBoard.squares[i][0],ChessBoard.squares[i][1]);

                if (ChessBoard.Pieces[location].getXCord() == ChessBoard.Pieces[ChessPiece.checkLocation].getXCord() && ChessBoard.Pieces[location].getYCord() == ChessBoard.Pieces[ChessPiece.checkLocation].getYCord() ) {
                    int origX = ChessBoard.Pieces[location].getXCord();
                    int origY = ChessBoard.Pieces[location].getYCord();
                    PieceHelper.getPiece(ChessPiece.checkLocation, false, 0, 0);
                    if(!(PieceHelper.isKingAttacked(location > 16, location, false, false))) {
                        PieceHelper.getImage(ChessPiece.checkLocation, origX, origY);
                    }
                    PieceHelper.getPiece(ChessPiece.checkLocation, true, origX, origY);
                }

                if(!(PieceHelper.isKingAttacked(location > 16,location,false, false))) {

                    c.fill(0,255,0);
                    c.ellipse(ChessBoard.squares[i][0],ChessBoard.squares[i][1],30,30);


                } else {
                    ChessBoard.Pieces[location] = p;
                }
            }
            ChessBoard.Pieces[location] = p;
        }

        ChessBoard.Pieces[location] = p;
    }


    public static int getIndex() {
        return index;
    }

    public static int getDirectionX() {
        return directionX;
    }

    public static int getDirectionY() {
        return directionY;
    }


}
