import org.junit.Before;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WojciechTeodorowiczTestTask1 {
    ArrayList<Period> discountPeriods;
    ArrayList<Period> discountPeriods1;
    ArrayList<Period> discountPeriods2;
    ArrayList<Period> discountPeriods3;

    ArrayList<Period> normalPeriods;
    ArrayList<Period> normalPeriods1;
    ArrayList<Period> normalPeriods2;
    ArrayList<Period> normalPeriods3;

    BigDecimal b;


    @Before
    public void instantiatePeriods() {
        discountPeriods = new ArrayList<>();
        discountPeriods.add(new Period(6,7));
        normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(2,5));
        normalPeriods.add(new Period(17,20));

        // Discount periods 2-4 18-19
        discountPeriods1 = new ArrayList<Period>() {{ add(new Period(2,4)); add(new Period(18,19)); }};
        // Normal periods 0-2 13-17
        normalPeriods1 = new ArrayList<Period>() {{ add(new Period(0,2)); add(new Period(13,17)); add(new Period(23,24)); }};

        // Discount periods 0-4 23-24
        discountPeriods2 = new ArrayList<Period>() {{ add(new Period(0,4)); add(new Period(23,24)); }};
        // Normal periods 6-23
        normalPeriods2 = new ArrayList<Period>() {{ add(new Period(6,23)); }};

        // Discount periods 0-2 13-17 and 23-24
        discountPeriods3 = new ArrayList<Period>() {{ add(new Period(0,2)); add(new Period(13,17)); add(new Period(23,24)); }};
        // Normal periods 2-4 5-12 17 - 19
        normalPeriods3 = new ArrayList<Period>() {{ add(new Period(2,4)); add(new Period(5,12)); add(new Period(17,19)); }};

        b = null;
    }


    /*
    TEST 1 check if kind is valid
     */
    @org.junit.Test
    public void kindIsValid() {
        Rate rt = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 2 check if normalrate is greater than 0
     */
    @org.junit.Test
    public void normalRateGreaterThanZero() {
        Rate rt = new Rate(CarParkKind.MANAGEMENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    /*
    TEST 3 check if normal rate = Max_Integer
     */
    @org.junit.Test
    public void normalRateMaxInt() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(Integer.MAX_VALUE), BigDecimal.valueOf(42), discountPeriods, normalPeriods);
    }

    /*
    TEST 4 check if normal rate is greater than discount rate
     */
    @org.junit.Test
    public void normalRateGreaterThanDiscountRate() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(4), discountPeriods, normalPeriods);
    }

    /*
    TEST 5 Normal rate cannot be less than 0
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThan0() throws Exception {
        Rate rt = new Rate(CarParkKind.STAFF, BigDecimal.valueOf(-1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 6 check if normal and disccount rate is == `1
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateEqualOne() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    /*
    TEST 7 normal or discpunt rate cannot be equal to 0 hours
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateEqualZero() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(0), BigDecimal.valueOf(0), discountPeriods, normalPeriods);
    }

    /*
    TEST 8 no chars allowed in integers
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateInvalidArgument() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf('W'), BigDecimal.valueOf('L'), discountPeriods, normalPeriods);
    }

    /*
    TEST 9
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void normalRateLessThanDiscountRate() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(5), BigDecimal.valueOf(7), discountPeriods, normalPeriods);
    }
    /*
    TEST 10 minumum discount time
     */
    @org.junit.Test
    public void discountPeriodMin() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 1));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }
    /*
    TEST 11 check if discount time is = 1
     */
    @org.junit.Test
    public void discountRateEqualToOne() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(2), BigDecimal.valueOf(1), discountPeriods, normalPeriods);
    }

    /*
        TEST 12 check if discount is larger than normal rate
         */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void discountRateGreaterThanNormalRate() throws Exception {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(1), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }
    /*
    TEST 13 discount cannot be lower than normal rate
     */
    @org.junit.Test
    public void discountRateMaxInt() {
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(Integer.MAX_VALUE), BigDecimal.valueOf(Integer.MAX_VALUE -1 ), discountPeriods, normalPeriods);
    }

    /*
   TEST 14 ceck if normal hours are in period time
    */
    @org.junit.Test
    public void normalPeriodValidPeriod() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{ add(new Period(11, 17));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 15 check if discount hours are in period
     */
    @org.junit.Test
    public void discountPeriodValidPeriod() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(11, 17));}};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 16: discountPeriod no period specified
     */
    @org.junit.Test
    public void discountPeriodNoPeriodSpecified() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 17: minimun range period for nomralhours
     */
    @org.junit.Test
    public void normalPeriodMin() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 1));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

    /*
    TEST 18: max range period for normal hours
     */
    @org.junit.Test
    public void normalPeriodMax() {
        ArrayList<Period> discountPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<Period>(){{add(new Period(0, 24));}};
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }

       /*
    TEST 19: Max Discount Period
     */
    @org.junit.Test
    public void discountPeriodMax() {
        ArrayList<Period> discountPeriods = new ArrayList<Period>(){{add(new Period(0, 24));}};
        ArrayList<Period> normalPeriods = new ArrayList<>();
        Rate rt = new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(3), BigDecimal.valueOf(2), discountPeriods, normalPeriods);
    }


}
