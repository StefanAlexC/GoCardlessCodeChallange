import com.google.gson.Gson;

public class Main {

  /**
   * Main method of the program
   * @param args the link that you wish to scrape
   */
  public static void main(String[] args) {
    if (args.length == 1) {
      String homeUrl = args[0];
      WebpageScraper webpageScraper = new WebpageScraper(homeUrl);
      WebpageManager webpageManager = new WebpageManager(webpageScraper);
      Gson gson = new Gson();

      webpageManager.dfs(webpageScraper.fetchWebpage(homeUrl));

      System.out.println(gson.toJson(webpageManager.getWebpageDatabase()));

    } else {
      System.out.println("Please insert the desired url as the only argument "
          + "of the program!");
    }
  }
}
