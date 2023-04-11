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

public class WebCrawler {
    public static void main(String[] args) {
        // Test
            
    }
    public static void GetCategoryLinks() {
        try{
            String url = "https://acikders.ankara.edu.tr/course/";
            Document doc = Jsoup.connect(url).get();

            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String linkUrl = link.attr("href");
                System.out.println(linkUrl);
            }  
            // I should return an array? of the links that gathered so it can be used for data scraping
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void GetSubjectLinks(String url) {
        try{
            //(tr) Url https://acikders.ankara.edu.tr/course/ adresinden bir kategorinin linki olmalıdır
            //(en) Url should be a link of the category from https://acikders.ankara.edu.tr/course/
            Document doc = Jsoup.connect(url).get();

            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String linkUrl = link.attr("href");
                System.out.println(linkUrl);
            }  
            // I should return an array? of the links that gathered so it can be used for data scraping
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}