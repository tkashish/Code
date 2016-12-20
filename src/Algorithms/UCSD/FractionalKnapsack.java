package Algorithms.UCSD;

import java.util.Arrays;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int len = values.length;
        Node[] frac = new Node[len];
        for(int i = 0; i < len; i++){
            frac[i] = new Node((double)values[i]/(double)weights[i],i);
        }
        Arrays.sort(frac);
        int index = 0;
        while(capacity > 0){
            Node node = frac[index];
            if(capacity >= weights[node.index]){
                capacity -= weights[node.index];
                value += values[node.index];
                index++;
            }else{
                value += capacity*node.frac;
                capacity = 0;
            }
        }
        return value;
    }
    private static class Node implements Comparable<Node>{
        double frac;
        int index;

        public Node(double v, int i) {
            frac = v;
            index = i;
        }

        @Override
        public int compareTo(Node o) {
            if(o.frac > frac)return 1;
            else if(o.frac < frac)return -1;
            else return 0;
        }
    }
    public static void main(String args[]) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int capacity = scanner.nextInt();
//        int[] values = new int[n];
//        int[] weights = new int[n];
//        for (int i = 0; i < n; i++) {
//            values[i] = scanner.nextInt();
//            weights[i] = scanner.nextInt();
//        }
//        System.out.println(getOptimalValue(capacity, values, weights));
        {
            int capacity = 50;
            int[] values = new int[]{60, 100, 120};
            int[] weights = new int[]{20, 50, 30};
            System.out.println(getOptimalValue(capacity, values, weights));
        }
        {
            int capacity = 10;
            int[] values = new int[]{500};
            int[] weights = new int[]{30};
            System.out.println(getOptimalValue(capacity, values, weights));
        }
    }
}
