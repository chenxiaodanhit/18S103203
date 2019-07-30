package Chess;

public class Action{
    public final int x;
    public final int y;
    public final boolean firstMoveOnly;
    public final boolean onTakeOnly;
    public Action(int x, int y, boolean firstMoveOnly, boolean onTakeOnly) {
        this. x = x;
        this. y = y;
        this.firstMoveOnly = firstMoveOnly;
        this.onTakeOnly = onTakeOnly;
    }
}
