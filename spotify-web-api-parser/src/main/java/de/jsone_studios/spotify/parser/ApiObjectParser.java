package de.jsone_studios.spotify.parser;

import de.jsone_studios.spotify.parser.model.SpotifyObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
class ApiObjectParser {

    private String baseUrl;

    List<SpotifyObject> parseSpotifyObjects(List<Elements> sections, String baseUrl) throws ApiParseException{
        this.baseUrl = baseUrl;
        var objects = new ArrayList<SpotifyObject>();
        var objectSection = sections.get(sections.size() - 1);//Last section is the Objects Index
        for (var element : objectSection.select("h3")) {
            var object = parseSpotifyObject(element);
            if (objects.contains(object)) {
                throw new ApiParseException("Object is defined twice: " + object.getName());
            } else {
                objects.add(object);
            }
        }
        //Apply fixes and add missing objects
        ApiObjectFixes.fixApiObjects(objects);
        for (var object : ApiObjectFixes.getMissingObjects()) {
            if (objects.contains(object)) {
                log.warn("Object {} is no longer missing", object.getName());
            }
            else {
                objects.add(object);
            }
        }
        objects.sort(Comparator.comparing(SpotifyObject::getName));
        return objects;
    }

    private SpotifyObject parseSpotifyObject(Element objectHeader) throws ApiParseException {
        var id = objectHeader.attributes().get("id");
        var link = this.baseUrl + "/#" + id;
        var objectName = objectHeader.text();
        var spotifyObject = new SpotifyObject(objectName, id, link);
        for (var prop : objectHeader.nextElementSibling().select("tbody > tr")) {
            var name = prop.selectFirst("code").text();
            var descriptionElement = prop.selectFirst("small");
            var description = Html2Markdown.convert(descriptionElement);
            var type = prop.child(1).text();
            var property = new SpotifyObject.Property(name, type, description);
            if (spotifyObject.getProperties().contains(property)) {
                throw new ApiParseException(String.format("Property %s of Object %s is defined twice", name, objectName));
            }
            spotifyObject.addProperty(property);
        }
        return spotifyObject;
    }
}
