package de.ddb.pdc.web;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;

public class SearchControllerTest {

  @Test
  public void search() {
    MetaFetcher fetcher = Mockito.mock(MetaFetcher.class);
    DDBItem[] result = new DDBItem[0];
    Mockito.when(fetcher.searchForItems("foo", 10)).thenReturn(result);
    SearchController controller = new SearchController(fetcher);
    Assert.assertSame(result, controller.search("foo", 10));
  }
}