package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        for (int i = 1; i < nums.length; i++) {
            double tmp = nums[i];
            int j = i;
            for (; j > 0 && nums[j - 1] > tmp; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = tmp;
        }
    }
}
