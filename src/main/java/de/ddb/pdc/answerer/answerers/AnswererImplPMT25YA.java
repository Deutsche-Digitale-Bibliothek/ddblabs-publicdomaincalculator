package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_25_YEARS_AGO question.
 */
public class AnswererImplPMT25YA implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    if (currentYear - metaData.getYearPublished() > 25) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}