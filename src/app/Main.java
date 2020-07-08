package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		
		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(match.getPieces());
				System.out.print("\nCurrent position: ");
				ChessPosition current = UI.readChessPosition(input);
				
				boolean[][] possibleMoves = match.possibleMoves(current);
				UI.clearScreen();
				UI.printBoard(match.getPieces(), possibleMoves);
				
				System.out.print("\nTargeted position: ");
				ChessPosition target = UI.readChessPosition(input);
				
				ChessPiece capturedPiece = match.movePiece(current, target);
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			}
		}

	}

}
