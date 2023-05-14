package search;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;


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

		if (!isValid(solution)) return solution;
	
		final List<T> path = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		List<T> visitedStates = new ArrayList<T>();
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
		
		solution = path;
		return path;
	}
}