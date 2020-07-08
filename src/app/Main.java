package app;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		
		while (true) {
			UI.printBoard(match.getPieces());
			System.out.print("\nCurrent position: ");
			ChessPosition current = UI.readChessPosition(input);
			System.out.print("\nTargeted position: ");
			ChessPosition target = UI.readChessPosition(input);
			
			ChessPiece capturedPiece = match.movePiece(current, target);
		}

	}

}
