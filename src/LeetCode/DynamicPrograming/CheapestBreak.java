package LeetCode.DynamicPrograming;//package dynamicProgramming;
//
//import java.util.*;
//
///**
// * Created by kashishtayal on 10/26/16.
// */
//public class CheapestBreak {
//    Node[][] matrix;
//    public List<Integer> cheapestBreakPoints(int inStringSize, List<Integer> inSizeList){
//        Collections.sort(inSizeList);
//        matrix = new Node[inStringSize][inStringSize];
//        HashMap<Integer, Set<List<Integer>>> possiblePositionForCut = new HashMap<>();
//        backtrack(possiblePositionForCut,0,inSizeList);
//        for(int i : possiblePositionForCut){
//            if(i){
//
//            }
//        }
//        return result;
//    }
//    public int cost(int start, int end, List<Integer> inSizeList){
//        if(start >= end) return -1;
//        int count = inSizeList.stream().reduce(0,Integer::sum);
//        int diff = end - start;
//        if(count != diff){
//            return -1;
//        }
//        Node node = matrix[0][diff];
//        int cost = -1;
//        if(node != null && (cost = node.find(inSizeList))>=0){
//            return cost;
//        }else{
//
//        }
//    }
//
//    public void backtrack(HashMap<Integer, Set<List<Integer>>> result, int start, List<Integer> inSizeList){
//        if(start == inSizeList.size()-1){
//            List<Integer> list = new ArrayList<>();
//            list.add(inSizeList.get(start));
//            Set<List<Integer>> combinations = result.get(inSizeList.get(start));
//            if(combinations == null) combinations = new HashSet<>();
//            combinations.add(list);
//            result.put(inSizeList.get(start),combinations);
//        }else{
//            int num = inSizeList.get(start);
//            backtrack(result,start+1,inSizeList);
//            for(Integer i : result.keySet()){
//                int sum = i+num;
//                Set<List<Integer>> set = result.get(sum);
//                if(set == null) set = new HashSet<>();
//                for(List<Integer> list : result.get(num)){
//                    List<Integer> interList = new ArrayList<>(list);
//                    interList.add(i);
//                    set.add(interList);
//                }
//                result.put(sum, set);
//                Set<List<Integer>> set1 = result.get(i);
//                if(set1 == null) {
//                    set1 = new HashSet<>();
//                    List<Integer> interList = new ArrayList<>();
//                    interList.add(i);
//                    set1.add(interList);
//                    result.put(i, set1);
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        CheapestBreak cb = new CheapestBreak();
//        List<Integer> sizeList = new ArrayList<>();
//        sizeList.add(2);
//        sizeList.add(2);
//        sizeList.add(2);
//        sizeList.add(2);
//        System.out.println(cb.cheapestBreakPoints(8, sizeList));
//    }
//
//    class Node{
//        private List<List<Integer>> lists;
//        private List<Integer> costs;
//        Node(){
//            costs = new ArrayList<>();
//            lists = new ArrayList<>();
//        }
//        public boolean add(List<Integer> list, int cost){
//            int i = lists.indexOf(list);
//            if(i < 0){
//                lists.add(list);
//                costs.add(cost);
//                return true;
//            }else{
//                if(costs.get(i) != cost){
//                    System.out.println("cost already associated and is not equal");
//                    return false;
//                }else{
//                    System.out.println("cost already associated and is equal");
//                    return false;
//                }
//            }
//        }
//        public int find(List<Integer> list){
//            int index = lists.indexOf(list);
//            return index < 0?-1:costs.get(index);
//        }
//    }
//}
