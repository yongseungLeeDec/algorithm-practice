package solution;

public class Feb_14 {
//    public static void main(String[] args) {
//        System.out.println(solution(5554)); // 10이어야 하지 않나??
//        System.out.println(solution(55));
//        System.out.println(solution(655)); // 5번 650 660
//    }

    public static int solution(int storey) {
        int r;
        int magicStoneCount = 0;

        while (storey > 0) {
            r = storey % 10;
            storey /= 10;

            if (r < 5) {
                magicStoneCount += r;
            } else if (r > 5){
                magicStoneCount += (10 - r);
                storey += 1;
            } else {
                magicStoneCount += r;
                if (storey % 10 >= 5) {
                    storey += 1;
                }
            }
        }

        return magicStoneCount;
    }
}
