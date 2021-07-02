package cs445.rec7;

import java.util.Arrays;

public class Queens {
    /**
     * Checks if a partial solution is a complete solution.
     * @param partial The partial solution
     * @return true if the partial solution is complete, false otherwise.
     */
    // partial = {0, 0, 0, 0, 0, 0, 0, 0}
    public static boolean isFullSolution(int[] partial) {
        // TODO: Implement this method
        //for (int i = 0; i < partial.length; i++){
        for (int i = 7; i >= 0; i--){
        	if (partial[i] == 0){
        		return false;
        	}
        }
        return true;
    }

    /**
     * Checks if a partial solution should be rejected because it can never be extended to a
     * complete solution.
     * @param partial The partial solution
     * @return true if the partial solution should be rejected, false otherwise.
     */
    public static boolean reject(int[] partial) {
        // TODO: Implement this method
        // return false;

        // check every pair of queens
        for (int i=0; i<8; i++){
        	for (int k=0; k<i; k++){

        		// one of these has no queen
        		if (partial[i] == 0 || partial[k] == 0){
        			continue;
        		}
        		// diff coln, queens are in the same row
        		else if (i != k && partial[i] == partial[k]){
        			return true;
        		}

        		// queens, positive diagonally, slope +1
        		else if (partial[i] - partial[k] == i - k){
        			return true;
        		}
        		// queens, negative diagonally, slope -1
        		else if (partial[i] - partial[k] == k - i){
        			return true;
        		}

        	}
            
        }
        return false;
    }

    /**
     * Extends a partial solution by adding one additional queen.
     * @param partial The partial solution
     * @return a partial solution with one more queen added, or null if no queen can be added.
     */
    public static int[] extend(int[] partial) {
        // TODO: implement this method
        int [] temp = new int[8];

        for (int i=0; i < 8; i++){

        	if (partial[i] != 0){
        		temp[i] = partial[i];
        	}
        	else {
        		// place a queen in the first row
        		temp[i] = 1;
        		return temp;
        	}
        }

        // if no queen can be added
        return null;
    }

    /**
     * Moves the most recently-placed queen to its next possible position.
     * @param partial The partial solution
     * @return a partial solution with the most recently-placed queen moved to its next possible
     * position, or null if we are out of options for the most recent queen.
     */
    public static int[] next(int[] partial) {
        // TODO: implement this method
        int [] temp = new int[8];
        int i = 0;

        while (i < 8){
        	// partial = {0, 0, 0, 0, 0, 0, 0, 0}
        	// partial = {2, 7, 4, 8, 3, 5, 0, 0}
        	// we've reached the last placed queen
        	if (i ==7 || partial[i+1] == 0) {
        		if (partial[i] == 8){
        			return null;
        		}
        		else {
        			temp[i] = partial[i] + 1;
        			break;
        		}
        	}
        	else {
        		// there's a queen but it's not the last one
        		temp[i] = partial[i];
        	}
        	i++;
        }
        return temp;
    }

    /**
     * Solves the 8-queens problem and outputs all solutions
     * @param partial The partial solution
     */
    public static void solve(int[] partial) {
        if (reject(partial)) return;
        if (isFullSolution(partial)) System.out.println(Arrays.toString(partial));
        int[] attempt = extend(partial);
        while (attempt != null) {
            solve(attempt);
            attempt = next(attempt);
        }
    }

    /**
     * Solves the 8-queens problem and returns one solution
     * @param partial The partial solution
     * @return A full, correct solution
     */
    public static int[] solveOnce(int[] partial) {
        if (reject(partial)) return null;
        if (isFullSolution(partial)) return partial;
        int[] attempt = extend(partial);
        while (attempt != null) {
            int[] solution = solveOnce(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }



    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("-a")) {
            // Find all solutions starting from the empty solution
            solve(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            // Find one solution starting from the empty solution
            int[] solution = solveOnce(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
            System.out.println(Arrays.toString(solution));
        }
    }
}

