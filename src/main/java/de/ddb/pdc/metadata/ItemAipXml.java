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

  public ItemAipXml(DOMSource domSource, XPathOperations xpath) {
    this.domSource = domSource;
    this.xpath = xpath;
  }

  /**
   * Returns the published year.
   */
  public int getPublishedYear() {
    String date = xpath.evaluateAsString("//dcterms:issued", domSource);
    if (date != null || !date.equals("")) {
      return Integer.parseInt(MetadataUtils.useRegex(date,"\\d{4}" ));
    } else {
      return -1;
    }
  }

  /**
   * Returns name of the institution.
   */
  public String getInstitution() {
    return xpath.evaluateAsString("//edm:dataProvider[1]", domSource);
  }

  /**
   * Returns a list with the authors dnb urls.
   */
  public List<String> getAuthorUrls() {
    List<String> authorUrls = new ArrayList<String>();
    String xpathFacetRole =
        "//ctx:facet[@name='affiliate_fct_role_normdata']/ctx:value";
    String xpathFacet =
        "//ctx:facet[@name='affiliate_fct_normdata']/ctx:value";
    List<Node> nodes = xpath.evaluateAsNodeList(xpathFacetRole, domSource);
    for (Node node : nodes) {
      String value = node.getFirstChild().getTextContent();
      if (value.endsWith("_1_affiliate_fct_subject")
          || value.endsWith("_1_affiliate_fct_involved")) {
        authorUrls.add(value.split("_")[0]);
      }
    }
    if (authorUrls.size() == 0) {
      nodes = xpath.evaluateAsNodeList(xpathFacet, domSource);
      for (Node node : nodes) {
        authorUrls.add(node.getFirstChild().getTextContent());
      }
    }
    return  authorUrls;
  }


}
