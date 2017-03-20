import java.util.HashSet;
import java.util.Set;

public class WebpageManager {

  private Set<String> linkDatabase;
  private Set<Webpage> webpageDatabase;
  private WebpageScraper webpageScraper;

  public WebpageManager(WebpageScraper webpageScraper) {
    this.linkDatabase = new HashSet<>();
    this.webpageScraper = webpageScraper;
    this.webpageDatabase = new HashSet<>();
  }

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

  public Set<Webpage> getWebpageDatabase() {
    return webpageDatabase;
  }
}
