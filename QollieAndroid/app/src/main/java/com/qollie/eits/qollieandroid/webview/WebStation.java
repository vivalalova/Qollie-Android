package com.qollie.eits.qollieandroid.webview;

import android.content.Context;
import android.util.Log;

import com.qollie.eits.qollieandroid.R;

/**
 * Created by xuyating on 2017/2/27.
 */
public class WebStation {


    public String getCompanyTitle(Context context, String shareText) {

        String strCompanyTitle = "";
        //104
        if (shareText.contains(context.getString(R.string.domain_104))) {
            strCompanyTitle = get104CompanyTitle(shareText, context.getString(R.string.domain_104));
        }
        //1111
        else if (shareText.contains(context.getString(R.string.domain_1111))) {
            strCompanyTitle = get1111CompanyTitle(shareText, context.getString(R.string.domain_1111));
        } else {
            strCompanyTitle = "";
        }


        if (strCompanyTitle.isEmpty()) {
            Log.e("意料之外的分享網址", "" + shareText);
        }


        return strCompanyTitle;
    }

    private String get1111CompanyTitle(String shareText, String domain) {
        String[] shareArray = shareText.split(domain);
        if (shareArray.length == 2) {
            if (shareArray[0].contains("全文網址:")) {
                String[] shareArray2 = shareArray[0].split("全文網址:");
                if (shareArray2.length == 2) {
                    String strCompany = "";
                    String[] shareArrayHasDash = shareArray2[1].split("‧");
                    for (int i = 0; i < shareArrayHasDash.length; i++) {
                        if (shareArrayHasDash[i].contains("公司")) {

                            strCompany = shareArrayHasDash[i];
                            i = shareArrayHasDash.length;
                            return strCompany;
                        }
                    }
                    return strCompany;
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } else {

            return "";
        }
    }

    private String get104CompanyTitle(String shareText, String domain) {
        String[] shareArray = shareText.split(domain);
        if (shareArray.length == 2) {
            if (shareArray[0].contains("-")) {
                String[] shareArrayHasDash = shareArray[0].split("-");
                return shareArrayHasDash[0];
            } else {
                return shareArray[0];
            }
        } else {

            return "";
        }
    }


}
