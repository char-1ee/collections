package java.Junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

/**
 * 1. Junit编写unit testing
 * 2. Fixture 
 * 3. Exception testing
 * 4. Conditional testing
 * 5. Parameterized testing
 */
public class Test {

    /**
     * Assertion also defines:
     * assertTrue(): 期待结果为true
     * assertFalse(): 期待结果为false
     * assertNotNull(): 期待结果为非null
     * assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
     * 
     * 使用浮点数时，由于浮点数无法精确地进行比较，因此，我们需要调用assertEquals(double expected, double actual, double delta)这个重载方法，指定一个误差值：
     * assertEquals(0.1, Math.abs(1 - 9 / 10.0), 0.0000001);
     */
    public class FactorialTest {
        @Test
        void testFact() {
            assertEquals(1, Factorial.fact(1));
            assertEquals(2, Factorial.fact(2));
            assertEquals(6, Factorial.fact(3));
            assertEquals(3628800, Factorial.fact(10));
            assertEquals(2432902008176640000L, Factorial.fact(20));
        }

        /**
         * JUnit提供assertThrows()来期望捕获一个指定的异常
         * 第二个参数Executable封装了我们要执行的会产生异常的代码
         * 当我们执行Factorial.fact(-1)时，必定抛出IllegalArgumentException
         * assertThrows()在捕获到指定异常时表示通过测试，未捕获到异常，或者捕获到的异常类型不对，均表示测试失败
         */
        @Test
        void testNegative() {
            assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                        Factorial.fact(-1);
                }   
            });
        }
    }

    /**
     * Fixture:
     * 对于实例变量，在@BeforeEach中初始化，在@AfterEach中清理，它们在各个@Test方法中互不影响，因为是不同的实例；
     * 对于静态变量，在@BeforeAll中初始化，在@AfterAll中清理，它们在各个@Test方法中均是唯一实例，会影响各个@Test方法
     */
    public class CalculatorTest {
        Calculator calculator;

        @BeforeEach
        public void setUp() {
            this.calculator = new Calculator();
        }

        @AfterEach
        public void tearDown() {
            this.calculator = null;
        }
    
        @Test
        void testAdd() {
            assertEquals(100, this.calculator.add(100));
            assertEquals(150, this.calculator.add(50));
            assertEquals(130, this.calculator.add(-20));
        }
    
        @Test
        void testSub() {
            assertEquals(-100, this.calculator.sub(100));
            assertEquals(-150, this.calculator.sub(50));
            assertEquals(-130, this.calculator.sub(-20));
        }
    }

    /**
     * @DisabledOnOs(OS.WINDOWS)                                        不在Windows平台执行的测试
     * @DisableOnJre(JRE.JAVA_8)                                        只能在Java 9或更高版本执行的测试
     * @EnableIfSystemProperty(named = "os.arch", matches = ".*64.*")   只能在64位操作系统上执行的测试
     * @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true") 需要传入环境变量DEBUG=true才能执行的测试
     */
    public class ConfigTest {
        @Test
        @EnabledOnOs(OS.WINDOWS)
        void testWindows() {
            assertEquals("C:\\test.ini", config.getConfigFile("test.ini"));
        }

        @Test
        @EnabledOnOs({ OS.LINUX, OS.MAC })
        void testLinuxAndMac() {
            assertEquals("/usr/local/test.cfg", config.getConfigFile("test.cfg"));
        }
    }
    
    /**
     * @ParameterizedTest
     * @ValueSource
     */
    public class MathAbsTest {
        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 5, 100 })
        void testAbs(int x) {
            assertEquals(x, Math.abs(x));
        }
    }

    public class StringUtilTest {
        
        /**
         * @MethodSource 它允许我们编写一个同名的静态方法来提供测试参数
         */
        @ParameterizedTest
        @MethodSource
        void testCapitalize1(String input, String result) {
            assertEquals(result, StringUtils.capitalize(input));
        }

        static List<Arguments> testCapitalize() {
            return List.of( // arguments:
                    Arguments.arguments("abc", "Abc"), //
                    Arguments.arguments("APPLE", "Apple"), //
                    Arguments.arguments("gooD", "Good"));
        }

        /**
         *  @CsvSource 它的每一个字符串表示一行，一行包含的若干参数用,分隔，因此，上述测试又可以改写如下：
         */
        @ParameterizedTest
        @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
        void testCapitalize(String input, String result) {
            assertEquals(result, StringUtils.capitalize(input));
        }

        /**
         * @CsvFileSource 如果有成百上千的测试输入，那么，直接写@CsvSource就很不方便。这个时候，我们可以把测试数据提到一个独立的CSV文件中，然后标注上@CsvFileSource：
         */
        @ParameterizedTest
        @CsvFileSource(resources = { "/test-capitalize.csv" })  // JUnit只在classpath中查找指定的CSV文件，因此，test-capitalize.csv这个文件要放到test目录下
        void testCapitalizeUsingCsvFile(String input, String result) {
            assertEquals(result, StringUtils.capitalize(input));
        }
    }
    
}
