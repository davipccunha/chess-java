package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
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
		return (ChessPiece) capturedPiece;
	}

	public Piece move(Position current, Position target) {
		Piece p = board.removePiece(current);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}

	private void checkCurrentPosition(Position pos) {
		if (!board.isThereAPiece(pos)) {
			throw new ChessException("\nError: No piece found in selected position");
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

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('C', 1, new Rook(board, Color.WHITE));
		placeNewPiece('C', 2, new Rook(board, Color.WHITE));
		placeNewPiece('D', 2, new Rook(board, Color.WHITE));
		placeNewPiece('E', 2, new Rook(board, Color.WHITE));
		placeNewPiece('E', 1, new Rook(board, Color.WHITE));
		placeNewPiece('D', 1, new King(board, Color.WHITE));

		placeNewPiece('C', 7, new Rook(board, Color.BLACK));
		placeNewPiece('C', 8, new Rook(board, Color.BLACK));
		placeNewPiece('D', 7, new Rook(board, Color.BLACK));
		placeNewPiece('E', 7, new Rook(board, Color.BLACK));
		placeNewPiece('E', 8, new Rook(board, Color.BLACK));
		placeNewPiece('D', 8, new King(board, Color.BLACK));
	}

}
