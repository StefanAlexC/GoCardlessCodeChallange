import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.BooleanAttribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebpageScraper {

  /**
   * The homeUrl
   */
  String homeUrl;

  /**
   * The default constructor for the WebpageScraper object
   * @param homeUrl the given starting url from which to crawl from
   */
  public WebpageScraper(String homeUrl) {
    this.homeUrl = homeUrl;
  }

  /**
   * Fetches webpage
   * @param searchedUrl The desired url to be fetched
   * @return A webpage object containing all the information of the page
   */
  public Webpage fetchWebpage(String searchedUrl) {

    try {
      Document webpage = Jsoup.connect(searchedUrl).get();

      return scrapeWebpage(webpage, searchedUrl);
    } catch (java.io.IOException e) {
      return null;
    }

  }

  /**
   * Scrapes the content of a webpage
   * @param document The fetched webpage
   * @param searchedUrl The url that needs to be searched
   * @return A webpage object containg all the information of the page
   */
  public Webpage scrapeWebpage(Document document, String searchedUrl) {

    return new Webpage(searchedUrl, getMedia(document), getLinks(document));

  }

  /**
   * Scrapes all the assets of the page
   * @param document The fetched webpage
   * @return A set containing all the assets
   */
  private Set<String> getMedia(Document document) {
    Elements media = document.select("[src]");
    Set<String> assets = new HashSet<>();
    String url;

    for (Element src : media) {
      url = cleanUrl(src.attr("abs:src"));
      if (validateAsset(url))
        assets.add(url);
    }

    Elements imports = document.select("link[href]");

    for (Element link : imports) {
      url = cleanUrl(link.attr("abs:href"));
      if (validateAsset(url))
        assets.add(url);
    }

    return assets;
  }

  /**
   * Scrapes all the links referenced by the page
   * @param document The fetched webpage
   * @return A set containing all the links referenced by the page
   */
  private Set<String> getLinks(Document document) {
    Elements links = document.select("a[href]");
    Set<String> referencedLinks = new HashSet<>();

    for (Element link : links) {
      String url = link.attr("abs:href");
      if (url.contains(homeUrl)) {
        referencedLinks.add(cleanUrl(url));
      }
    }

    return referencedLinks;
  }

  /**
   * Cleans the url removing everything after # or ? symbols
   * Can be adapted further for clear urls
   * @param url The dirty url
   * @return The clean url
   */
  private String cleanUrl(String url) {
    int index;

    index = url.indexOf("#");
    if (index != -1) {
      url = url.substring(0, index);
    }

    index = url.indexOf("?");
    if (index != -1) {
      url = url.substring(0, index);
    }

    return url;
  }

  /**
   * Validates only the assets that have the specified extensions.
   * For this exercise the extensions used are .jpg .png .gif .js .icon .css,
   * but more can be added
   * @param url The url that need to be validates
   * @return Boolean value that represents whether or not the url is valid
   */
  private static Boolean validateAsset(String url) {
    return url.contains(".jpg") || url.contains(".png") || url.contains(""
        + ".gif") || url.contains(".js") || url.contains(".ico") || url
        .contains(".css");
  }
}

