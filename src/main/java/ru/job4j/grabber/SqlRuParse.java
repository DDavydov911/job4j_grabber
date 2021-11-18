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

    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            try {
                Document doc = Jsoup.connect(link + "/" + i).get();
                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    Element href = td.child(0);
                    String ln = href.attr("href");
                    result.add(detail(ln));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Post detail(String link) {
        String title = null;
        String description = null;
        LocalDateTime ldt = null;
        try {
            Document innerDoc = Jsoup.connect(link).get();
            title = innerDoc.select(".messageHeader").get(0).text();
            ldt = dateTimeParser.parse(innerDoc.select(".msgFooter").get(0).text());
            description = innerDoc.select(".msgBody").get(1).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Post(title, link, description, ldt);
    }

    public static void main(String[] args) {
        SqlRuParse parser = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> posts = parser.list("https://www.sql.ru/forum/job-offers");
        for (Post post : posts) {
            System.out.println(post);
        }
    }
}