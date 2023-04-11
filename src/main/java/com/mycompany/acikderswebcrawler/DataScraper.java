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
            DataScraper.GetSubjectData("https://acikders.ankara.edu.tr/course/view.php?id=7003");
    }
    public static void GetSubjectData(String url) {
        // Url olarak https://acikders.ankara.edu.tr/course/ adresinden herhangi bir dersin linki verilmeli
        try {
            // Jsoup kullanarak belirtilen url'den bir HTML dokümanı çekin
            Document doc = Jsoup.connect(url).get();

            // Kursun/Dersin Adi
            Elements subjectName = doc.select("p[class='tree_item branch active_tree_node']");
            System.out.println(subjectName.text());
            
            // Kursun egitmeninin adi
            Element tutorNameElement = doc.select("span[class='instancename']").first();
            String tutorName = tutorNameElement.text().replaceAll(" URL", "");
            System.out.println(tutorName);
            
            // Kursun iceriklerinin tamami
            Element mainDiv = doc.select("ul[class='topics']").first();
            
            // Kursun iceriklerinin haftalara bolunmus hali
            Elements subDivs = mainDiv.select("li[class='section main clearfix']");
             
            for (Element content : subDivs) {
                // Kursun Haftasinin Adi
                Elements sectionName = content.select("h3[class='sectionname']");
                System.out.println(sectionName.text());
                
                // Kursun haftasinin iceriginin linki
                Elements a = content.select("a[class='aalink']");
                String link = a.attr("href");
                System.out.println(link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}