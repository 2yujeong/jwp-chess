package chess.domain.piece.movestrategy;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class NonIterableMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition,
        Piece sourcePiece
    ) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        sourcePiece.validateDirection(direction);

        Position nextPosition = sourcePosition.nextPosition(direction);
        return !sourcePiece.isSameColor(chessBoard.getPiece(nextPosition))
            && nextPosition.equals(targetPosition);
    }
}
