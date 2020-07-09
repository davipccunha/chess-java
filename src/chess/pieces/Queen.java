package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position pos = new Position(0, 0);

		pos.setValues(position.getRow() - 1, position.getColumn());

		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setRow(pos.getRow() - 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		pos.setValues(position.getRow() + 1, position.getColumn());

		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setRow(pos.getRow() + 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		pos.setValues(position.getRow(), position.getColumn() - 1);

		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setColumn(pos.getColumn() - 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		pos.setValues(position.getRow(), position.getColumn() + 1);

		while (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
			pos.setColumn(pos.getColumn() + 1);
		}
		if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
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
