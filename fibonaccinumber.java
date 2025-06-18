class Solution {
    public int fib(int n) {
        if(n==0){
             return 0;
        }
        if(n==1){
             return 1;
        }
        int f1=1,f2=0;
        for (int i=2;i<=n;i++) {
            int current=f1+f2;
            f2=f1;
            f1=current;
        }
        return f1;
    }
}
