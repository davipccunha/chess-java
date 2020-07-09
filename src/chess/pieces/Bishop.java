package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position pos = new Position(0, 0);

		pos.setValues(position.getRow() - 1, position.getColumn() - 1);
		
		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setValues(pos.getRow() - 1, pos.getColumn() - 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		pos.setValues(position.getRow() - 1, position.getColumn() + 1);
		
		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setValues(pos.getRow() - 1, pos.getColumn() + 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		pos.setValues(position.getRow() + 1, position.getColumn() + 1);
		
		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setValues(pos.getRow() + 1, pos.getColumn() + 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		pos.setValues(position.getRow() + 1, position.getColumn() - 1);
		
		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setValues(pos.getRow() + 1, pos.getColumn() - 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		return matrix;
	}

}
