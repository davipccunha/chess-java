package app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner input) {
		try {
			String str = input.nextLine();
			char ch = str.charAt(0);
			char column = Character.toUpperCase(ch);
			int row = Integer.parseInt(str.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("\nError: invalid input");
		}
	}
	
	public static void printMatch(ChessMatch match, List<ChessPiece> captured) {
		printBoard(match.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println("\nTurn #" + match.getTurn());
		System.out.println("Waiting for " + match.getCurrentPlayer() + " player...");
	}

	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println("  A B C D E F G H");

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int k = 0; k < pieces.length; k++) {
				printPiece(pieces[i][k], false);
			}
			System.out.println();
		}
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.println("  A B C D E F G H");

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int k = 0; k < pieces.length; k++) {
				printPiece(pieces[i][k], possibleMoves[i][k]);
			}
			System.out.println();
		}
	}

	private static void printPiece(ChessPiece piece, boolean backgroundColor) {
		if (backgroundColor) {
			System.out.print(ANSI_RED_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_BLUE + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured pieces: ");
		System.out.print("White: " + ANSI_YELLOW);
		System.out.println(Arrays.toString(white.toArray()) + ANSI_RESET);
		System.out.print("Black: " + ANSI_BLUE);
		System.out.println(Arrays.toString(black.toArray()) + ANSI_RESET);
	}
}
