package com.qiuchen.ly.iread.Presenter;

import com.qiuchen.ly.iread.DataModel.NewsM;
import com.qiuchen.ly.iread.DataModel.ResourceDynamicM;
import com.qiuchen.ly.iread.Model.DynamicRModel;
import com.qiuchen.ly.iread.Net.API_URL;
import com.qiuchen.ly.iread.View.DynamicView;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicRPresenter extends SimplePresenter<DynamicRModel,DynamicView> {
    @Override
    DynamicRModel createModel() {
        return new DynamicRModel();
    }
    public void GetNews() {
        SendRequest(API_URL.URL_MAINPAGE, new RequestCallback() {
            @Override
            public void onSuccess(String RetStr) {

                String img = Jsoup.parse(RetStr).getElementsByClass("tbl_ArticlePortlet_picTitleClass").get(0).getElementsByTag("script").get(0).data();

                //regex parse
                Pattern p = Pattern.compile("imgLink21\\[.*?\\]=\"(.*?)\";imgUrl21\\[.*?\\]=\"(.*?)\";imgText21\\[.*?\\]=\"(.*?)\"");
                Matcher m = p.matcher(img);
                List<NewsM> newsList = new ArrayList<>();
                while (m.find()) {
                    NewsM news = new NewsM();
                    news.imglink = API_URL.BASE_HOST + m.group(2);
                    news.link = "http://www.jsahvc.edu.cn" + m.group(1);
                    news.title = m.group(3);
                    newsList.add(news);
                }

                //resource dynamic
                int start = RetStr.indexOf("class=\"biaoti2\">资源动态</td>") + "class=\"biaoti2\">资源动态</td>".length();
                int end = RetStr.indexOf("<td width=\"78\" align=\"center\" class=\"biaoti\">数字资源</td>", start);
                String resDynamic = RetStr.substring(start, end);
                m = Pattern.compile("<a href='(.*?)' target=_blank title=\"(.*?)\">.*?</a></td><td  width='30' align=right><div style='white-space:nowrap'>(.*?)</div>").matcher(resDynamic);
                List<ResourceDynamicM> RSList = new ArrayList<>();
                while (m.find()) {
                    String link = API_URL.BASE_HOST + m.group(1),
                            tit = m.group(2),
                            date = m.group(3);
                    ResourceDynamicM mrs = new ResourceDynamicM();
                    mrs.date = date;
                    mrs.link = link;
                    mrs.title = tit;
                    RSList.add(mrs);
                }
                if (getView() != null) {
                    getView().getNews(newsList,RSList);
                }
            }

            @Override
            public void onFailed(String ReasonStr) {

            }
        });
    }

    public void initUrl() {
        SendRequest(API_URL.URL_INITCOOKIE, new RequestCallback() {
            @Override
            public void onSuccess(String RetStr) {

            }

            @Override
            public void onFailed(String ReasonStr) {

            }
        });
    }
}
