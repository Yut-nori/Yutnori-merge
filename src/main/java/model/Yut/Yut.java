package model.Yut;

import java.util.Random;

// Yut 각각 1개에 대해서 앞면과 뒷면이 나올 확률을 지정
// 빽도 윷인지 아닌지 확인하기 위해 isBackDoYut을 통해 빽도 전용 표시
// toss()를 통해 던질 때, flat이나 Round 중 하나를 반환
// 현재 코드상에서는 각각 50%씩으로 지정, 확률을 변경시에 이 클래스에서 지정 가능
public class Yut {
    private boolean isFlat; // 평평한 면  = 앞면
    private boolean isBackDoYut;

    public Yut(boolean isBackDoYut) {
        this.isBackDoYut = isBackDoYut;
    }

    public void toss() {
        Random rand = new Random();
        // 백도 윷도 평평한 면이 나올 수 있음.
        if(isBackDoYut) {
            isFlat = rand.nextBoolean();
        }
        else{
            isFlat = rand.nextBoolean();
        }
    }

    public boolean isFlat() {
        return isFlat;
    }

    public boolean isRound(){
        return !isFlat;
    }

    public boolean isBackDoYut() {
        return isBackDoYut;
    }
}