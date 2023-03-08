package pl.edu.pw.ee;

import java.util.ArrayList;

public class Surprise {

    public int countChanges(String cinema, int K) {
        if (cinema.length() < K) {
            return -1;
        }

        int changes = 0;
        char[] CINEMA = cinema.toCharArray();

        ArrayList<Integer> startIndexes = new ArrayList<>();
        ArrayList<Integer> endIndexes = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < cinema.length(); i++) {
            if (cinema.charAt(i) == 'M') {
                count++;
                if (count == 1) {
                    startIndexes.add(i);
                }
                if (count > 0 && i == cinema.length() - 1) {
                    endIndexes.add(i);
                }
            } else {
                if (count > 0) {
                    endIndexes.add(i - 1);
                }
                count = 0;
            }
        }

        int haveToChange = 0;
        int isResult = 0;
        for (int i = 0; i < startIndexes.size(); i++) {
            int size = endIndexes.get(i) - startIndexes.get(i) + 1;
            if (size > K) {
                haveToChange = 1;
            }
            if (size == K) {
                isResult = 1;
            }
        }

        if (haveToChange == 1) {
            for (int i = 0; i < startIndexes.size(); i++) {
                int size = endIndexes.get(i) - startIndexes.get(i);
                if (size +1> K) {
                    while (size + 1 > K) {
                        changes++;
                        size -= K;
                    }
                    isResult = 1;
                }
            }
        }
        
        if (isResult == 0) {
            
        }

        return changes;
    }

}
