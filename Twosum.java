class Twosum{
    public static void main(String [] args){
        int[] num={2,5,7,11};
        int target=13;
        for(int i=0; i<num.length;i++){
            for(int j=0;j<num.length;j++){
                if(num[i]+num[j]==target){
                    System.out.println("indices"+i+"and"+j);    
                    return;
                }
            }
        }
        System.out.println("no two numbers found that add upto the target");
    }
}