package de.sonallux.spotify.parser;

import de.sonallux.spotify.core.model.SpotifyWebApiObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
class ApiObjectParser {

    private String baseUrl;

    SortedMap<String, SpotifyWebApiObject> parseSpotifyObjects(List<Elements> sections, String baseUrl) throws ApiParseException{
        this.baseUrl = baseUrl;
        var objects = new TreeMap<String, SpotifyWebApiObject>();
        var objectSection = sections.get(sections.size() - 1);//Last section is the Objects Index
        for (var element : objectSection.select("h3")) {
            var object = parseSpotifyObject(element);
            if (objects.containsKey(object.getName())) {
                throw new ApiParseException("Object is defined twice: " + object.getName());
            } else {
                objects.put(object.getName(), object);
            }
        }

        return objects;
    }

    private SpotifyWebApiObject parseSpotifyObject(Element objectHeader) throws ApiParseException {
        var id = objectHeader.attributes().get("id");
        var link = this.baseUrl + "/#" + id;
        var objectName = objectHeader.text();
        var spotifyObject = new SpotifyWebApiObject(objectName, link);
        for (var prop : objectHeader.nextElementSibling().select("tbody > tr")) {
            /*
            Properties have the following structure:
            <tr>
              <td>
                <code>{name}</code>
                <br>
                {description}
              </td>
              <td>{type}</td>
            </tr>
             */
            var nameElement = prop.selectFirst("code");
            var name = nameElement.text();
            var brElement = nameElement.nextElementSibling();
            var descriptionElements = brElement.nextElementSiblings();
            var description = Html2Markdown.convert(descriptionElements);
            var type = prop.child(1).text();
            var property = new SpotifyWebApiObject.Property(name, type, description);
            if (spotifyObject.getProperties().contains(property)) {
                throw new ApiParseException(String.format("Property %s of Object %s is defined twice", name, objectName));
            }
            spotifyObject.addProperty(property);
        }
        return spotifyObject;
    }
}
