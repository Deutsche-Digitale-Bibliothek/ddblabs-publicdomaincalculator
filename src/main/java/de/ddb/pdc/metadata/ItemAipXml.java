package de.ddb.pdc.metadata;

import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Node;

import javax.xml.transform.dom.DOMSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to operate with xpath the DDB XML AIP request.
 */
public class ItemAipXml {

  private DOMSource domSource;
  private XPathOperations xpath;

  /**
   * Create new ItemAipXml Object for every DomSource.
   * The class is need to operate with xpath on the dom.
   *
   * @param domSource xml source in a dom
   * @param xpath use for xpath operations
   */
  public ItemAipXml(DOMSource domSource, XPathOperations xpath) {
    this.domSource = domSource;
    this.xpath = xpath;
  }

  /**
   * Returns the title of the item.
   */
  public String getTitle() {
    String title = xpath.evaluateAsString("//ctx:preview/ctx:title", domSource);
    if (title.equals("")) {
      return null;
    }
    return title;
  }

  /**
   * Returns the subtitle of the item.
   */
  public String getSubtitle() {
    String subtitle = xpath.evaluateAsString("//ctx:preview/ctx:subtitle"
        , domSource);
    if (subtitle.equals("")) {
      return null;
    }
    return subtitle;
  }

  /**
   * Returns url to thumbnail of the item.
   */
  public String getThumbnail() {
    String thumbnail = xpath.evaluateAsString("//ctx:preview/ctx:thumbnail/@href"
        , domSource);
    if (thumbnail.equals("")) {
      return null;
    }
    return thumbnail;
  }

  /**
   * Returns the published year.
   */
  public int getPublishedYear() {
    String date = xpath.evaluateAsString("//dcterms:issued", domSource);
    try {
      return Integer.parseInt(MetadataUtils.useRegex(date,"\\d{4}" ));
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * Returns name of the institution.
   */
  public String getInstitution() {
    String institution = xpath.evaluateAsString("//edm:dataProvider[1]"
        , domSource);
    if (institution.equals("")) {
      return null;
    }
    return institution;
  }

  /**
   * Returns number of creative commons license 1.0, 2.0, 2.5 , 3.0 or 4.0
   */
  public int getCCLicense() {
    String license = xpath.evaluateAsString(
        "//ore:Aggregation/edm:rights/@ns3:resource", domSource);
    if (license.isEmpty() || license.equals("")) {
      return 0;
    } else {
      String[] temp = license.split("/");
      temp = temp[temp.length - 1].split("\\.");
      try {
        return Integer.parseInt(temp[0]) * 10 + Integer.parseInt(temp[1]);
      } catch (NumberFormatException e) {
        return 0;
      }
    }
  }

  /**
   * Returns a list with the authors dnb urls.
   */
  public List<String> getAuthorUrls() {
    List<String> authorUrls = new ArrayList<String>();
    String xpathFacetRole =
        "//ctx:facet[@name='affiliate_fct_role_normdata']/ctx:value";
    List<Node> nodes = xpath.evaluateAsNodeList(xpathFacetRole, domSource);
    for (Node node : nodes) {
      String value = node.getFirstChild().getTextContent();
      if (value.endsWith("_1_affiliate_fct_involved")) {
        authorUrls.add(value.split("_")[0]);
      }
    }
    return  authorUrls;
  }
}
