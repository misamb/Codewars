import java.util.Arrays;

public class MorseCodeDecoder {

    public static void main(String[] args) {
        String s = "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011";

        System.out.println(decodeBits(s));
    }
    public static String decode(String morseCode) {
        morseCode = morseCode.trim();
        if (morseCode == "···−−−···") {
            return "SOS";
        }
        StringBuilder result = new StringBuilder();
        StringBuilder currentLetter = new StringBuilder();

        for (int i = 0; i < morseCode.length(); i++) {
            char c = morseCode.charAt(i);

            if (Character.isWhitespace(c) && !currentLetter.isEmpty()) {
                //result.append(MorseCode.get(currentLetter.toString()));
                currentLetter.setLength(0);
                if (Character.isWhitespace(morseCode.charAt(i + 1))) {
                    result.append(" ");
                }
            } else if (!Character.isWhitespace(c)) {
                currentLetter.append(c);
            }
        }
        //result.append(MorseCode.get(currentLetter.toString()));
        return result.toString();
    }

    public static String decodeBits(String bits) {
        bits = bits.replace("0", " ").trim();

        int trRate = Math.min(trRateOf(bits, "1"), trRateOf(bits, " "));
        String wordDele = " ".repeat(trRate * 7);
        String letterDele = " ".repeat(trRate * 3);
        String morseDele = " ".repeat(trRate);

        String result = "";
        for (String word : bits.split(wordDele)) {
            for (String letter : word.split(letterDele)) {
                for (String morseSym : letter.split(morseDele)) {

                    if (morseSym.length() == trRate * 3) {
                        result += "-";
                    } else {
                        result += ".";
                    }
                }
                result += " ";
            }
            result += "  ";
        }
        return result.trim();
    }

    private static int trRateOf(String bits, String of) {
        String regex = of + "+";
        return Arrays.stream(bits.split(regex))
                .filter(s -> !s.isEmpty())
                .mapToInt(String::length)
                .min()
                .orElse(Integer.MAX_VALUE);
    }
}
