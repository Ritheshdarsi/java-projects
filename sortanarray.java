class Solution {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int n = high - low + 1;
        int[] temp = new int[n];
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            temp[k++] = (nums[i] <= nums[j]) ? nums[i++] : nums[j++];
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= high) {
            temp[k++] = nums[j++];
        }

        System.arraycopy(temp, 0, nums, low, n);
    }
}
