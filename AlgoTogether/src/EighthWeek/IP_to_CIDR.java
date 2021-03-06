package EighthWeek;

import java.util.ArrayList;
import java.util.List;

public class IP_to_CIDR {
    class Solution {
        public List<String> ipToCIDR(String ip, int n) {
            long x = 0;
            String[] ips = ip.split("\\.");
            for (int i = 0; i < 4; i++) {
                x = (x << 8) + Long.parseLong(ips[i]); // same as x * 256
            }

            List<String> res = new ArrayList<>();
            while (n > 0) {
                long count = x & (-x); // this count value here means if we don't change the current start ip, how many more ips we can represent with CIDR
                while (count > n) { // for example count is 7 but we only want to 2 more ips
                    count /= 2;
                }

                res.add(oneCIDR(x, count));
                n -= count;
                x += count;
            }
            return res;
        }

        private String oneCIDR(long x, long count) {
            int d, c, b, a;
            d = (int) x & 255;
            x >>= 8; // right shift by 8
            c = (int) x & 255;
            x >>= 8;
            b = (int) x & 255;
            x >>= 8;
            a = (int) x & 255;

            int len = 0;
            // this while loop to know how many digits of count is in binary
            // for example, 00001000 here the len will be 4.
            while (count > 0) {
                count /= 2;
                len++;
            }
            // Think about 255.0.0.7 -> 11111111 00000000 00000000 00000111
            // x & -x of it is 00000001, the mask is 32. (which is 32 - (1 - 1))
            int mask = 32 - (len - 1);

            StringBuilder sb = new StringBuilder();
            sb.append(a);
            sb.append(".");
            sb.append(b);
            sb.append(".");
            sb.append(c);
            sb.append(".");
            sb.append(d);
            sb.append("/");
            sb.append(mask);
            return sb.toString();
        }
    }
}
