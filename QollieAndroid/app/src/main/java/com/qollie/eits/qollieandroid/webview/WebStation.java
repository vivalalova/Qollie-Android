package com.qollie.eits.qollieandroid.webview;

/**
 * Created by xuyating on 2017/2/27.
 */
public enum WebStation {

    //1111
    ONE_ZERO_FOUR,
    //1111
    ONE_ONE_ONE,
    //123
    ONE_TWO_TREE,
    OTHER;

    public static WebStation getWebStation(String shareText) {
        if (shareText.contains("http://m.104.com.tw")) {

            return ONE_ZERO_FOUR;
        } else {
            return OTHER;
        }
    }


    public static String getCompany(String shareText) {

        WebStation webStation = getWebStation(shareText);

        switch (webStation) {
            case ONE_ZERO_FOUR:
                String[] shareArray = shareText.split("http://m.104.com.tw");
                if (shareArray.length == 2) {
                    if (shareArray[0].contains("-")) {
                        String[] shareArrayHasDash = shareArray[0].split("-");
                        return shareArrayHasDash[0];
                    } else {
                        return shareArray[0];
                    }
                } else {

                    return shareText;
                }

            case ONE_TWO_TREE:
                return shareText;
            default:
                return shareText;
        }

    }
}
