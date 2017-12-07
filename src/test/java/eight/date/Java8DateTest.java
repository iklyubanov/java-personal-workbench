package eight.date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.IllegalFormatConversionException;

import static org.junit.Assert.*;

public class Java8DateTest {

    LocalTime currentTime;
    LocalDate currentDate;
    LocalDateTime currentDateTime;

    @Before
    public void init() {
        currentTime = LocalTime.now();
        currentDate = LocalDate.now();
        currentDateTime = LocalDateTime.of(currentDate, currentTime);
    }

    @Test(expected = IllegalFormatConversionException.class)
    public void shouldThrowFormatException() {
        //%[argument_index$][flags][width][.precision]conversion (%1$tm %1$te,%1$tY)
        //%ta %tb %td %tT %tZ %tY", e.g. "Sun Jul 20 16:17:00 EDT 1969"
        System.out.format("currentTime: %tT", currentTime);
        System.out.format("\ncurrentDate: %tB", currentDate);
        //Local currentDate times has not have currentTime zone (%tZ)
        System.out.format("\ncurrentDate currentTime: %tc %tZ %tY", currentDateTime);
    }

    @Test
    public void testMaxMinDates() {
        LocalDate max = LocalDate.MAX;
        LocalDate min = LocalDate.MIN;
        System.out.format("\nmax currentDate: %1$tY-%1$tm-%1$td", max);
        System.out.format("\nmin currentDate: %1$tY-%1$tm-%1$td", min);
        assertTrue(max.isAfter(min));
    }

    @Test
    public void testTimeZones() {
        ZoneId zoneId = ZoneId.systemDefault();
        assertEquals(zoneId.getId(), "Europe/Moscow");
        ZonedDateTime localZDT = ZonedDateTime.of(currentDateTime, zoneId);
        System.out.println("\nlocal zoned currentDate currentTime: " + localZDT.toString());
        ZoneId france = ZoneId.of("Europe/Paris");
        ZonedDateTime franceZDT = ZonedDateTime.of(localZDT.toLocalDateTime(), france);
        System.out.println("\nfrance zoned currentDate currentTime: " + franceZDT.toString());
        assertFalse(localZDT.isEqual(franceZDT));

        ZonedDateTime franceSameInstantZDT = localZDT.withZoneSameInstant(france);
        System.out.println("\nfrance zoned currentDate currentTime: " + franceSameInstantZDT.toString());
        ZonedDateTime franceSameLocalZDT = localZDT.withZoneSameLocal(france);
        System.out.println("\nfrance zoned currentDate currentTime: " + franceSameLocalZDT.toString());
        assertNotEquals(franceSameInstantZDT, franceSameLocalZDT);
    }


    /**
     * Kind of Assessment timer
     */
    @Test
    public void checkTimeBeforeAssessment() {
        LocalDateTime timeBeforeAssessmentReview = LocalDateTime.of(2017, 10, 30, 0, 0, 0, 0);
        Duration duration = Duration.between(currentDateTime, timeBeforeAssessmentReview);
        System.out.println("Days left before assessment: " + duration.toDays());
        System.out.println("Weeks left before assessment: " + Math.ceil(duration.toDays() / 7));
    }

    @Test
    public void checkTimeLeftBeforeRefundExpired() {
        LocalDate purchaseDate = LocalDate.of(2018, 4, 11);
        long duration = ChronoUnit.DAYS.between(purchaseDate, currentDate);
        long daysLeft = 90 - duration;
        System.out.println("Days left before refund expiring: " + daysLeft);
        System.out.println("Weeks left before refund expiring: " + Math.ceil(daysLeft / 7));
        Assert.assertTrue(duration < 90);
        Assert.assertTrue(daysLeft > 0);
    }

    @Test
    public void checkExamples() {
        LocalDate date = LocalDate.of(2014, 2, 15); // 2014-06-15
        boolean isBefore = LocalDate.now().isBefore(date); // false
        // information about the month
        Month february = date.getMonth(); // FEBRUARY
        int februaryIntValue = february.getValue(); // 2
        int minLength = february.minLength(); // 28
        int maxLength = february.maxLength(); // 29
        Month firstMonthOfQuarter = february.firstMonthOfQuarter(); // JANUARY
        // information about the year
        int year = date.getYear(); // 2014
        int dayOfYear = date.getDayOfYear(); // 46
        int lengthOfYear = date.lengthOfYear(); // 365
        boolean isLeapYear = date.isLeapYear(); // false
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekIntValue = dayOfWeek.getValue(); // 6
        String dayOfWeekName = dayOfWeek.name(); // SATURDAY
        int dayOfMonth = date.getDayOfMonth(); // 15
        LocalDateTime startOfDay = date.atStartOfDay(); // 2014-02-15 00:00
        // time information
        LocalTime time = LocalTime.of(15, 30); // 15:30:00
        int hour = time.getHour(); // 15
        int second = time.getSecond(); // 0
        int minute = time.getMinute(); // 30
        int secondOfDay = time.toSecondOfDay(); // 55800
    }

    public void checkPeriods() {
        // periods
        LocalDate firstDate = LocalDate.of(2010, 5, 17); // 2010-05-17
        LocalDate secondDate = LocalDate.of(2015, 3, 7); // 2015-03-07
        Period period = Period.between(firstDate, secondDate);
        int days = period.getDays(); // 18
        int months = period.getMonths(); // 9
        int years = period.getYears(); // 4
        boolean isNegative = period.isNegative(); // false
        Period twoMonthsAndFiveDays = Period.ofMonths(2).plusDays(5);
        LocalDate sixthOfJanuary = LocalDate.of(2014, 1, 6);
        // add two months and five days to 2014-01-06, result is 2014-03-11
        LocalDate eleventhOfMarch = sixthOfJanuary.plus(twoMonthsAndFiveDays);
        // durations
        Instant firstInstant = Instant.ofEpochSecond(1294881180); // 2011-01-13 01:13
        Instant secondInstant = Instant.ofEpochSecond(1294708260); // 2011-01-11 01:11
        Duration between = Duration.between(firstInstant, secondInstant);
        // negative because firstInstant is after secondInstant (-172920)
        long seconds = between.getSeconds();
        // get absolute result in minutes (2882)
        long absoluteResult = between.abs().toMinutes();
        // two hours in seconds (7200)
        long twoHoursInSeconds = Duration.ofHours(2).getSeconds();
    }

    @Test
    public void checkOfferAndLpdDatesDiffirence() {
        LocalDate firstLpdDate = LocalDate.of(2018, 2, 7);
        LocalDate offerDate = LocalDate.of(2017, 12, 30);
        long duration = ChronoUnit.DAYS.between(offerDate, firstLpdDate);
        System.out.println("Days before Lpd: " + duration);
    }

    @Test
    public void testLambdaWithOwnFucnInterface() {
        Func func = () -> System.out.println("Hello!");
        func.exec();
    }

    @FunctionalInterface
    private interface Func {
        void exec();
    }
}
