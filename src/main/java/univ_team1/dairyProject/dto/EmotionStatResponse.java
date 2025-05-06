package univ_team1.dairyProject.dto;

import lombok.Data;
import univ_team1.dairyProject.domain.enums.Emotion;

@Data
public class EmotionStatResponse {
  private String emotion;
  private Long count;
  private String emoji;

  public EmotionStatResponse(Emotion emotion, Long count) {
    this.emotion = emotion.name(); //감정이름 문자열로 설정 왜?
    this.count = count;
    this.emoji = getEmojiForEmotion(emotion);
  }

  //이모지 매핑 메서드
  private String getEmojiForEmotion(Emotion emotion) {
    switch (emotion) {
      case HAPPY: return "😊";
      case SMILE: return "😀";
      case SO_SO: return "😐";
      case SAD: return "😢";
      case ANGRY: return "😤";
      default: return "";
    }
  }
}