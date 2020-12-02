package de.sonallux.spotify.parser;

import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

final class ParseUtils {

    private ParseUtils() {
    }

    static List<Elements> splitAt(Elements elements, String selector) {
        var dividers = elements.select(selector);
        if (dividers.size() == 0) {
            return List.of();
        }

        var result = new ArrayList<Elements>();


        var startIndex = elements.indexOf(dividers.get(0));
        for (int i = 1; i < dividers.size(); i++) {
            var endIndex = elements.indexOf(dividers.get(i));
            result.add(new Elements(elements.subList(startIndex, endIndex)));
            startIndex = endIndex;
        }
        result.add(new Elements(elements.subList(startIndex, elements.size())));

        return result;
    }
}
