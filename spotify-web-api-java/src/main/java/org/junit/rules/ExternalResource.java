package org.junit.rules;

/**
 * Fake ExternalResource class from junit4 to get rid of the direct
 * junit4 dependency of MockWebServer.
 * <p>
 * This class can be removed if MockWebServer has removed its dependency to junit4. Also see the two issues:
 * <ul>
 *     <li><a href="https://github.com/square/okhttp/issues/4667">https://github.com/square/okhttp/issues/4667</a></li>
 *     <li><a href="https://github.com/square/okhttp/issues/2635">https://github.com/square/okhttp/issues/2635</a></li>
 * </ul>
 */
public class ExternalResource {
}
