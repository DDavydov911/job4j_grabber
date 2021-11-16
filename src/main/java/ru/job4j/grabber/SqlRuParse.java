package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.DateTimeParser;
import ru.job4j.grabber.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        return null;
    }

    @Override
    public Post detail(String link) {
        Post post = null;
        /*
        Element href = element.child(0);
        String line = href.attr("href");
        String title = href.text();
        LocalDateTime ldt = dateTimeParser.parse(element.parent().child(5).text());
        Document innerDoc = null;
        try {
            innerDoc = Jsoup.connect(href.attr("href")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements innerRow = innerDoc.select(".msgBody").next();
        StringBuilder description = new StringBuilder();
        for (Element el : innerRow) {
            description.append(el.text());
        }
        post = new Post(title, line, description.toString(), ldt);

         */
        return post;
    }

    private String getDescription(Elements innerRow) {
        StringBuilder description = new StringBuilder();
        for (Element el : innerRow) {
            description.append(el.text());
        }
        return description.toString();
    }

    public static void main(String[] args) throws Exception {
        SqlRuDateTimeParser dateTimeParser = new SqlRuDateTimeParser();
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
