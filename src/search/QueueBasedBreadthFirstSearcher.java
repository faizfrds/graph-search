package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> solve() {
    	// TODO
		if (solution != null) {
			return solution;
		}
	
		final List<T> path = new ArrayList<T>();
		Queue<T> queue = new LinkedList<T>();
		List<T> visitedStates = new ArrayList<T>();
		queue.add(searchProblem.getInitialState());
	
		while (!queue.isEmpty()){

			T currentState = queue.remove();
			path.add(currentState);

			if (searchProblem.isGoalState(currentState)){

				if (isValid(path)) {
					solution = path;
					return path;
				} 
				else{
					throw new RuntimeException("searcher should never find an invalid solution!");
				}
			}

			visitedStates.add(currentState);

			for (T neighbor : searchProblem.getSuccessors(currentState)){

				if (!visitedStates.contains(neighbor)){
					queue.add(neighbor);
				}
			}
	
			while (!queue.isEmpty() && visitedStates.contains(queue.peek())){

				queue.remove();
				path.remove(0);
			}
		}

		solution = path;
		return path;
	}

}
