public class Codec {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) == ';') {
                    sb.append("/;");
                }
                else if (s.charAt(i) == '/') {
                    sb.append("//");
                }
                else {
                    sb.append(s.charAt(i));
                }
                i++;
            }
            sb.append(";");
        }

        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> decodedStrings = new ArrayList<>();

        StringBuilder currentString = new StringBuilder();

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '/') {
                currentString.append(s.charAt(i + 1));
                i += 2;
            }
            else if (s.charAt(i) != ';') {
                currentString.append(s.charAt(i));
                i++;
            }
            else {
                decodedStrings.add(currentString.toString());
                currentString.setLength(0);
                i++;
            }
        }

        return decodedStrings;
    }
}
