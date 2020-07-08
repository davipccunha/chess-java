package board;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("\nError generating board. Not enough rows and/or columns");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("\nError: inexistent position");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardException("\nError: inexistent position");
		}
		return pieces[pos.getRow()][pos.getColumn()];
	}
	
	public void placePiece(Piece piece, Position pos) {
		if (isThereAPiece(pos)) {
			throw new BoardException("\nError: there is already a piece in position " + pos);
		}
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	public Piece removePiece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardException("\nError: inexistent position");
		}
		if (piece(pos) == null) {
			return null;
		}
		Piece p = piece(pos);
		p.position = null;
		pieces[pos.getRow()][pos.getColumn()] = null;
		return p;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position pos) {
		return positionExists(pos.getRow(), pos.getColumn());
	}
	
	public boolean isThereAPiece(Position pos) {
		if (!positionExists(pos)) {
			throw new BoardException("\nError: inexistent position");
		}
		return piece(pos) != null;
	}

}
