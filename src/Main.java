import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Læs alle variabler fra brugeren
        System.out.print("Vælg tidspunkt på dagen (dag/aften/nat): ");
        var time = getSpecificInput(Arrays.asList("dag", "aften", "nat"));

        System.out.print("Vælg traffikmængde (høj/lav): ");
        var traffic = getSpecificInput(Arrays.asList("høj", "lav"));

        System.out.print("Vælg traffikmængde (bolig/erhverv/blandet): ");
        var areaType = getSpecificInput(Arrays.asList("bolig", "erhverv", "blandet"));

        // Læs input som boolean, men oversæt ja/nej til true/false med simpel equals.
        System.out.print("Er det en nødsituation? (ja/nej) ");
        var emergency = getSpecificInput(Arrays.asList("ja", "nej")).equals("ja");

        var trafficLight = TrafficLight.RED;
        if (emergency || (time.equals("dag") && traffic.equals("lav"))) {
            trafficLight = TrafficLight.GREEN;
        } else if (time.equals("aften") || (time.equals("nat") && traffic.equals("lav"))) {
            trafficLight = TrafficLight.YELLOW;
        }
        // Denne her else if er lige meget, da traffiklyset allerede er rød som standard.
        // Den er her bare for at vise at vi ved hvad vi laver.
        // else if ((time.equals("nat") && traffic.equals("høj")) || (areaType.equals("bolig") && traffic.equals("høj"))) {
        //    trafficLight = TrafficLight.RED;
        //}

        var access = traffic.equals("lav") && (areaType.equals("erhverv") || areaType.equals("blandet"));

        // Samme princip gælder her. Vi behøver ikke engang et if statement, men bare at tjekke indline, da
        // vi kun har to udfald. Vi har dem dog stadig her som kommentarer for at vise hvad vi laver.
        // if (traffic.equals("lav") && (areaType.equals("erhverv") || areaType.equals("blandet"))) {
        //     access = true;
        // }
        // else if (areaType.equals("bolig") && time.equals("nat")) {
        //    access = false;
        // }

        var farve = trafficLight == TrafficLight.RED ? "rødt" : trafficLight == TrafficLight.YELLOW ? "gult" : "grønt";
        System.out.println(STR."Trafiklyset er \{farve}");
        System.out.println(access ? "Adgang tilladt" : "Adgang nægtet");
    }

    /**
     * Enum med værdier fra et traffiklys for at opnå
     * en type-stærkhed i koden, i stedet for strings🤮
     */
    enum TrafficLight {
        GREEN,
        YELLOW,
        RED,
    }

    /**
     * En funktion til at modtage en brugers input, begrænset til
     * de givne input i [allowed].
     * Funktionen læser inputtet, indtil det er et gyldigt input.
     */
    static String getSpecificInput(List<String> allowed) {
        Scanner scanner = new Scanner(System.in);
        var input = "";
        while (!allowed.contains(input)) {
            input = scanner.nextLine();
            if (!allowed.contains(input)) {
                System.out.println("Forkert valg. Vælg en af følgende: " + String.join("/", allowed));
            }
        }
        return input;
    }
}