class Solution {
    public int singleNumber(int[] nums) {
        HashSet<Integer> seen=new HashSet<>();
        for (int i:nums){
            if (seen.contains(i)) {
                seen.remove(i);}
            else {
                seen.add(i);
            }

        }
        return seen.iterator().next();
    }
}
