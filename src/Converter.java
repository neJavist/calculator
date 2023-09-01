import java.util.Arrays;

public class Converter {

    private final String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XIX", "XX", "XL", "L", "XC", "C"};
    private final int[] arabic = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 19, 20, 40, 50, 90, 100};

    public String arabicToRoman(int arabicNum) {
        StringBuilder romanNum = new StringBuilder();
        int[] minimumDifference = new int[arabic.length];

        while (arabicNum != 0) {
            int tempNumber = 1000, tempIndex = 0;
            for (int i = 0; i < arabic.length; i++) {
                minimumDifference[i] = Math.abs(arabicNum - arabic[i]);
                if (tempNumber > minimumDifference[i]) {
                    tempNumber = minimumDifference[i];
                    tempIndex = i;
                }

                if (arabicNum < arabic[tempIndex])
                    tempIndex -= 1;
            }

            romanNum.append(roman[tempIndex]);
            arabicNum -= arabic[tempIndex];
        }

        return String.valueOf(romanNum);
    }

    public int romanToArabic(String romanNum) {
        int index = 0;

        for (int i = 0; i < 11; i++) {
            if (romanNum.equals(roman[i])) {
                index = i;
                break;
            }
        }
        return arabic[index];
    }

    public boolean isRoman(String romanNum) {
        return Arrays.asList(roman).contains(romanNum);
    }
}
