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
	
		final List<T> path = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		List<T> visitedSet = new ArrayList<T>();
		
		if (searchProblem.isGoalState(searchProblem.getInitialState())) return path;

		stack.push(searchProblem.getInitialState());


		while (!stack.empty()){
			T current = stack.pop(); 
			path.add(current);

			if (searchProblem.isGoalState(current)){

				if (isValid(path)){
					solution = path;
					return path;
				} 
				else{
					throw new RuntimeException();
				}
			}

			visitedSet.add(current);

			for (T adjV : searchProblem.getSuccessors(current)){

				if (!visitedSet.contains(adjV)){
					stack.push(adjV);
				}
			}
	
			while (!stack.empty() && visitedSet.contains(stack.peek())){

				stack.pop();
				path.remove(path.size()-1);
			}
		}
		
		solution = path;
		return path;
	}
}