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

		final T initialState = searchProblem.getInitialState();

		final List<T> path = new ArrayList<T>();
		Queue<T> queue = new LinkedList<T>();
		List<T> discoveredSet = new ArrayList<T>();

		if (initialState == null){
			return path;
		}
		
		if (searchProblem.isGoalState(initialState)){

			path.add(initialState);
			return path;
		}

		queue.add(initialState);
	
		while (!queue.isEmpty()) {

			T currentState = queue.remove();
			path.add(currentState);

			if (searchProblem.isGoalState(currentState)) {
				if (isValid(path)) {
					solution = path;
					return path;
				} 
				else {
					throw new RuntimeException();
				}
			}

			discoveredSet.add(currentState);

			for (T adjacent : searchProblem.getSuccessors(currentState)) {

				if (!discoveredSet.contains(adjacent)) {
					queue.add(adjacent);
				}
			}
	
			while (!queue.isEmpty() && discoveredSet.contains(queue.peek())) {

				queue.remove();
				path.remove(path.size() - 1);
			}
		}
	
		return path;
	}

}
