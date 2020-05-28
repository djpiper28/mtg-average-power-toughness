package djpiper28.averagepowertoughness;

import forohfor.scryfall.api.Card;
import forohfor.scryfall.api.MTGCardQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scryfall search line:");
        String query = scanner.nextLine();

        System.out.println("Fetching cards...");
        List<Card> cards = MTGCardQuery.search(query);

        System.out.println("Found "+cards.size()+" cards.");

        int cardsWithoutPower = 0;
        int cardsWithoutToughness = 0;

        List<Float> power = new LinkedList<>();
        List<Float> toughness = new LinkedList<>();

        for(Card card: cards) {
            System.out.println("Found "+card.getName());

            // Get power
            if(card.getTypeLine().contains("Creature")) {
                try {
                    power.add(Float.parseFloat(card.getPower()));
                } catch(Exception e) {
                    cardsWithoutPower++;
                }
            }

            // Get toughness
            if(card.getTypeLine().contains("Creature")) {
                try {
                    toughness.add(Float.parseFloat(card.getToughness()));
                } catch(Exception e) {
                    cardsWithoutToughness++;
                }
            }
        }

        System.out.println("There are "+cardsWithoutPower+" cards with no power");
        System.out.println("There are "+cardsWithoutToughness+" cards with no toughness");

        System.out.println("Average power: "+average(power)+" Average toughness:"+average(toughness));

        System.exit(0);
    }

    private static float average(List<Float> numbersFloatList) {
        float total = 0;

        if(numbersFloatList.size() == 0) {
            return 0;
        }
        int length = numbersFloatList.size();

        while(!numbersFloatList.isEmpty()) {
            total += numbersFloatList.remove(0);
        }

        return total / length;
    }

}
