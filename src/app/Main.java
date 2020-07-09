package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!match.getCheckmate()) {
			try {
				UI.clearScreen();
				UI.printMatch(match, captured);
				System.out.print("\nCurrent position: ");
				ChessPosition current = UI.readChessPosition(input);
				
				boolean[][] possibleMoves = match.possibleMoves(current);
				UI.clearScreen();
				UI.printBoard(match.getPieces(), possibleMoves);
				
				System.out.print("\nTargeted position: ");
				ChessPosition target = UI.readChessPosition(input);
				
				ChessPiece capturedPiece = match.movePiece(current, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (match.getPromoted() != null) {
					System.out.print("\nB = Bishop \nN = Knight \nR = Rook \nQ = Queen");
					System.out.print("\nEnter piece to replace promoted Pawn: ");
					String replacement = input.nextLine().toUpperCase();
					match.replacePromotedPiece(replacement);
				}
				
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				input.nextLine();
			}
		}
		
		UI.clearScreen();
		UI.printMatch(match, captured);

	}
}
