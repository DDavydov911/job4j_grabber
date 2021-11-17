package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private static String getDescription(Elements innerRow) {
        StringBuilder description = new StringBuilder();
        for (Element el : innerRow) {
            description.append(el.text());
        }
        return description.toString();
    }

    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Document doc;
            try {
                doc = Jsoup.connect(link + "/" + i).get();
                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    result.add(detail(td));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Post detail(Element td) {
        Element href = td.child(0);
        String line = href.attr("href");
        String title = href.text();
        LocalDateTime ldt = dateTimeParser.parse(td.parent().child(5).text());
        Document innerDoc = null;
        try {
            innerDoc = Jsoup.connect(href.attr("href")).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements innerRow = innerDoc.select(".msgBody").next();
        String description = getDescription(innerRow);
        return new Post(title, line, description, ldt);
    }

    public static void main(String[] args) {
        SqlRuParse parser = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> posts = parser.list("https://www.sql.ru/forum/job-offers");
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}
