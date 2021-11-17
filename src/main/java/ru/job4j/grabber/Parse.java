package ru.job4j.grabber;

import org.jsoup.nodes.Element;

import java.util.List;

public interface Parse {
    List<Post> list(String link);

    Post detail(Element td);
}
