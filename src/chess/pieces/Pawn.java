package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch match;

	public Pawn(Board board, Color color, ChessMatch match) {
		super(board, color);
		this.match = match;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position pos = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			pos.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() - 2, position.getColumn());
			Position pos2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos) && getBoard().positionExists(pos2)
					&& !getBoard().isThereAPiece(pos2) && getMoveCount() == 0) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			// En passant
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == match.getEnPassantVulnerable()) {
					matrix[left.getRow() - 1][left.getColumn()] = true;
				}

				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == match.getEnPassantVulnerable()) {
					matrix[right.getRow() - 1][right.getColumn()] = true;
				}
			}

		} else {
			pos.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos)) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() + 2, position.getColumn());

			Position pos2 = new Position(position.getRow() + 1, position.getColumn());

			if (getBoard().positionExists(pos) && !getBoard().isThereAPiece(pos) && getBoard().positionExists(pos2)
					&& !getBoard().isThereAPiece(pos2) && getMoveCount() == 0) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			pos.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(pos) && isThereOpponentPiece(pos)) {
				matrix[pos.getRow()][pos.getColumn()] = true;
			}
			// En passant
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == match.getEnPassantVulnerable()) {
					matrix[left.getRow() + 1][left.getColumn()] = true;
				}

				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == match.getEnPassantVulnerable()) {
					matrix[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}

		return matrix;
	}
}
