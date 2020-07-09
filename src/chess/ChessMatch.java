package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import board.Board;
import board.Piece;
import board.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkmate;

	private List<Piece> piecesInGame = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckmate() {
		return checkmate;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int k = 0; k < board.getColumns(); k++) {
				matrix[i][k] = (ChessPiece) board.piece(i, k);
			}
		}

		return matrix;
	}

	public boolean[][] possibleMoves(ChessPosition currentPosition) {
		Position pos = currentPosition.toPosition();
		checkCurrentPosition(pos);
		return board.piece(pos).possibleMoves();
	}

	public ChessPiece movePiece(ChessPosition currentPosition, ChessPosition targetedPosition) {
		Position current = currentPosition.toPosition();
		Position target = targetedPosition.toPosition();
		checkCurrentPosition(current);
		checkTargetedPosition(current, target);
		Piece capturedPiece = move(current, target);

		if (testCheck(currentPlayer)) {
			undoMove(current, target, capturedPiece);
			throw new ChessException("Error: You cannot check your own king");
		}

		check = (testCheck(opponent(currentPlayer))) ? true : false;

		if (testCheckmate(opponent(currentPlayer))) {
			checkmate = true;
		} else {
			nextTurn();
		}
		return (ChessPiece) capturedPiece;
	}

	public Piece move(Position current, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(current);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {
			piecesInGame.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void undoMove(Position current, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, current);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesInGame.add(capturedPiece);
		}
	}

	private void checkCurrentPosition(Position pos) {
		if (!board.isThereAPiece(pos)) {
			throw new ChessException("\nError: No piece found in selected position");
		}
		if (currentPlayer != ((ChessPiece) board.piece(pos)).getColor()) {
			throw new ChessException("\nError: Chosen piece is not " + currentPlayer);
		}
		if (!board.piece(pos).isThereAnyPossibleMove()) {
			throw new ChessException("\nError: No possible moves");
		}
	}

	private void checkTargetedPosition(Position current, Position target) {
		if (!board.piece(current).isMovePossible(target)) {
			throw new ChessException("\nError: Invalid target position");
		}
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesInGame.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("Error: There is no " + color + " king in game");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesInGame.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color))
				.collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] matrix = p.possibleMoves();
			if (matrix[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckmate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesInGame.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] matrix = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int k = 0; k < board.getColumns(); k++) {
					if (matrix[i][k]) {
						Position current = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, k);
						Piece capturedPiece = move(current, target);
						boolean testCheck = testCheck(color);
						undoMove(current, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesInGame.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('A', 1, new Rook(board, Color.WHITE));
		placeNewPiece('C', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('E', 1, new King(board, Color.WHITE));
        placeNewPiece('F', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('H', 1, new Rook(board, Color.WHITE));
        placeNewPiece('A', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('B', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('C', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('D', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('E', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('F', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('G', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('H', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('A', 8, new Rook(board, Color.BLACK));
        placeNewPiece('C', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('E', 8, new King(board, Color.BLACK));
        placeNewPiece('F', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('H', 8, new Rook(board, Color.BLACK));
        placeNewPiece('A', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('B', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('C', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('D', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('E', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('F', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('G', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('H', 7, new Pawn(board, Color.BLACK));
	}

}
