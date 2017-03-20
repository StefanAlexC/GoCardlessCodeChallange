import java.util.Set;

public class Webpage {

  private final String url;
  private final Set<String> assets;
  private transient final Set<String> links;

  public Webpage(String url, Set<String> assets, Set<String> links) {
    this.assets = assets;
    this.links = links;
    this.url = url;
  }

  public Set<String> getAssets() {
    return assets;
  }

  public Set<String> getLinks() {
    return links;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    String toString = new String("");
    toString += "{\n" + url + "\n" + "  [" + "\n";
    for (String asset : assets) {
      toString += "   " + asset + "\n";
    }
    toString += "   ]\n}\n\n";

    return toString;
  }
}
