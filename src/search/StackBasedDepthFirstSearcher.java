package search;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Collections;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	List<T> predecessors = new ArrayList<T>();
	List<T> states = new ArrayList<T>();

	@Override
	public List<T> solve() {
		if (solution != null) {
			return solution;
		}

		final List<T> path = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		List<T> visitedStates = new ArrayList<T>();

		if (searchProblem.isGoalState(searchProblem.getInitialState())) return null;

		stack.push(searchProblem.getInitialState());
		
		while (!stack.empty()){

			T currentState = stack.pop();
			path.add(currentState);

			if (searchProblem.isGoalState(currentState)){

				if (isValid(path)){
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
					stack.push(neighbor);
				}
			}
	
			while (!stack.empty() && visitedStates.contains(stack.peek())){

				stack.pop();
				path.remove(path.size() - 1);
			}

		}
		
		if(!isValid(path)) throw new RuntimeException();

		if (solution.isEmpty()) return null;

		return path;

		/*final List<T> path = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		List<T> visitedSet = new ArrayList<T>();
		

		T initial = searchProblem.getInitialState();
		T curr = initial;

		if (searchProblem.isGoalState(searchProblem.getInitialState()))
			return null;

		stack.push(searchProblem.getInitialState());

		T current = iterativeDFSWithExplicitPredecessors(initial);
		System.out.println(current);

		if (current != null) {
			// build a path by looking up each predecessor, starting from
			// the current state
			path.add(current);
			while (!current.equals(initial)) {
				final T predecessor = predecessors.get(states.indexOf(current));
				path.add(predecessor);
				current = predecessor;
				System.out.println(predecessor);
			}

			// the path is in reverse order (goal-to-initial), so we reverse
			// it into the correct order
			Collections.reverse(path);
		}
		if (path.size() > 0) {
			if (!isValid(path)) {
				throw new RuntimeException(
						"searcher should never find an invalid solution!");
			}
		}
		return path;*/
	}

	private T iterativeDFSWithExplicitPredecessors(T initialState) {

		Stack<T> stack = new Stack<>();
		List<T> visited = new ArrayList<T>();

		stack.push(initialState);

		stack.push(initialState);
		predecessors.add(initialState);
	
		while (!stack.isEmpty()) {
			T state = stack.pop();
	
			if (searchProblem.isGoalState(state)) {
				return state;
			}
	
			visited.add(state);

			boolean unvisitedNeighborFound = false;
			for (T neighbor : searchProblem.getSuccessors(state)) {
				if (!visited.contains(neighbor)) {
					unvisitedNeighborFound = true;
	
					if (!states.contains(neighbor)) {
						states.add(neighbor);
						predecessors.add(neighbor);
					}
	
					stack.push(neighbor);
				}
			}
	
			if (!unvisitedNeighborFound) {
				visited.remove(state);
				states.remove(state);
				predecessors.remove(state);
			}
		}
	
		return null;
	}

}