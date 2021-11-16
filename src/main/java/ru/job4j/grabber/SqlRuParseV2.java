package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.time.LocalDateTime;

public class SqlRuParseV2 {

    private static String getDescription(Elements innerRow) {
        StringBuilder description = new StringBuilder();
        for (Element el : innerRow) {
            description.append(el.text());
        }
        return description.toString();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 2; i++) {
            SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                String link = href.attr("href");
                String title = href.text();
                LocalDateTime ldt = parser.parse(td.parent().child(5).text());
                Document innerDoc = Jsoup.connect(href.attr("href")).get();
                Elements innerRow = innerDoc.select(".msgBody").next();
                String description = getDescription(innerRow);
                Post post = new Post(title, link, description, ldt);
                System.out.println(post);
            }
        }
    }
}