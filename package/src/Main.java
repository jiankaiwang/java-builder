package src;

import libs.Cal;

public class Main {
  public static void main(String[] args) {
    Cal cal = new Cal();

    int[] values = {2, 3, 5, 7, 8, 9, 11, 13, 17, 19, 1033, 1037};
    for(int i = 0 ; i < values.length ; i++) {
      System.out.println(
        String.format("Is the value %d a prime? %b", 
          values[i], cal.IsPrimeNum(values[i])));
    }
  }
}