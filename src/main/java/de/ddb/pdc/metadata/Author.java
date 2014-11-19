package de.ddb.pdc.metadata;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Stores information about an author of an item.
 */
public class Author {

  private String dnbId;
  private String name;
  private Calendar yearOfBirth = new GregorianCalendar();
  private String placeOfBirth;
  private Calendar yearOfDeath = new GregorianCalendar();
  private String placeOfDeath;
  private String nationality;

  /**
   * Creates an Author.
   *
   * @param dnbId the ID assigned to the author by the DNB
   */
  public Author(String dnbId) {
    this.dnbId = dnbId;
  }

  /**
   * Returns the ID assigned to the author by the Deutsche Nationalbibliothek
   * (http://d-nb.info).
   */
  public String getDnbId() {
    return dnbId;
  }

  /**
   * Returns the author's full name for display.
   */
  public String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the year at which the author was born. If unknown, null is
   * returned.
   */
  public Calendar getYearOfBirth() {
    return yearOfBirth;
  }
  
  void setYearOfBirth(int yearOfBirth) {
    if (yearOfBirth == -1) {
      this.yearOfBirth = null;
    } else {
      this.yearOfBirth = new GregorianCalendar();
      this.yearOfBirth.set(Calendar.YEAR, yearOfBirth);
    }
  }

  /**
   * Returns the year at which the author died. If unknown, null is returned.
   */
  public Calendar getYearOfDeath() {
    return yearOfDeath;
  }
  
  void setYearOfDeath(int yearOfDeath) {
    if (yearOfDeath == -1) {
      this.yearOfDeath = null;
    } else {
      this.yearOfDeath = new GregorianCalendar();
      this.yearOfDeath.set(Calendar.YEAR, yearOfDeath);
    }
  }

  /**
   * Returns the name of the state at which the author was born. If
   * unknown, null is returned.
   */
  public String getNationality() {
    return nationality;
  }
  
  void setNationality(String nationality) {
    this.nationality = nationality;
  }

  /**
   * Returns the name of the city or state where the author was born.
   * If unknown, null is returned.
   */
  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
}
