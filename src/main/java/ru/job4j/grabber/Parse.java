package ru.job4j.grabber;

import javax.swing.text.Element;
import java.util.List;

public interface Parse {
    List<Post> list(String link);

    Post detail(String link);
}
