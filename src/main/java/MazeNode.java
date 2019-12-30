import java.util.ArrayList;
import java.util.List;

public class MazeNode {

    List<MazeNode> outgoingEdges;

    boolean marked;

    public MazeNode() {
        this.outgoingEdges = new ArrayList<>();
        this.marked = false;
    }
}
