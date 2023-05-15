package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;



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

		List<T> predecessors = new ArrayList<T>();
		List<T> states = new ArrayList<T>();
		Queue<T> queue = new LinkedList<T>();
		List<T> visitedStates = new ArrayList<T>();
		final List<T> path = new ArrayList<T>();

		T initial = searchProblem.getInitialState();

		queue.add(initial);
		states.add(initial);
		predecessors.add(initial);

		T goal = null;
		
		while (!queue.isEmpty()){

			T currentState = queue.remove();

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
					queue.add(neighbor);
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
