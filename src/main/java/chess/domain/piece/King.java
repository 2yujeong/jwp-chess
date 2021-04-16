package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.movestrategy.NonIterableMoveStrategy;

import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color, new NonIterableMoveStrategy());
        this.type = Type.KING;
    }

    public King(Color color, char piece) {
        super(color, piece);
    }

    @Override
    public List<Direction> direction() {
        return Direction.everyDirection();
    }
}
