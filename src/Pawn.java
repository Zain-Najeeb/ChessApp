import processing.core.PImage;

public class Pawn extends ChessPiece {

    static boolean inCheck = false;
    static boolean draw = false;
    public Pawn(int x, int y) {
        super(x, y);


    }

    public boolean getValidMoves(int x, int y, int location, int a, boolean ForCheck, int skip, boolean ForPinned) {
        int pawnX = ChessBoard.Pieces[location].getXCord();
        int pawnY = ChessBoard.Pieces[location].getYCord();

        if (!ForCheck) {

            boolean forPinnedDiagonally = false;
            boolean forPinnedStraigth = false;
            boolean forPinnedSideWays = false;
            if ((PieceHelper.IfPinned(x, y, location)) && !inCheck  ||( draw && (PieceHelper.IfPinned(x, y, location) ))) {

                if ((PieceHelper.getDirectionY() != 0 && PieceHelper.getDirectionX() != 0)) {

                    forPinnedDiagonally = true;
                }

                if ((PieceHelper.getDirectionY() != 0 && PieceHelper.getDirectionX() == 0)) {

                    forPinnedStraigth = true;

                }
                if ((PieceHelper.getDirectionY() == 0 && PieceHelper.getDirectionX() != 0)) {

                    forPinnedSideWays = true;

                }

            }
            if (!Main.InCheck || inCheck) {
                if (a == 350) {
                    if (pawnY != 150) {
                        a = -100;
                    } else {
                        a = -200;
                    }
                }


                if (pawnY != 650 && a > 0) {
                    a = 100;
                }


                int b = a;
                if (a == 200 || a == -200) {
                    if (a > 0) {
                        b = 100;
                    } else {
                        b = -100;
                    }

                }

                boolean PieceInfrontChecks = PieceHelper.IsPieceInFrontPawn(pawnX, pawnY, b, false);
                boolean PieceInfrontChecks2 = PieceHelper.IsPieceInFrontPawn(pawnX, pawnY, b * 2, true);
                if (!PieceInfrontChecks && PieceInfrontChecks2) {
                    if (a > 0) {
                        a = 100;
                    } else {
                        a = -100;
                    }
                }


                if (!forPinnedDiagonally && !forPinnedSideWays) {
                    if (a > 0) {

                        if (x == pawnX && (y >= pawnY - a && y < pawnY) && !PieceInfrontChecks) {

                            return true;
                        }
                    }
                    if (a < 0) {
                        if (x == pawnX && (y <= pawnY - a && y > pawnY) && !PieceInfrontChecks) {


                            return true;
                        }
                    }
                }

                if (!forPinnedStraigth && !forPinnedSideWays) {
                    if (a > 0) {
                        for (int i = 16; i < 32; i++) {
                            a = 100;
                            boolean PawnCaptures = PieceHelper.PawnCaptures(x, y, pawnX, pawnY, i, a, false);
                            if (PawnCaptures) {
                                if (!forPinnedDiagonally) {
                                    if (!inCheck) {
                                        ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                    }
                                    return true;
                                } else {
                                    int origX = ChessBoard.Pieces[location].getXCord();
                                    int origY = ChessBoard.Pieces[location].getYCord();
                                    if (PieceHelper.PawnPinnedCaptures(location, +100, -100, false)) {
                                        if (x - origX == 100) {
                                            ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                            if (!inCheck) {
                                                ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                            }
                                            return true;
                                        }
                                    }
                                    ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                    if (PieceHelper.PawnPinnedCaptures(location, -100, -100, false)) {
                                        if (origX - x == 100) {
                                            ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                            if (!inCheck) {
                                                ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                            }
                                            return true;
                                        }
                                    }


                                    ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                }

                            }

                        }
                    }
                    if (a < 0) {
                        for (int i = 0; i < 16; i++) {
                            a = -100;
                            boolean PawnCaptures = PieceHelper.PawnCaptures(x, y, pawnX, pawnY, i, a, false);
                            if (PawnCaptures) {
                                if (!forPinnedDiagonally) {
                                    if (!inCheck) {
                                        ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                    }
                                    return true;
                                } else {

                                    int origX = ChessBoard.Pieces[location].getXCord();
                                    int origY = ChessBoard.Pieces[location].getYCord();

                                    if (PieceHelper.PawnPinnedCaptures(location, +100, 100, true)) {
                                        if (x - origX == 100) {
                                            ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                            if (!inCheck) {
                                                ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                            }
                                            return true;
                                        }
                                    }
                                    ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                    if (PieceHelper.PawnPinnedCaptures(location, -100, 100, true)) {
                                        if (origX - x == 100) {
                                            ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                            if (!inCheck) {
                                                ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                            }
                                            return true;
                                        }
                                    }


                                    ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                }

                            }
                        }
                    }

                }
            }

            if (Main.InCheck && !doubleCheck && !inCheck) {
                int origX =    ChessBoard.Pieces[location].getXCord();
                int origY = ChessBoard.Pieces[location].getYCord();
                inCheck = true;
                int b = 100;
                int s = 16;
                int g =32;
                if (a == 350) {
                    s = 0;
                    g = 16;
                    b = -100;
                }
                for (int i = s; i < g; i++) {

                    boolean PawnCaptures = PieceHelper.PawnCaptures(x, y, pawnX, pawnY, i, b, false);
                    if (PawnCaptures) {

                        if (PieceHelper.PawnPinnedCaptures(location, +100, -b, location >=16)) {
                            if (x - origX == 100) {
                                ChessBoard.Pieces[location] = new Pawn(origX, origY);
                                    ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                Main.InCheck =false;
                                inCheck = false;
                                return true;
                            }
                        }
                        ChessBoard.Pieces[location] = new Pawn(origX, origY);
                        if (PieceHelper.PawnPinnedCaptures(location, -100,-b, location >=16)) {
                            if (origX - x == 100) {
                                ChessBoard.Pieces[location] = new Pawn(origX, origY);

                                    ChessBoard.Pieces[i] = new Pawn(1000000002, 100000000);
                                Main.InCheck =false;
                                inCheck = false;
                                return true;
                            }
                        }


                        ChessBoard.Pieces[location] = new Pawn(origX, origY);
                    }
                }

                if (getValidMoves(x,y,location,a,false,0,false)) {
                    ChessBoard.Pieces[location] = new Pawn(x, y);


                    if(!(PieceHelper.isKingAttacked(location>=16,location,false, false))) {
                        Main.InCheck =false;
                        inCheck = false;
                        return true;
                    } else {
                        ChessBoard.Pieces[location] = new Pawn(origX,origY);
                        inCheck = false;
                        return false;
                    }

                } else {
                    ChessBoard.Pieces[location] = new Pawn(origX,origY);
                    inCheck = false;
                    return  false;
                }

            }
        }


        if (ForCheck) {
            int b = 100;

            if (a == 10) {

                return pawnY + b == y && (pawnX + 100 == x || pawnX - 100 == x);
            } else {

                return pawnY - b == y && (pawnX + 100 == x || pawnX - 100 == x);
            }
        }
        return false;
    }


    public void draw(int x, int y, boolean highlight, int location, int colour) {

        ChessBoard c = ChessBoard.instance;
        int white = c.color(255, 255, 255);

        if (highlight) {
            int a;
            if (!Main.InCheck && !doubleCheck) {

                int b = 16;
                int k = 32;
                if (colour == white) {
                    a = 200;
                } else {
                    a = 350;
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
            }

            inCheck = false;

            if(Main.InCheck && !doubleCheck) {
                int s = 16;
                int g = 32;
                int l = -100;

                if (colour == white) {
                    a = 200;

                } else {
                    a = 350;
                    s = 0;
                    g = 16;
                    l = 100;
                }
                for (int i = 0; i < 64; i++ ) {

                    inCheck = true;

                    if (getValidMoves(ChessBoard.squares[i][0], ChessBoard.squares[i][1], location, a, false, 0, false)) {

                        ChessBoard.Pieces[location] = new Pawn(ChessBoard.squares[i][0] ,ChessBoard.squares[i][1]);


                        if(!(PieceHelper.isKingAttacked(location > 16,location,false, false))) {

                            c.fill(0,255,0);
                            c.ellipse(ChessBoard.squares[i][0],ChessBoard.squares[i][1],30,30);


                        } else {
                            ChessBoard.Pieces[location] = new Pawn(x, y);
                        }
                    }
                    ChessBoard.Pieces[location] = new Pawn(x, y);
                }



                inCheck = false;

                for (int i = s; i < g; i++) {
                    a = 100;
                    boolean PawnHighlights = PieceHelper.PawnCaptures(0, 0, x, y, i, a, true);
                    if (PawnHighlights) {
                        int origX = ChessBoard.Pieces[location].getXCord();
                        int origY = ChessBoard.Pieces[location].getYCord();


                        PieceHelper.pawnPinndHighligths(location, +100, l, location >= 16);
                        ChessBoard.Pieces[location] = new Pawn(origX, origY);
                        PieceHelper.pawnPinndHighligths(location, -100, l, location >= 16);


                        ChessBoard.Pieces[location] = new Pawn(origX, origY);
                    }
                }


            }
        } else {

            ChessBoard.Pieces[location] = new Pawn(x, y);
            MouseClicked.location = -1;
            if (y == 750 || y == 50) {
                ChessBoard.Pieces[location] = new Queen(x, y);


            }
            if (PieceHelper.isKingAttacked(location < 16, location, false, false)) {

                Main.InCheck = true;
                int s = 31;
                if (location >= 16) {
                    s = 15;
                }
                ChessPiece.checkLocation = location;
                if (PieceHelper.isKingAttacked(location < 16, location, false, true)) {
                    doubleCheck = true;
                }
                PieceHelper.getImage(s, ChessBoard.Pieces[s].getXCord(), ChessBoard.Pieces[s].getYCord());
            }

            ChessBoard.turn = !ChessBoard.turn;

        }
    }

    public static  void Highlight(int x, int y, boolean forBlack) {
        ChessBoard c = ChessBoard.instance;
        PImage gPawn;
        c.fill(0, 255, 0);
        c.rect(x - 50, y - 50, 100, 100);
        if (forBlack) {

            gPawn = c.loadImage("Images/BlackPawn.png");

        } else {
            gPawn = c.loadImage("Images/WhitePawn.png");
        }
        gPawn.resize(600, 450);
        c.image(gPawn, x, y);



    }

    public String getType() {
        return "PAWN";
    }

}

