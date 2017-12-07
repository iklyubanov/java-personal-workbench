package eight.stream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class StreamTest {

    private Stream<String> stream;
    private Collection<String> collection;
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("\nStarting test: " + description.getMethodName());
        }
    };

    @Before
    public void init() {
        collection = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g"));
        stream = collection.stream();//Stream.of("a", "b", "c", "d", "e", "f", "g");
    }

    @Test
    public void testFilter() {
        //filter	Отфильтровывает записи, возвращает только записи, соответствующие условию
        assertEquals(1, stream.filter("a"::equals).peek((e) -> System.out.print("," + e)).count());
    }

    @Test
    public void testFilter2() {
        //filter	Отфильтровывает записи, возвращает только записи, соответствующие условию
        assertEquals(3, stream.filter("a, c, e"::contains).peek((e) -> System.out.print("," + e)).count());
    }

    @Test
    public void testSkip() {
        //skip	Позволяет пропустить N первых элементов
        assertEquals(2, stream.skip(5).peek((e) -> System.out.print("," + e)).count());
    }

    @Test
    public void testSkip2() {
        //skip	Позволяет пропустить N первых элементов
        Collection<String> result = stream.skip(4).collect(Collectors.toList());
        System.out.print("[");
        //joining element in single string
        System.out.print(result.stream().collect(Collectors.joining(", ")));
        System.out.println("]");
        assertEquals(3, result.size());

        assertNotEquals("1", collection.stream().skip(collection.size() - 1).findFirst().orElse("1"));
    }

    @Test
    public void testDistinct() {
        //distinct	Возвращает стрим без дубликатов (для метода equals)
        collection.addAll(Arrays.asList("a", "b", "c"));
        System.out.print(collection.stream().collect(Collectors.joining(", ")));
        System.out.println();
        assertEquals(10, collection.size());
        collection = collection.stream().distinct().collect(Collectors.toList());
        System.out.print(collection.stream().collect(Collectors.joining(", ")));
        assertEquals(7, collection.stream().distinct().count());
    }

    @Test
    public void testMap() {
        //map	Преобразует каждый элемент стрима
        String suffix = "_1";
        collection = collection.stream().map((s) -> s + suffix).collect(Collectors.toList());
        System.out.print(collection.stream().collect(Collectors.joining(", ")));

        assertThat(collection, everyItem(endsWith(suffix)));
    }

    @Test
    public void testPeek() {
        //peek	Возвращает тот же стрим, но применяет функцию к каждому элементу стрима
        collection = collection.stream().map(String::toUpperCase).peek((e) -> System.out.print("," + e)).
                collect(Collectors.toList());
        printCollection(collection);
    }

    @Test
    public void testLimit() {
        //limit	Позволяет ограничить выборку определенным количеством первых элементов
        assertEquals(3, stream.limit(3).peek((e) -> System.out.print("," + e)).count());
    }

    @Test
    public void testSkipLimit() {
        //skip	Позволяет пропустить N первых элементов
        //limit	Позволяет ограничить выборку определенным количеством первых элементов
        String[] ar = {"d", "e"};
        assertArrayEquals(ar, stream.skip(3).limit(2).peek((e) -> System.out.print("," + e)).toArray());
    }

    @Test
    public void testSorted() {
        //sorted Позволяет сортировать значения либо в натуральном порядке, либо задавая Comparator
        printCollection(collection);
        collection = collection.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        printCollection(collection);
        assertThat(collection, instanceOf(ArrayList.class));
        String first = collection.stream().findFirst().orElse("nope");
        assertThat(first, is("g"));
    }

    @Test
    public void testMapToPrimitive() {
        //mapToInt, mapToDouble, mapToLong	Аналог map, но возвращает числовой стрим (то есть стрим из числовых примитивов)
        collection = Arrays.asList("1", "2", "3", "4", "5");
        int[] expectedIntArray = {1, 2, 3, 4, 5};
        int[] array = collection.stream().mapToInt(Integer::parseInt).toArray();
        assertArrayEquals(expectedIntArray, array);
    }

    @Test
    public void testReduce() {
        //mapToInt, mapToDouble, mapToLong	Аналог map, но возвращает числовой стрим (то есть стрим из числовых примитивов)
        collection = Arrays.asList("1", "2", "3", "4", "5");
        OptionalInt op = collection.stream().mapToInt(Integer::parseInt)
                .reduce((d1, d2) -> d1 + d2);//or just use .sum() reduction function
        assertTrue(op.isPresent());
        assertEquals(15, op.orElse(0));
    }

    @Test
    public void testFlatMap() {
        //flatMap,flatMapToInt,flatMapToDouble,flatMapToLong	Похоже на map, но может создавать из одного элемента несколько
        collection = Arrays.asList("1,2,3", "4,5,6", "7,8,9", "a,b,c", "d,e,f");
        String[] ar = collection.stream().flatMap((p) -> Arrays.stream(p.split(","))).toArray(String[]::new);
        String result = Arrays.stream(ar).collect(Collectors.joining(", "));
        System.out.print(result);
        assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f", result);
    }

    @Test
    public void testConvertToMap() {
        List<Person> people = Arrays.asList( new Person("Вася", 16, Sex.MAN), new Person("Петя", 23, Sex.MAN),
                new Person("Елена", 42, Sex.WOMEN), new Person("Иван Иванович", 69, Sex.MAN));
        Map<String, Person> map = people.stream().collect(Collectors.toMap(Person::getName, p -> p));
        map.keySet().forEach(System.out::println);
    }

    @Test
    public void testConvertToMap2() {
        Person[] persons = {new Person("Вася", 16, Sex.MAN), new Person("Петя", 23, Sex.MAN),
                new Person("Елена", 42, Sex.WOMEN), new Person("Иван Иванович", 69, Sex.MAN)};
        Map<String, Integer> map = Arrays.stream(persons)
                .collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println(map);
    }

    @Test
    public void testConvertToMapByGroupBy() {
        List<Person> people = Arrays.asList( new Person("Вася", 16, Sex.MAN), new Person("Петя", 23, Sex.MAN),
                new Person("Елена", 42, Sex.WOMEN), new Person("Иван Иванович", 69, Sex.MAN));
        Map<String, List<Person>> map = people.stream().collect(Collectors.groupingBy(Person::getName));
        map.keySet().forEach(System.out::println);
    }

    @Test
    public void testMapWithSeparator() {
        final String text = "33100066\n" +
                "33100068\n" +
                "33100067\n" +
                "33100069\n" +
                "33100070\n" +
                "33100071\n" +
                "33100073\n" +
                "33100072\n" +
                "33100075\n" +
                "33100074\n" +
                "33100076\n" +
                "33100077\n" +
                "33100078\n" +
                "33100079\n" +
                "33100080\n" +
                "33100081\n" +
                "33100082\n" +
                "33100083\n" +
                "33100085\n" +
                "33100084\n" +
                "33100086\n" +
                "33100087\n" +
                "33100088\n" +
                "33100089\n" +
                "33100090";
        Optional<String> optionalString = Arrays.stream(text.split("\n"))
//                .reduce((a, b) -> (a.startsWith("'") ? "" : "'") +
//                        a + "', '" + b + (b.startsWith("'") ? "" : "'"));
//'33100037', '33100038'', '33100034'', '33100036'', '33100035'', '33100031'', '33100033'', '33100032'', '33100029'', '33100030'', '33100026'', '33100027'', '33100028'', '33100024'', '33100025'', '33100020'', '33100019'', '33100023'', '33100021'', '33100022'
                    .reduce((a, b) -> a + "', '" + b);
        assertTrue(optionalString.isPresent());
        System.out.println("'" + optionalString.get() + "'");

    }

    //terminal methods tests

///
    private enum Sex {
        MAN, WOMEN
    }

    private class Person {
        private String name;
        private int age;
        private Sex sex;

        Person(String name, int age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        int getAge() {
            return age;
        }

        void setAge(int age) {
            this.age = age;
        }

        Sex getSex() {
            return sex;
        }

        void setSex(Sex sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }
    }

    @Test
    public void testPeople() {
        List<Person> people = Arrays.asList( new Person("Вася", 16, Sex.MAN), new Person("Петя", 23, Sex.MAN),
                new Person("Елена", 42, Sex.WOMEN), new Person("Иван Иванович", 69, Sex.MAN));
        //Выбрать мужчин-военнообязанных (от 18 до 27 лет)
        List<Person> result = people.stream().filter((p)-> p.getAge() >= 18 && p.getAge() < 27
                && p.getSex() == Sex.MAN).peek(System.out::println).collect(Collectors.toList());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getName(), is("Петя"));

        //Найти средний возраст среди мужчин
        double avgMenAge = people.stream().filter((p) -> p.getSex() == Sex.MAN).
                mapToInt(Person::getAge).average().getAsDouble();
        System.out.println("average men age: " + avgMenAge);
        assertEquals(36, avgMenAge, 1);

        //Найти кол-во потенциально работоспособных людей в выборке
        // (т.е. от 18 лет и учитывая что женщины выходят в 55 лет, а мужчина в 60)
        long potencialWorkersCount = people.stream().filter((p) -> p.getAge() >= 18).filter(
                (p) -> (p.getSex() == Sex.WOMEN && p.getAge() < 55) || (p.getSex() == Sex.MAN && p.getAge() < 60)).count();
        System.out.println("potencial workers count: " + potencialWorkersCount);
        assertThat(potencialWorkersCount, is(2L));
    }

    private static void printCollection(final Collection<String> collection) {
        System.out.println();
        System.out.print("[");
        //joining element in single string
        collection.stream().forEach(System.out::print);
        System.out.println("]");
    }
}
