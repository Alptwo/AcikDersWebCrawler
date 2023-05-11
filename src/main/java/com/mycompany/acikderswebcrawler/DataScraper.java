/**
 *
 * @author Alptwo
 */
package com.mycompany.acikderswebcrawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataScraper {

    public static void main(String[] args) {
        // Test
        // DataScraper.GetSubjectData("https://acikders.ankara.edu.tr/course/view.php?id=112");
    }

    public void GetSubjectData(String url) {
        //(tr) Url olarak https://acikders.ankara.edu.tr/course/ adresinden herhangi bir dersin linki verilmeli
        //(en) Url should be a subject link from https://acikders.ankara.edu.tr/course/

        //if Url contains page sayfalar arası geçi olmalı bazılarında page var page 0 1 gibi artıyor ona bak unutma
        try {
            Document doc = Jsoup.connect(url).get();

            //(tr) Kursun/Dersin Adi
            //(en) Name of the subject
            Elements subjectName = doc.select("p[class='tree_item branch active_tree_node']");
            System.out.println(subjectName.text());

            //(tr) Kursun egitmeninin adi
            //(en) Name of the tutor of the subject
            Element tutorNameElement = doc.select("span[class='instancename']").first();
            String tutorName = "0";
            try {
                tutorName = tutorNameElement.text().replaceAll(" URL", "");
            } catch (Exception e) {
                System.out.println("ÖĞRETMEN İSMİ BULUNAMADI: " + url);
            }
            System.out.println(tutorName);

            //(tr) Kursun iceriklerinin tamami
            //(en) All the contents of the subject
            Element mainDiv = doc.select("ul[class='topics']").first();
            if (mainDiv == null) {
                mainDiv = doc.select("ul[class='weeks']").first();
            }

            //(tr) Kursun iceriklerinin haftalara bolunmus hali
            //(en) Subject devided into weeks 
            if (mainDiv.select("li[class='section main clearfix']") != null) {
                Elements subDivs = mainDiv.select("li[class='section main clearfix']");

                for (Element content : subDivs) {
                    //(tr) Kursun haftasinin adi
                    //(en) Subjects content/week name
                    Elements sectionName = content.select("h3[class='sectionname']");
                    System.out.println(sectionName.text());

                    //(tr) Kursun haftasinin iceriginin linki
                    //(en) Link of the contents of the week
                    Elements a = content.select("a[class='aalink']");
                    String link = a.attr("href");
                    System.out.println(link);
                }
            } else {
                System.out.println("DERS İÇERİĞİ BOŞ: " + url);
            }
        } catch (IOException e) {
            System.out.println(" BÖYLE BİR DERS YOK: " + url);
            //e.printStackTrace();
        }
    }
}
