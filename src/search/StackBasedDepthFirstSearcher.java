package search;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Currency;


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
	
		final List<T> path = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		List<T> visitedSet = new ArrayList<T>();
		T goal = null;
		T initial = searchProblem.getInitialState();
		T curr = initial;
		
		if (searchProblem.isGoalState(searchProblem.getInitialState())) return null;

		stack.push(searchProblem.getInitialState());


		/*while (!stack.empty()){
			T current = stack.pop(); 


			if (searchProblem.isGoalState(current)){
				
				goal = current;
				if (isValid(visitedSet)){
					solution = path;
					return path;
				} 
				else{
					throw new RuntimeException();
				}
			}


			if (!visitedSet.contains(current)){

				visitedSet.add(current);
			}


			for (T adjV : searchProblem.getSuccessors(current)){

				if (!visitedSet.contains(adjV)){
					stack.push(adjV);
					path.add(adjV);
					System.out.println(adjV);
				}
			}
	
			
			while (!stack.empty() && visitedSet.contains(stack.peek())){

				stack.pop();
				path.remove(path.size()-1);
			}
			
		}*/

		
		

		
		solution = path;
		return path;
	}
	
	private T iterativeDFSWithExplicitPredecessors(T initialState) {
		Stack<T> stack = new Stack<>();
		List<T> visited = new ArrayList<T>();
	
		stack.push(initialState);
	
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
	
					if (!visitedStates.contains(neighbor)) {
						visitedStates.add(neighbor);
					}
	
					stack.push(neighbor);
				}
			}
	
			if (!unvisitedNeighborFound) {
				visited.remove(state);
				//states.remove(state);
				//predecessors.remove(state);
			}
		}
	
		return null;
	}
	
}