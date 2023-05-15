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

	@Override
	public List<T> solve() {
		if (solution != null) {
			return solution;
		}

		List<T> predecessors = new ArrayList<T>();
		List<T> states = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		List<T> visitedStates = new ArrayList<T>();
		final List<T> path = new ArrayList<T>();

		T initial = searchProblem.getInitialState();

		stack.push(initial);
		states.add(initial);
		predecessors.add(initial);

		T goal = null;
		
		while (!stack.empty()){

			T currentState = stack.pop();

			if (searchProblem.isGoalState(currentState)){
				goal = currentState;
				break;
			}

			visitedStates.add(currentState);

			for (T neighbor : searchProblem.getSuccessors(currentState)) {
				if (!visitedStates.contains(neighbor)) {
	
					// if this neighbor hasn't been seen before
					if (!states.contains(neighbor)) {
						// add it to the list of states
						states.add(neighbor);
						// and set its predecessor to itself
						predecessors.add(neighbor);
						
					}
	
					// now set the neighbor's predecessor correctly
					predecessors.set(states.indexOf(neighbor), currentState);
					stack.push(neighbor);
					System.out.println(neighbor);
				}
			}

		}

		// if a goal was found
		if (goal != null) {
			// build a path by looking up each predecessor, starting from
			// the goal state
			path.add(goal);
			while (!goal.equals(searchProblem.getInitialState())) {
				final T predecessor = predecessors.get(states.indexOf(goal));
				path.add(predecessor);
				goal = predecessor;
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
		return path;
	
	}

}