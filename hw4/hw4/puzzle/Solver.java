package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState State;
        private int moves;
        private SearchNode preSN;

        public SearchNode(WorldState State, int moves, SearchNode preSN) {
            this.State = State;
            this.moves = moves;
            this.preSN = preSN;
        }

        @Override
        public int compareTo(SearchNode sn) {
            return this.moves + this.State.estimatedDistanceToGoal() - sn.moves - sn.State.estimatedDistanceToGoal();
        }
    }

    MinPQ<SearchNode> PQ = new MinPQ<>();
    private List<WorldState> bestsolution;
    private int totalMoves;

    private void getAnswer(SearchNode goal) {
        totalMoves = goal.moves;
        bestsolution = new ArrayList<>();
        SearchNode ptr = goal;
        while (ptr != null) {
            bestsolution.add(ptr.State);
            ptr = ptr.preSN;
        }
    }

    public Solver(WorldState initial) {
        SearchNode ini = new SearchNode(initial, 0, null);
        PQ.insert(ini);
        while (!PQ.isEmpty()) {
            SearchNode sn = PQ.delMin();
            if (sn.State.isGoal()) {
                getAnswer(sn);
                return;
            }
            for (WorldState neighbor: sn.State.neighbors()) {
                if (sn.preSN == null || !neighbor.equals(sn.preSN.State)) {
                    PQ.insert(new SearchNode(neighbor, sn.moves + 1, sn));
                }
            }
        }
    }

    public int moves() {
        return  totalMoves;
    }
    public Iterable<WorldState> solution() {
        List<WorldState> ret = new ArrayList<>();
        for (int i = totalMoves; i >= 0; i--) {
            ret.add(bestsolution.get(i));
        }
        return ret;
    }
}
