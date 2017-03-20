import com.google.gson.Gson;

public class Main {

  public static void main(String[] args) {
    String homeUrl = "https://www.doc.ic.ac.uk/project/2016/163/g1616303/";
    WebpageScraper webpageScraper = new WebpageScraper(homeUrl);
    WebpageManager webpageManager = new WebpageManager(webpageScraper);
    Gson gson = new Gson();

    webpageManager.dfs(webpageScraper.fetchWebpage(homeUrl));

    System.out.println(gson.toJson(webpageManager.getWebpageDatabase()));
  }

}
