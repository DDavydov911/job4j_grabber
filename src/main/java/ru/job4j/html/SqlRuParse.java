package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.time.LocalDateTime;

public class SqlRuParse {
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
                StringBuilder description = new StringBuilder();
                for (Element el : innerRow) {
                    description.append(el.text());
                }
                Post post = new Post(title, link, description.toString(), ldt);
                System.out.println(post);
            }
        }
    }
}