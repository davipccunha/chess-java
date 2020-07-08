package app;

import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println("  A B C D E F G H");
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int k = 0; k < pieces.length; k++) {
				printPiece(pieces[i][k]);
			}
			System.out.println();
		}
	}

	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}

}
