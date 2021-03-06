package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}

	private boolean canMove(Position pos) {
		ChessPiece p = (ChessPiece) getBoard().piece(pos);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position pos = new Position(0, 0);

		pos.setValues(position.getRow() - 1, position.getColumn() - 2);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() - 2, position.getColumn() - 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() - 2, position.getColumn() + 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() - 1, position.getColumn() + 2);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 1, position.getColumn() + 2);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 2, position.getColumn() + 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 2, position.getColumn() - 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 1, position.getColumn() - 2);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}

		return matrix;
	}

}
