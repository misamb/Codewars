import java.util.Stack;

public class DirectionsReduction {

    public static String[] dirReduc(String[] arr) {
        if (arr == null || arr.length == 1) {
            return arr;
        }

        Stack<String> stack = new Stack<String>();
        stack.push(arr[0]);
        int i = 1;
        while (i < arr.length) {
            String nextDirection = arr[i];
            if (stack.isEmpty()) {
                stack.push(nextDirection);
            } else {
                String stackTop = stack.pop();
                if (!isCollison(stackTop, nextDirection)) {
                    stack.push(stackTop);
                    stack.push(nextDirection);
                }
            }
            i++;
        }


        return stack.toArray(new String[stack.size()]);
    }

    private static boolean isCollison(String s1, String s2) {
        return s1 == "NORTH" && s2 == "SOUTH" || s1 == "SOUTH" && s2 == "NORTH"
                || s1 == "WEST" && s2 == "EAST" || s1 == "EAST" && s2 == "WEST";
    }
}
