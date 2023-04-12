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
import java.util.ArrayList;

public class WebCrawler {
    public static void main(String[] args) {
        // Test
        //WebCrawler.GetCategoryLinks();
        WebCrawler.GetSubCategoryLinks("https://acikders.ankara.edu.tr/course/index.php?categoryid=123");
    }
    public static ArrayList<String> GetCategoryLinks() {
        try{
            String url = "https://acikders.ankara.edu.tr/course/";
            Document doc = Jsoup.connect(url).get();
            
            //(tr) Butun kategorilerin HTML'i aliniyor
            //(en) Getting HTML of all the categories
            Element allCategories = doc.select("div[class='subcategories']").first();
            Elements categories = allCategories.select("div[class='category notloaded with_children collapsed']");
            
            ArrayList<String> categoryUrls = new ArrayList<String>();
            for (Element category : categories) {
                Elements h3 = category.select("h3[class='categoryname aabtn']");
                Elements alink = h3.select("a[href]");
                for (Element aElement : alink) {
                    //(tr) Kategorilerin altindeki url'lerin toplanmasi
                    //(en) Getting the urls under the categories
                    String categoryUrl = aElement.attr("href");
                    categoryUrls.add(categoryUrl);
                    System.out.println(categoryUrl);
                }
            }
            return categoryUrls;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String> GetSubCategoryLinks(String url) {
        // I need to write the code to get SubCategory urls that some categories have if they dont have sub categories and just have the subject link than it should directly go to GetSubjectLinks function
        try{
            Document doc = Jsoup.connect(url).get();

            //(tr) Butun alt kategorilerin HTML'i aliniyor
            //(en) Getting HTML of all the sub categories
            Element subCategoriesDiv = doc.select("div[class='subcategories']").first();
            
            //(tr)Eger alt kategori varsa url'leri almali
            //(en) If there is any sub category than it should take the urls
            if(subCategoriesDiv != null){
                Elements categories = subCategoriesDiv.select("div[class='category notloaded with_children collapsed']");
                
                ArrayList<String> categoryUrls = new ArrayList<String>();
                for (Element category : categories) {
                    Elements h3 = category.select("h3[class='categoryname aabtn']");
                    Elements alink = h3.select("a[href]");
                    for (Element aElement : alink) {
                        //(tr) Alt ategorilerin altindeki url'lerin toplanmasi
                        //(en) Getting the urls under the subcategories
                        String categoryUrl = aElement.attr("href");
                        categoryUrls.add(categoryUrl);
                        System.out.println(categoryUrl);
                    }
                }
                return categoryUrls;
            }    
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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