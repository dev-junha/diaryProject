package univ_team1.dairyProject.repository;

// 필요한 클래스 임포트
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import univ_team1.dairyProject.domain.Diary;
import univ_team1.dairyProject.domain.enums.Emotion;

import java.time.LocalDate;
import java.util.List;

// 기존 리포지토리 인터페이스
public interface DiaryRepository extends JpaRepository<Diary, Long> {

  // 기존 통계 쿼리 메서드
  @Query("SELECT d.emotion, COUNT(d) FROM Diary d " +
      "WHERE YEAR(d.createdDate) = :year AND MONTH(d.createdDate) = :month " +
      "GROUP BY d.emotion")
  List<Object[]> findEmotionStatisticsByMonth(
      @Param("year") int year,
      @Param("month") int month);

  // 최근 7일 데이터를 조회하는 쿼리 메서드 추가
  @Query("SELECT d.createdDate, d.emotion FROM Diary d " +
      "WHERE d.createdDate BETWEEN :startDate AND :endDate " +
      "ORDER BY d.createdDate DESC")
  List<Object[]> findEmotionsForLast7Days(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}