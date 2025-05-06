package univ_team1.dairyProject.dto;
import lombok.Data;

import java.time.LocalDate;

// 최근 7일 감정 데이터를 담는 DTO
@Data
public class DailyEmotionResponse {
  // 날짜 (예: 2025-05-01)
  private LocalDate date;
  // 감정 이름 (예: "HAPPY")
  private String emotion;
  // 이모지 (예: "😊")
  private String emoji;

  // 날짜와 감정 이름, 이모지를 받아 초기화
  public DailyEmotionResponse(LocalDate date, String emotion, String emoji) {
    this.date = date;
    this.emotion = emotion;
    this.emoji = emoji;
  }

  // 감정 없는 날을 위한 생성자
  public DailyEmotionResponse(LocalDate date) {
    this.date = date;
    this.emotion = null;
    this.emoji = null;
  }
}