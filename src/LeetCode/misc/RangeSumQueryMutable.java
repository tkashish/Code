package LeetCode.misc;

/**
 * Created by tayalka on 2/9/2017.
 */
public class RangeSumQueryMutable {
    private int[] _tree;
    private int[] _nums;
    private boolean isSetUp = true;
    public RangeSumQueryMutable(int[] nums) {
        int len = nums.length;
        _tree = new int[len+1];
        _nums = nums;
        for (int i = 0; i < len ; i++) {
            update(i , 2*nums[i]);
        }
        isSetUp = false;
    }

    public void update(int i, int val) {
        int change = val-_nums[i];
        for(int parent = i+1; parent < _tree.length; parent+=parent&(-parent)){
            _tree[parent] += change;
        }
        if(!isSetUp)_nums[i] = val;
    }

    private int sum(int i){
        int sum = 0;
        int parent = i+1;
        while(parent > 0){
            sum += _tree[parent];
            parent -= parent&(-parent);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return sum(j)-sum(i-1);
    }

    public static void main(String[] args) {
        RangeSumQueryMutable r = new RangeSumQueryMutable(new int[]{1,3,5});
        System.out.println(r.sumRange(0,2));
    }
}
