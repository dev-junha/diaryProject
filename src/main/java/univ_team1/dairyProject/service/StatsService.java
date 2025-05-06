package univ_team1.dairyProject.service;

// 필요한 클래스 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ_team1.dairyProject.domain.enums.Emotion;
import univ_team1.dairyProject.dto.DailyEmotionResponse;
import univ_team1.dairyProject.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
@Service
public class StatsService {

  @Autowired
  private DiaryRepository diaryRepository;

  // 월별 감정 통계 계산
  public Map<Emotion, Long> getMonthlyEmotionStats(int year, int month) {
    //리포지터리에서 통계 데이터 조회
    List<Object[]> results = diaryRepository.findEmotionStatisticsByMonth(year, month);
    //결과 저장 map함수
    Map<Emotion, Long> stats = new EnumMap<>(Emotion.class);

    //모든 감정 초기값 0으로 설정
    for (Emotion emotion : Emotion.values()) {
      stats.put(emotion, 0L);
    }

    //쿼리 결과 Map에 매핑
    for (Object[] result : results) {
      Emotion emotion = (Emotion) result[0];
      Long count = (Long) result[1];
      stats.put(emotion, count);
    }

    return stats;
  }
  // 최근 7일 감정 조회 메서드 추가
  public List<DailyEmotionResponse> getLast7DaysEmotions() {
    // 오늘 날짜 기준으로 최근 7일 계산
    LocalDate endDate = LocalDate.now();
    LocalDate startDate = endDate.minusDays(6); // 7일 전부터 오늘까지

    // 리포지토리에서 데이터 조회
    List<Object[]> results = diaryRepository.findEmotionsForLast7Days(startDate, endDate);

    // 날짜별 감정을 저장할 리스트
    List<DailyEmotionResponse> response = new ArrayList<>();

    // 최근 7일 날짜를 순회
    for (int i = 0; i < 7; i++) {
      LocalDate currentDate = endDate.minusDays(i);
      boolean found = false;

      // 조회된 데이터에서 해당 날짜의 감정 찾기
      for (Object[] result : results) {
        LocalDate date = (LocalDate) result[0];
        if (date.equals(currentDate)) {
          Emotion emotion = (Emotion) result[1];
          String emoji = getEmojiForEmotion(emotion);
          response.add(new DailyEmotionResponse(currentDate, emotion.name(), emoji));
          found = true;
          break;
        }
      }

      // 해당 날짜에 일기가 없으면 null로 설정
      if (!found) {
        response.add(new DailyEmotionResponse(currentDate));
      }
    }

    return response;
  }

  // 임시 이모지 매핑 메서드 (팀원과 협의 필요)
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