package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AnswererImplAA;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AnswererImplAATest {

  @Test
  public void authorAnonymousTest() {
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, 0, null);

    Answerer answerer = new AnswererImplAA();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

  @Test
  public void authorNotAnonymousTest() {
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, "Goethe", 0, 0, null);

    Answerer answerer = new AnswererImplAA();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

}