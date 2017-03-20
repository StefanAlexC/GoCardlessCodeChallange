import java.util.Set;

public class Webpage {

  /**
   * Holds the url of a Webpage
   */
  private final String url;
  /**
   * A set containing all the assets of the webpage
   */
  private final Set<String> assets;
  /**
   * A set containing all the links that are referenced by the webpage
   */
  private transient final Set<String> links;

  /**
   * The default constructor for a Webpage object
   * @param url The url of the webpage
   * @param assets The set containing all the assets of the webpage
   * @param links The set containing all the links that are referenced by the
   * webpage
   */
  public Webpage(String url, Set<String> assets, Set<String> links) {
    this.assets = assets;
    this.links = links;
    this.url = url;
  }

  /**
   * Getter method for the links field
   * @return The links of the webpage object
   */
  public Set<String> getLinks() {
    return links;
  }

  /**
   * Getter method for the url field
   * @return The url of the webpage object
   */
  public String getUrl() {
    return url;
  }

  /**
   * toString method
   * @return the string representation of Webpage objects
   */
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
