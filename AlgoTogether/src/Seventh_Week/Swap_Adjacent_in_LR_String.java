package Seventh_Week;

public class Swap_Adjacent_in_LR_String {
    class Solution {
        public boolean canTransform(String start, String end) {
            if (start.length() != end.length()) return false;
            if (!start.replace("X", "").equals(end.replace("X", ""))) return false;

            int p1 = 0, p2 = 0;
            while (p1 < start.length() && p2 < end.length()) {
                // get the non-X positions of 2 strings
                while (p1 < start.length() && start.charAt(p1) == 'X') {
                    p1++;
                }
                while (p2 < end.length() && end.charAt(p2) == 'X') {
                    p2++;
                }

                // if both of the pointers reach the end the strings are transformable
                if (p1 == start.length() && p2 == end.length()) {
                    return true;
                }
                // if only one of the pointer reach the end they are not transformable
                if (p1 == start.length() || p2 == end.length()) {
                    return false;
                }

                if (start.charAt(p1) != end.charAt(p2)) {
                    return false;
                }

                if (start.charAt(p1) == 'L' && p2 > p1) { // L should move to left, so start index i >= end index j
                    return false;
                }
                if (start.charAt(p1) == 'R' && p1 > p2) { // R should move to right, so start index i <= end index j
                    return false;
                }
                p1++;
                p2++;
            }
            return true;
        }
    }
}
