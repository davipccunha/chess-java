package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch match;

	public King(Board board, Color color, ChessMatch match) {
		super(board, color);
		this.match = match;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position pos) {
		ChessPiece p = (ChessPiece) getBoard().piece(pos);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position pos) {
		ChessPiece p = (ChessPiece)getBoard().piece(pos);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position pos = new Position(0, 0);

		pos.setValues(position.getRow() - 1, position.getColumn());

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 1, position.getColumn());

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow(), position.getColumn() - 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow(), position.getColumn() + 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() - 1, position.getColumn() - 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() - 1, position.getColumn() + 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 1, position.getColumn() - 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		pos.setValues(position.getRow() + 1, position.getColumn() + 1);

		if (getBoard().positionExists(pos) && canMove(pos)) {
			matrix[pos.getRow()][pos.getColumn()] = true;
		}
		
		//Castling
		if (getMoveCount() == 0 && !match.getCheck()) {
			Position posRightRook = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(posRightRook)) {
				Position pos1 = new Position(position.getRow(), position.getColumn() + 1);
				Position pos2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(pos1) == null && getBoard().piece(pos2) == null) {
					matrix[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			Position posLeftRook = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posLeftRook)) {
				Position pos1 = new Position(position.getRow(), position.getColumn() - 1);
				Position pos2 = new Position(position.getRow(), position.getColumn() - 2);
				Position pos3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(pos1) == null && getBoard().piece(pos2) == null && getBoard().piece(pos3) == null) {
					matrix[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return matrix;
	}

}
