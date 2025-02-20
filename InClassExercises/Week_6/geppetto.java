import java.util.*;

import javax.management.loading.MLet;

public class geppetto {

    public static void main(String[] args) {
        // ingedients 1 to N
        // some ingredients cannot mix
        // M pairs of ingredients that cannot mix
        // How many different pizzas can be made
        // Pizzas at ingredient are considered different of index i that is different
        // from index j

        Scanner sc = new Scanner(System.in);
        // first line contains N and M
        int N = sc.nextInt();
        int M = sc.nextInt();
        // each of the next M lines contains two integers A and B. 1<=A,B<=N. A and B
        // cannot mix. A and B can occur multiple times
        List<List<Integer>> cannotMix = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<Integer> pair = new ArrayList<>();
            pair.add(sc.nextInt());
            pair.add(sc.nextInt());
            cannotMix.add(pair);
        }
        sc.close();

        // output the number of different pizzas that can be made

        // create a list of ingredients
        List<Integer> ingredients = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            ingredients.add(i);
        }

        // calcuate the number of pizzas that can be made
       int count = N * (N - 1) / 2;
        for (int i = 0; i < ingredients.size(); i++) {
            for (int j = i + 1; j < ingredients.size(); j++) {
                List<Integer> pizza = new ArrayList<>();
                pizza.add(ingredients.get(i));
                pizza.add(ingredients.get(j));
                if (!cannotMix.contains(pizza)) {
                    count--;
                }
            }
        }
        if(count > 0) {
            System.out.println(count+1);
        } else {
            System.out.println(0);
        }
    }
}