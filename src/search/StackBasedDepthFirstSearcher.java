package search;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
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

			for (T neighbor : searchProblem.getSuccessors(currentState)){

				if (!visitedStates.contains(neighbor)){
					
					if (!states.contains(neighbor)){ //if neighbor hasnt been found before
		
						states.add(neighbor);
						predecessors.add(neighbor);	
					}
	
					predecessors.set(states.indexOf(neighbor), currentState); //setting the predecessor for correct indexing
					stack.push(neighbor);
					System.out.println(neighbor);
				}
			}

		}

		if (goal != null) {

			path.add(goal); //building path from goal-to-initial
			while (!goal.equals(searchProblem.getInitialState())){

				final T predecessor = predecessors.get(states.indexOf(goal));
				path.add(predecessor);
				goal = predecessor;
			}
			Collections.reverse(path); //reversing path from goal-to-initial to initial-to-goal
		}

		if (path.size() > 0){
			if (!isValid(path)){
				throw new RuntimeException("searcher should never find an invalid solution!");
			}
		}

		return path;
	}
}