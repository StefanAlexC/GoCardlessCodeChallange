import java.util.HashSet;
import java.util.Set;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebpageScraper {

  String homeUrl;

  public WebpageScraper(String homeUrl) {
    this.homeUrl = homeUrl;
  }

  public String getHomeUrl() {
    return homeUrl;
  }

  public Webpage fetchWebpage(String searchedUrl) {

    try {
      Document webpage = Jsoup.connect(searchedUrl).get();

      return scrapeWebpage(webpage, searchedUrl);
    } catch (java.io.IOException e) {
      return null;
    }

  }

  public Webpage scrapeWebpage(Document document, String searchedUrl) {

    return new Webpage(searchedUrl, getMedia(document), getLinks(document));

  }

  private Set<String> getMedia(Document document) {
    Elements media = document.select("[src]");
    Set<String> assets = new HashSet<>();

    for (Element src : media) {
      assets.add(src.attr("abs:src"));
    }

    Elements imports = document.select("link[href]");

    for (Element link : imports) {
      assets.add(link.attr("abs:href"));
    }

    return assets;
  }

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
}

