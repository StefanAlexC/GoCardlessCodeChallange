import java.util.HashSet;
import java.util.Set;

public class WebpageManager {

  /**
   * A set that holds all the links that have already been seen
   */
  private Set<String> linkDatabase;
  /**
   * A set that hold all the webpages crawled so far
   */
  private Set<Webpage> webpageDatabase;
  /**
   * The webpageScrapper that holds the homeUrl
   */
  private WebpageScraper webpageScraper;

  /**
   * Constructor for WebpageManager
   * @param webpageScraper The webpageScraper that hold the homeUrl
   */
  public WebpageManager(WebpageScraper webpageScraper) {
    this.linkDatabase = new HashSet<>();
    this.webpageScraper = webpageScraper;
    this.webpageDatabase = new HashSet<>();
  }

  /**
   * Chosen algorithm for web crawling
   * @param currentPage The page that is currently being analysed
   */
  public void dfs(Webpage currentPage) {
    if (currentPage != null) {
      //System.out.println(currentPage.toString());
      linkDatabase.add(currentPage.getUrl());
      webpageDatabase.add(currentPage);

      for (String link : currentPage.getLinks()) {
        if (!linkDatabase.contains(link)) {
          dfs(webpageScraper.fetchWebpage(link));
        }
      }
    }
  }

  /**
   * Getter method for the webpageDatabse field
   * @return The webpageDatabase
   */
  public Set<Webpage> getWebpageDatabase() {
    return webpageDatabase;
  }
}
