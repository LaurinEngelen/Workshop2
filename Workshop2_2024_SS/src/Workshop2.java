// Workshop 2 SS 2024
// O. Bittel

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Workshop2 {
    private static final String BLUE = "\u001B[34m";
    private static final String BLACK = "\u001B[34m"; //"\u001B[30m";

    public static void main(String[] args) throws IOException {
        System.out.println("|---------------------------------------------------------------|");
        System.out.println("| Willkommen zum Workshop Funktionales Programmieren im SS 2024 |");
        System.out.println("| 66% (40 von 60 Punkte) der Teile müssen fehlerfrei laufen!    |");
        System.out.println("|---------------------------------------------------------------|");

        teil1(); //  7 Punkte
        teil2(); //  6 Punkte
        teil3(); // 11 Punkte
        teil4(); //  5 Punkte
        teil5(); // 31 Punkte

    }

    public static void teil1() {
        /* Bestimme die Anzahl der Zeichen in myString ohne das Zeichen 'a'.
         */

        String myString = "Bestimme die Anzahl der Zeichen in einem String ohne das Zeichen 'a'";
        System.out.println(BLUE + "\nTeil 1: " + myString);

        // a) (1 Punkt) Imperativ (mit einer Schleife):
        // ...
        int count1a = 0;
        for (char c : myString.toCharArray()){
            if(c != 'a') {
                count1a++;
            }
        }
        System.out.println(BLACK + "Imperativ: " + count1a + " Zeichen");

        // b) (3 Punkte) Funktional (Lambda-Ausdruck ohne Schleife mit Benutzung von String-Methoden).
        // Hinweis: String.replaceAll könnte hilfreich sein.
        // ...
        Function<String, Integer> func = s -> s.replaceAll("a", "").length();
        System.out.println("Funktional: "+ func.apply(myString) + " Zeichen");

        // c) (3 Punkte) Mit Streams.
        // Hinweis: Erzeugen Sie aus dem String einen Zeichen-Strom.
        // ...
        long count1c = myString.chars().filter(x->x != 'a').count();
        System.out.println("Streams: " + count1c + " Zeichen");
    }

    public static void teil2() {
        // Bestimme die Haeufigkeit des Strings myString in der String-Liste myList.
        System.out.println(BLUE + "\nTeil 2: Häufigkeit eines Strings in einer String-Liste");
        List<String> myList = List.of("das", "der", "die", "der", "der", "die", "der", "das", "die", "der");
        String myString = "der";

        // a) (1 Punkt) Externe Iteration mit einer for-each-Schleife:
        // ...

        int count2a = 0;
        for (String word : myList ){
            if(word.equals(myString)){
                count2a++;
            }
        }
        System.out.println(BLACK + "Externe Iteration: " + count2a + " Zeichen");

        // b) (3 Punkte) Interne Iteration mit forEach():
        AtomicInteger nAtomic = new AtomicInteger(0);
        // ...
        myList.forEach(word -> {
            if (word.equals(myString)) {
                nAtomic.incrementAndGet();
            }
        });

        System.out.println("Interne Iteration: " + nAtomic.get() + " Zeichen");

        // c) (2 Punkte) Mit einem Stream:
        // ...
        long count2c = myList.stream().filter(s -> s.equals(myString)).count();
        System.out.println("Streams: "+ count2a + " Zeichen");
    }

    public static void teil3() {
        // Liste von Städte und Strom-Operationen
        System.out.println(BLUE + "\nTeil 3: " + "Liste von Städte und Strom-Operationen");
        List<Stadt> sLst = new LinkedList<>();
        sLst.add(new Stadt("Muenchen","Deutschland",1_484_226 ));
        sLst.add(new Stadt("Paris","Frankreich",2_175_601));
        sLst.add(new Stadt("Valencia","Spanien",794_288  ));
        sLst.add(new Stadt("Porto","Portugal",238_000 ));
        sLst.add(new Stadt("Berlin","Deutschland",3_669_491));
        sLst.add(new Stadt("Mailand","Italien",1_396_059));
        sLst.add(new Stadt("Toulouse","Frankreich",493_465 ));
        sLst.add(new Stadt("Konstanz","Deutschland",84_911));
        sLst.add(new Stadt("Frankfurt","Deutschland",759_224));
        sLst.add(new Stadt("Marseille","Frankreich",919_305));
        sLst.add(new Stadt("Stuttgart","Deutschland",626_275));
        sLst.add(new Stadt("Lyon","Frankreich",522_969 ));
        sLst.add(new Stadt("Rom","Italien",4_333_274));
        sLst.add(new Stadt("Turin","Italien",870_952 ));
        sLst.add(new Stadt("Madrid","Spanien",3_266_126 ));
        sLst.add(new Stadt("Barcelona","Spanien",1_636_762 ));
        sLst.add(new Stadt("Lissabon","Portugal",2_963_272 ));

        // a) (3 Punkte)
        // Sortieren Sie die Liste von Städten alphabetisch nach dem Land und bei gleichem Land
        // nach dem Namen der Stadt. Geben Sie die sortierten Städte aus.
        // Versuchen Sie Comparator.comparing einzusetzen.
        System.out.println(BLACK);
        System.out.println("Städte alphabetisch nach dem Land sortiert und bei gleichem Land nach dem Namen der Stadt\n");
        // ...
        sLst.stream().sorted(Comparator.comparing(Stadt::name)).sorted(Comparator.comparing(Stadt::land)).forEach(System.out::println);

        // b) (2 Punkte) Erstellen Sie eine Statistik über die Einwohnerzahl (Anzahl, Summe, Minimum, Maximum und Mittelwert)
        System.out.println(BLACK);
        System.out.println("Statistik");
        // ...
        long anzahl_ews = sLst.stream().count();
        System.out.println("Anzahl: " + anzahl_ews);

        int summe_ews = sLst.stream().mapToInt(Stadt::ewz).sum();
        System.out.println("Summe: " + summe_ews);

        sLst.stream().min(Comparator.comparing(Stadt::ewz)).ifPresent(Stadt -> System.out.println("Minimum: " + Stadt.ewz()));

        sLst.stream().max(Comparator.comparing(Stadt::ewz)).ifPresent(Stadt -> System.out.println("Minimum: " + Stadt.ewz()));

        double mittelwert_ewz = summe_ews/anzahl_ews;
        System.out.println("Mittelwert: " + mittelwert_ewz);

        System.out.println(sLst.stream().mapToDouble(s -> s.ewz()).summaryStatistics());


        // c) (3 Punkte) Sammeln Sie alle Millionenstädte nach der Einwohnerzahl absteigend sortiert in einer Liste und geben Sie die Liste aus:
        System.out.println(BLACK);
        System.out.println("Millionenstädte sortiert");
        List<Stadt> mLst = null;
        // ...
        mLst = sLst.stream().filter(Stadt -> Stadt.ewz() >= 1000000).sorted(Comparator.comparing(Stadt::ewz).reversed()).collect(Collectors.toList());
        System.out.println(mLst);

        // d) (3 Punkte) Sammeln Sie alle Städte in einer Map. Die Map speichert für jedes Land eine Liste aller Städte des Landes.
        // Geben Sie die Map aus.
        System.out.println(BLACK);
        System.out.println("Alle Städte in einer Map");
        // ...
        NavigableMap<String, List> laenderMap = new TreeMap<>();
        System.out.println(sLst.stream().collect(Collectors.groupingBy(Stadt::land, Collectors.mapping(Stadt::name, Collectors.toList()))));
    }

    public static void teil4() {
        // (5 Punkte)
        /* Erzeugen Sie mit iterate einen Integer-Strom nach folgender Vorschrift:
         * Beginne mit irgendeiner natürlichen Zahl n = s > 0.
         * Ist n gerade, so wähle als nächste Zahl n/2.
         * Ist n ungerade, so wähle als nächste Zahl 3*n+1.
         * Wiederhole die Vorgehensweise.
         * Breche ab, falls n == 1 ist.
         * Es wird vermutet (Collatz-Vermutung), dass die Zahlenfolge immer bei 1 endet,
         * unabhängig vom Startwert s.
         */

        System.out.println(BLUE + "\nTeil 4: " + "Collatz-Folge" + BLACK);
        int s = 973;
        // ...
        IntStream
                .iterate(s, n -> {
                    if (n%2 == 0){
                        n = n/2;
                        return n;
                    } else {
                        n = 3*n+1;
                        return n;
                    }
                })
                .peek(System.out::println).anyMatch(n -> n == 1);
    }

    public static void teil5() throws IOException {
        // Kino-Filme und Stromoperationen
        System.out.print(BLUE + "\nTeil 5: Kinofilme");
        System.out.println(BLACK);

        // a) (4 Punkte)
        // Speisen Sie alle Kinofilme (jeweils eine Zeile) aus der Datei data/movies-top-grossing.txt
        // in einen Strom von Zeilen ein
        // und geben Sie nur den Titel der Kinofilme sortiert aus.
        // Bestimmen Sie außerdem die Anzahl der Filme.
        System.out.println("\n5 a)");
        BufferedReader in = new BufferedReader(new FileReader("Workshop2_2024_SS/data/movies-top-grossing.txt"));
        // ...
        in.lines().map(line -> line.split("\\(")[0]).sorted().forEach(System.out::println);
        in.close();
        // ...

        // b) (1 Punkt)
        // Die record-Klasse Movie stellt zwei Konstruktoren zur Verfügung.
        // Testen Sie die beiden Konstruktoren und erklären Sie die Funktionsweise.
        System.out.println("\n5 b)");
        Movie m1 = new Movie("101 Dalmatians", List.of("Benfield, John", "Braid, Hilda", "Capron, Brian"),1996);
        Movie m2 = new Movie("101 Dalmatians (1996)/Benfield, John/Braid, Hilda/Capron, Brian");
        // Ausgabe von m1 und m2 sollte identisch sein:
        System.out.println(m1);
        System.out.println(m2);

        // c) (4 Punkte)
        // Mit Hilfe von Strom-Operationen sollen alle Kinofilme in eine Movie-Liste gespeichert werden.
        // Ebenfalls mit Strom-Operationen sollen dann die ersten 3 Filme zeilenweise ausgegeben werden.
        System.out.println("\n5 c)");
        in = new BufferedReader(new FileReader("Workshop2_2024_SS/data/movies-top-grossing.txt"));
        // ...
        List <Movie> movieList = in.lines().map(Movie::new).collect(Collectors.toList());
        movieList.stream().limit(3).forEach(System.out::println);
        in.close();
        // ...

        // d) (3 Punkte)
        // Mit Hilfe von Strom-Operationen und der Movie-Liste aus c) sollen
        // alle Filmtitel mit Jahreszahl ausgegeben werden,
        // in denen der Schauspieler Morgan Freeman mitgespielt hat.
        System.out.println("\n5 d)");
        String s1 = "Freeman, Morgan";
        // ...
        movieList.stream().filter(movie -> movie.actors().contains(s1)).forEach(m -> System.out.println(m.title()+ " " + m.year()));

        // e) (4 Punkte)
        // Ermitteln Sie mit Stromoperationen, wie viele verschiedene
        // Schauspielerinnen und Schauspieler die Filmdatei enthält?
        // Hinweis: berücksichtigen Sie flatMap!
        System.out.println("\n5 e)");
        // ...

        // f) (5 Punkte)
        // Erstellen Sie mit Strom-Operationen eine Map, die für jede Jahreszahl
        // die entsprechende Liste von Filme abspeichert.
        // Geben Sie dann mit Strom-Operationen und der erstellten Map
        // alle Film des Jahres 1990 in jeweils einer Zeile aus.
        System.out.println("\n5 f)");
        // ...

        // g) (5 Punkte, etwas schwieriger)
        // Erstellen Sie mit Stromoperationen eine Map, die für jede Jahreszahl
        // die entsprechende Anzahl von Filmtiteln abspeichert.
        // Geben Sie dann mit Stromoperationen die Map zeilenweise nach der Jahreszahl sortiert aus.
        System.out.println("\n5 g)");
        // ...

        // h) (5 Punkte, etwas schwieriger)
        // Erstellen Sie eine TreeMap mapYearToTitles, die für jede Jahreszahl die entsprechende Liste von Filmtiteln abspeichert.
        // Siehe Beispiel in der Java API zu Collectors.groupingBy(classifier,mapFactory,downstream)
        // Geben Sie dann die Map aus.
        System.out.println("\n5 h)");
        // ...
        // System.out.println(mapYearToTitles);
    }
}