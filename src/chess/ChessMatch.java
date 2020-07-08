package chess;

import board.Board;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int k = 0; k < board.getColumns(); k++) {
				matrix[i][k] = (ChessPiece) board.piece(i, k);
			}
		}
		
		return matrix;
	}

}
