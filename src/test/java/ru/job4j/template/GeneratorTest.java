package ru.job4j.template;

import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void whenProduceRightStringFromTemplate() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        String example = "I am a Ivan, Who are you?";
        Map<String, String> args = Map.of("Ivan", "you");
        assertThat(generator.produce(template, args), is(example));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTemplateHasWrongKeys() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${guy}?";
        Map<String, String> args = Map.of("Ivan", "you");
        generator.produce(template, args);
    }

    @Test(expected = NumberFormatException.class)
    public void whenTemplateHasWrongKeys() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}, mr.${mr}?";
        Map<String, String> args = Map.of("Ivan", "you");
        generator.produce(template, args);
    }
}