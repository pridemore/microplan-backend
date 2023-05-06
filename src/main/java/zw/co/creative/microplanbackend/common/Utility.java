package zw.co.creative.microplanbackend.common;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    private static final String INVALID_INPUT_GIVEN = "INVALID_INPUT_GIVEN";

    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    public static String convertMoneyToNumberMain(String money) {
        {
            String str2 = "";
            NumToWords w = new NumToWords();
            String amt = money;
            int rupees = Integer.parseInt(amt.split("\\.")[0]);
            String str1 = w.convert(rupees);
            str1 += " United States Dollars ";
            int paise = Integer.parseInt(amt.split("\\.")[1]);
            if (paise != 0) {
                str2 += " and";
                str2 = w.convert(paise);
                str2 += " Cents";
            }
            return str1 + str2 + " Only";
        }
    }

}
