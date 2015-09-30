package cyclone.repo;

/**
 * Created by DilshaniS on 02/09/2015.
 */
public class ComparingManager {

        private char[] text;
        private char[] pattern;
        private int n;
        private int m;

        public void setString(String t, String p) {
            this.text = t.toCharArray();
            this.pattern = p.toCharArray();
            this.n = t.length();
            this.m = p.length();
        }

        public int search() {
            for (int i = 0; i < n-m; i++) {
                int j = 0;
                while (j < m && text[i+j] == pattern[j]) {
                    j++;
                }
                if (j == m) return i;
            }
            return -1;
        }
    }

    class Test {

        public static void main(String[] args) {
            ComparingManager comparingManager = new ComparingManager();
            String text = "Lorem ipsum dolor sit amet";
            String pattern = "ipsum";

            comparingManager.setString(text, pattern);
            int first_occur_position = comparingManager.search();
            System.out.println("The text '" + pattern + "' is first found after the " + first_occur_position + " position.");
        }

}
