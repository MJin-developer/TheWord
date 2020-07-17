package kor.co.mu.jin.theword;

import java.util.ArrayList;
import java.util.Collections;

public class IntrowordList {

    public String returnWord(){
        ArrayList<String> words = new ArrayList<>();

        words.add("도중에 포기하지 마세요. 망설이지 마세요. 성공할때 까지.");
        words.add("때로는 우연에 기대세요.");
        words.add("정답은 없습니다. 앞으로도 없을 겁니다. 지금까지도 없었습니다. 이게 해답입니다.");
        words.add("답은 없습니다. 누군가에게 점수를 부탁한적도 없으니까요.");
        words.add("소신을 가지세요.");
        words.add("당신의 직감을 믿으세요.");
        words.add("하고싶은 일이 아닌, 해야할 일을 먼저하세요.");
        words.add("천천히가도, 쉬어가도 괜찮습니다. 뒤로만 가지 마세요.");
        words.add("이해받기 위해 애쓰지 마세요.");
        words.add("얻는게 있다면, 잃는 것도 있습니다. 잃는게 있다면, 얻는것도 있겟지요.");
        words.add("운은 무시할 수 없습니다.");
        words.add("할까 말까 할때는 하지 마세요.");
        words.add("참으면 아무도 알 수 없습니다.");
        words.add("변하지 않는게 더 무서운겁니다.");
        words.add("길을 잃는다는 것은 곧 그 길을 알게된다는 것이 아닐까요?");
        words.add("믿음은 곧 생각이 되며, 생각은 곧 말이 됩니다. 그리고 말은 곧 행동이 되지요.");
        words.add("어차피 하고싶은 것을 하지 않나요 ?");
        words.add("항상 위에는 더 위가 있고, 바닥에는 더 바닥이 있습니다.");
        words.add("가진걸 보여주세요. 가지지도 않은 걸 얻으려 하지 마세요.");
        words.add("자기 자신을 존중하세요.");
        words.add("자신감과 자만심을 구별하세요.");
        words.add("실천할 수 없다면 그건그저 몽상입니다.");
        words.add("아무런 위험을 감수하지 않는다면 더 큰 위험을 감수하게 될겁니다.");
        words.add("밤에만 꿈을 꾸려하지 마세요.");
        words.add("당신의 그림자를 보지 마세요.");
        words.add("실패하지 않는 것 보다, 언제 실패하냐가 더 중요합니다.");
        words.add("생각하는 것을 멈추고, 지금을 보세요.");
        words.add("같은 잘못을 되풀이 하는것, 그게 진정 잘못입니다.");
        words.add("움직임과 행동을 혼돈하지 마세요.");

        Collections.shuffle(words);
        String result = words.get(0);

        return result;
    }

}
