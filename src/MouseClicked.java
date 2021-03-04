public class MouseClicked {
    public static int location = -1;
    ChessBoard graph = ChessBoard.instance;

    public MouseClicked(int x, int y) {

        if (location != -1) {
            PieceHelper.PieceMoving(x, y, location, false);

        }

        boolean pieceClicked = false;

        if (location == -1) {

            int a = 0;
            int b = 16;
            if (!ChessBoard.turn)  {
              a = 16;
               b = 32;
            }

            for (int i = a; i < b; i++) {


                    pieceClicked = PieceHelper.IsPieceClicked(x, y, i);
                    if (pieceClicked) {

                        location = i;

                        break;
                    }



            }



            if (Main.oldX == x && Main.oldY == y && pieceClicked) {

                pieceClicked = false;
                Main.oldX = -1;
                Main.oldY = -1;
            }


            if (pieceClicked ) {


                PieceHelper.getImage(location, x,y);
                PieceHelper.PieceMoving(x, y, location, true);


                Main.oldX = x;
                Main.oldY = y;

            } else {
           graph.redrawEntireChessBoard();
                MouseClicked.location = -1;
                PieceHelper.HowManySpaces = -1;
                Main.IsPieceClicked = false;
                Main.oldX = -1;
                Main.oldY = -1;

            }


        }

    }


}
