import java.util.Scanner;
import java.util.Stack;

public class StackAutomaton {
    private int[][][] transitionTable;
    private Stack<Character> stack;
    private String input;
    private int currentState;
    private boolean isAccepted;

    public StackAutomaton(int[][][] transitionTable, String input) {
        this.transitionTable = transitionTable;
        this.stack = new Stack<>();
        this.input = input;
        this.currentState = 0;
        this.isAccepted = false;
    }

    public void simulate() {
        for (int i = 0; i < input.length(); i++) {
            char currentInput = input.charAt(i);
            int nextState = transitionTable[currentState][currentInput - 'a'][0];
            char nextStackInput = (char) (transitionTable[currentState][currentInput - 'a'][1] + 'a');

            if (nextState == -1) {
                break;
            }

            currentState = nextState;

            if (nextStackInput != '#') {
                stack.push(nextStackInput);
            } else if (!stack.empty()) {
                stack.pop();
            }

            if (currentState == transitionTable.length - 1) {
                isAccepted = true;
                break;
            }
        }
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of states: ");
        int numOfStates = sc.nextInt();
        System.out.print("Enter the number of input symbols: ");
        int numOfInputs = sc.nextInt();
        int[][][] transitionTable = new int[numOfStates][numOfInputs][2];
        System.out.println("Enter the transition table: ");
        for (int i = 0; i < numOfStates; i++) {
            for (int j = 0; j < numOfInputs; j++) {
                transitionTable[i][j][0] = sc.nextInt();
                transitionTable[i][j][1] = sc.nextInt();
            }
        }
        System.out.print("Enter the input string: ");
        String input = sc.next();
        StackAutomaton automaton = new StackAutomaton(transitionTable, input);
        automaton.simulate();
        if (automaton.isAccepted()) {
            System.out.println("The input string is accepted.");
        } else {
            System.out.println("The input string is not accepted.");
        }
        sc.close();
    }
}