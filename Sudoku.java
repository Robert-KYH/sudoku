
class Sudoku {

  int[][] sudo = new int[9][9];

  Sudoku(String s) {
    char[] c = s.toCharArray();
    for (int r = 0; r < 9; ++r)
      for (int k = 0; k < 9; ++k)
        sudo[r][k] = Character.getNumericValue(c[10*r+k]);
  }


  void lös() {
    if (lösRekursiv(0, 0)) {
      for (int r = 0; r < 9; ++r) {
        for (int k = 0; k < 9; ++k)
          System.out.print(sudo[r][k]+" ");
        System.out.println();
      }

    } else  System.out.println("Går ej att lösa");
  }


  boolean lösRekursiv(int rad, int kol) {
    if (kol == 9) {  kol = 0;  ++rad;  }
    if (rad == 9)  return true;
    if (sudo[rad][kol] != 0)  return lösRekursiv(rad, kol+1);

    int ogiltiga = 0;
    for (int i = 0; i < 9; ++i) {
      int br = rad/3*3 + i/3, bk = kol/3*3 + i%3;
      ogiltiga |= 1<<sudo[br][bk];
      ogiltiga |= 1<<sudo[rad][i];
      ogiltiga |= 1<<sudo[i][kol];
    }

    for (int s = 1; s <= 9; ++s) {
      if ((ogiltiga & 1<<s) == 0) {
        sudo[rad][kol] = s;
        boolean res = lösRekursiv(rad, kol+1);
        if (res)  return true;
      }
    }

    sudo[rad][kol] = 0;
    return false;
  }


  public static void main(String[] args) {
    String s = """
               000000000
               010000020
               000000000
               000000000
               000050000
               000000000
               000000000
               040000030
               000000000
               """;

    new Sudoku(s).lös();
  }
}
