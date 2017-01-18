package LeetCode;

/**
 * Created by kashishtayal on 1/17/17.
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedArray = merge(nums1,nums2);
        int len = mergedArray.length;
        if(len%2 == 0){
            return (mergedArray[len/2] + mergedArray[(len/2)+1])/2d;
        }else{
            return mergedArray[(len/2)];
        }
    }
    private int[] merge(int[] nums1, int[] nums2){
        if(nums1.length == 0) return nums2;
        if(nums2.length == 0) return nums1;
        int[] result = new int[nums1.length + nums2.length];
        int index = 0;
        int i = 0, j = 0;
        for(; i < nums1.length && j < nums2.length; ){
            if(nums1[i] < nums2[j]){
                result[index] = nums1[i];
                i++;
            }else{
                result[index] = nums2[j];
                j++;
            }
            index++;
        }
        if(i < nums1.length){
            for(;i<nums1.length;i++){
                result[index] = nums1[i];
                index++;
            }
        }
        if(j < nums2.length){
            for(;j<nums2.length;j++){
                result[index] = nums2[j];
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays dc = new MedianOfTwoSortedArrays();
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2,4};
        System.out.println(dc.findMedianSortedArrays(nums1,nums2));
    }
}
