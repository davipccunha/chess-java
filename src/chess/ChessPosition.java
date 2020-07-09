package chess;

import board.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		if (column < 'A' || column > 'H' || row < 1 || row > 8) {
			throw new ChessException("Error: invalid row and/or column");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position(8 - row, column - 'A');
	}
	
	protected static ChessPosition toChessPosition(Position pos) {
		return new ChessPosition((char)('A' + pos.getColumn()), 8 - pos.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}

}
