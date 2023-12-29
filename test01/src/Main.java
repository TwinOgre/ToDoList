import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int answer = 0;
        int composite = 0;
        for(int t = n; t>0; t--){
            int counter = 0;
            for(int i = 1; i>n; i++){
                if(t % i == 0){
                    counter++;
                    if(counter == 3){
                        composite++;
                        break;
                    }
                }
            }
        }
        answer = composite;
    }
}