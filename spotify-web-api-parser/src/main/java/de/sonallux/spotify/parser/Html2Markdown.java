package de.sonallux.spotify.parser;

import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.html2md.converter.HtmlLinkResolver;
import com.vladsch.flexmark.html2md.converter.HtmlLinkResolverFactory;
import com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Node;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Html2Markdown {

    private static final FlexmarkHtmlConverter CONVERTER;

    static {
        var options = new MutableDataSet();
        options.set(FlexmarkHtmlConverter.UNORDERED_LIST_DELIMITER, '-');
        CONVERTER = FlexmarkHtmlConverter.builder(options)
                .linkResolverFactory(new HostHtmlLinkResolverFactory())
                .build();
    }

    static String convert(Node node) {
        return CONVERTER.convert(node).trim();
    }

    static String convert(List<? extends Node> nodes) {
        return nodes.stream()
                .map(CONVERTER::convert)
                .collect(Collectors.joining("\n"))
                .trim();
    }

    private static class HostHtmlLinkResolver implements HtmlLinkResolver {
        @Override
        public ResolvedLink resolveLink(Node node, HtmlNodeConverterContext context, ResolvedLink link) {
            if (link.getUrl().startsWith("/")) {
                var host = getHost(node.baseUri());
                return link.withUrl(host + link.getUrl());
            }
            return link;
        }

        private String getHost(String baseUri) {
            try {
                var url = new URL(baseUri);
                return url.getProtocol() + "://" + url.getHost();
            }
            catch (MalformedURLException e) {
                return "";
            }
        }
    }

    private static class HostHtmlLinkResolverFactory implements HtmlLinkResolverFactory {
        @Override
        public @Nullable Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override
        public @Nullable Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override
        public boolean affectsGlobalScope() {
            return false;
        }

        @Override
        public HtmlLinkResolver apply(HtmlNodeConverterContext context) {
            return new HostHtmlLinkResolver();
        }
    }
}
