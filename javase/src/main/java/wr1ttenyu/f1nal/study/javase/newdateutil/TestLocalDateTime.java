package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility.newdateutil;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {

    // 6. ZonedDate、ZonedTime、ZonedDateTime: 带时区的时间或日期
    @Test
    public void test7(){
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime);

        LocalDateTime localDateTime2 = LocalDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(localDateTime2);
    }

    // 时区
    @Test
    public void test6(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    //5. DateTimeFormatter : 解析和格式化日期或时间
    @Test
    public void test5() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(dateTimeFormatter.format(ldt));
    }

    // 4. TemporalAdjuster
    @Test
    public void test4() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);
    }

    // 3. Duration: used to calculation the difference between two time
    //    Period: used to calculation the difference between two LocalDate
    @Test
    public void test3() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime oldDateTime = LocalDateTime.now().minusDays(1);
        Duration duration = Duration.between(nowDateTime, oldDateTime);
        System.out.println(duration.getSeconds());

        LocalDate now = LocalDate.now();
        LocalDate old = LocalDate.now().minusDays(1);
        Period period = Period.between(now, old);
        System.out.println(period.getDays());


        Instant instantNow = Instant.now();
        Instant instantOld = Instant.now().minusSeconds(1000);
        Duration duration1 = Duration.between(instantNow, instantOld);
        System.out.println(duration1.getSeconds());
    }

    // 2.Instant: timeStamp （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
    @Test
    public void test2() {
        Instant instant = Instant.now();
        System.out.println(instant);
    }

    // 1.LocalDate、LocalTime、LocalDateTime
    @Test
    public void test1() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}
