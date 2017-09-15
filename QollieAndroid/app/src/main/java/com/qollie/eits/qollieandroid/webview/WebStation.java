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
        }
        //518
        else if (shareText.contains(context.getString(R.string.domain_518))) {
            strCompanyTitle = get518CompanyTitle(shareText);
        } else {
            strCompanyTitle = "";
        }


        if (strCompanyTitle.isEmpty()) {
            Log.e("意料之外的分享網址", "" + shareText);
        }


        return strCompanyTitle;
    }

    private String get518CompanyTitle(String shareText) {

        String[] shareArray = shareText.split("企業名稱：");

        if (shareArray.length == 2) {
            if (shareArray[1].contains("職缺名稱：")) {
                String[] shareArray2 = shareArray[1].split("職缺名稱：");
                if (shareArray2.length == 2) {
                    return shareArray2[0];
                }else {
                    return "";
                }
            } else if (shareArray[1].contains("薪資待遇：")) {
                String[] shareArray2 = shareArray[1].split("薪資待遇：");
                if (shareArray2.length == 2) {
                    return shareArray2[0];
                }else {
                    return "";
                }
            } else {
                return "";
            }
        } else {

            return "";
        }
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
                        if (shareArrayHasDash[i].contains("公司") || shareArrayHasDash[i].contains("企業")) {

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
