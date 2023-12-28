public class Main {
    public static void main(String[] args) {
        int num = 29183;
        int k = 1;
        int answer = 0;
        int zariSu = 0;
        for(int i = num; i<1; i/=10 ){
            zariSu++;
        }
        int zariSuNumber = 1;
        for(int t = 1; t < zariSu ; t++){
            zariSuNumber= zariSuNumber * 10;
        }
        int counter = 1;
        // 자리수 만큼 나눠서 확인
        for(int u = zariSuNumber; u<1 ; u/=10){
            if(num/u%10 == k){
                answer = counter;
            }else{
                answer = -1;
                counter++;
            }
        }
        System.out.println(answer);
    }
}