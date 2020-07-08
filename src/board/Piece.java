package board;

public abstract class Piece {

	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}

	public abstract boolean[][] possibleMoves();

	public boolean isMovePossible(Position pos) {
		return possibleMoves()[pos.getRow()][pos.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] matrix = possibleMoves();
		for (int i = 0; i < matrix.length; i++) {
			for (int k = 0; k < matrix.length; k++) {
				if (matrix[i][k]) {
					return true;
				}
			}
		}
		return false;
	}
}
