public class Cal {
  public boolean IsPrimeNum(int getNum) {
    if(getNum < 2) {
      throw new IllegalArgumentException(
        String.format("The value %d was not allowed.", getNum));
    }
    try {
      if(getNum == 2 || getNum == 3) {
        return true;
      }
      if(getNum % 2 == 0) {
        return false;
      }
      int stopNum = (int)Math.sqrt(getNum);
      for(int i = 3 ; i <= stopNum ; i = i + 2) {
        if(getNum % i == 0) {
          return false;
        }
      }
      return true;
    } catch (Exception err) {
      System.err.println(String.format("Failed in checking if the number is prime. %s", err));
      return false;
    }
  }
}