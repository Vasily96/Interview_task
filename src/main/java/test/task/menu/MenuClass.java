package test.task.menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class MenuClass {

  private Scanner in = new Scanner(System.in);

  private String userName;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private String wordsContain;
  private String groupByUserName;
  private ChronoUnit groupByTimeUnit;
  private int countThreads = 1;
  private String fileName = "default.txt";


  private boolean hasReadUserName() {
    System.out.println("Hello! Please, Choose filter parameters: ");
    String filterUserName = "";
    while (!(filterUserName.toUpperCase().equals("Y") || filterUserName.toUpperCase()
        .equals("N"))) {
      System.out.println("User name? (Y/N):");
      filterUserName = in.nextLine();
    }
    if (filterUserName.toUpperCase().equals("Y")) {
      System.out.println("Please, enter user name:");
      userName = in.nextLine();
    }

    return filterUserName.toUpperCase().equals("Y");
  }

  private static boolean isValidDateTime(String dateTime) {
    try {
      LocalDateTime.parse(dateTime);
    } catch (DateTimeParseException e) {
      System.out.println("Incorrect date format. Try again!");
      return false;
    }
    return true;
  }

  private static boolean isValidTimeUnit(String timeUnit) {
    try {
      ChronoUnit.valueOf(timeUnit.toUpperCase());
    } catch (IllegalArgumentException e) {
      System.out.println("Incorrect unit time format. Try again!");
      return false;
    }

    return true;
  }

  private static boolean isCountThread(String strCountThreads) {
    try {
      Integer.parseInt(strCountThreads);
      if (Integer.parseInt(strCountThreads) <= 0) {
        System.out.println("Incorrect Count of threads. Try again!");
        return false;
      }
    } catch (NumberFormatException e) {
      System.out.println("Incorrect Count of threads. Try again!");
      return false;
    }
    return true;
  }

  private boolean hasReadStartDateTime() {
    String filterStartTimePeriod = "";
    while (!(filterStartTimePeriod.toUpperCase().equals("Y") || filterStartTimePeriod.toUpperCase()
        .equals("N"))) {
      System.out.println("Start time period? (Y/N):");
      filterStartTimePeriod = in.nextLine();
    }

    if (filterStartTimePeriod.toUpperCase().equals("Y")) {
      String startTime;
      do {
        System.out.println("Please, enter start time period(yyyy-mm-ddThh:mm:ss):");
        startTime = in.nextLine();
      } while (!isValidDateTime(startTime));

      startDateTime = LocalDateTime.parse(startTime);
    }

    return filterStartTimePeriod.toUpperCase().equals("Y");
  }

  private boolean hasReadEndDateTime() {
    String filterEndTimePeriod = "";
    while (!(filterEndTimePeriod.toUpperCase().equals("Y") || filterEndTimePeriod.toUpperCase()
        .equals("N"))) {
      System.out.println("End time period? (Y/N):");
      filterEndTimePeriod = in.nextLine();
    }

    if (filterEndTimePeriod.toUpperCase().equals("Y")) {
      String endTime;
      do {
        System.out.println("Please, enter end time period(yyyy-mm-ddThh:mm:ss):");
        endTime = in.nextLine();
      } while (!isValidDateTime(endTime));

      endDateTime = LocalDateTime.parse(endTime);
    }

    return filterEndTimePeriod.toUpperCase().equals("Y");
  }

  private boolean hasReadWordsContain() {
    String filterContainWord = "";
    while (!(filterContainWord.toUpperCase().equals("Y") || filterContainWord.toUpperCase()
        .equals("N"))) {
      System.out.println("Does the message contain words? (Y/N):");
      filterContainWord = in.nextLine();
    }

    if (filterContainWord.toUpperCase().equals("Y")) {
      System.out.println("Please, enter the message:");
      wordsContain = in.nextLine();
    }

    return filterContainWord.toUpperCase().equals("Y");
  }

  private boolean hasReadGroupingByUserName() {
    String groupingByUserName = "";
    while (!(groupingByUserName.toUpperCase().equals("Y") || groupingByUserName.toUpperCase()
        .equals("N"))) {
      System.out.println("Group by userName? (Y/N)");
      groupingByUserName = in.nextLine();
    }

    groupByUserName = groupingByUserName;

    return groupingByUserName.toUpperCase().equals("Y");
  }

  private boolean hasReadGroupingByTimeUnit() {
    String groupingByTimeUnit = "";
    while (!(groupingByTimeUnit.toUpperCase().equals("Y") || groupingByTimeUnit.toUpperCase()
        .equals("N"))) {
      System.out.println("Group by time unit? (Y/N):");
      groupingByTimeUnit = in.nextLine();
    }

    if (groupingByTimeUnit.toUpperCase().equals("Y")) {
      String timeUnit;
      do {
        System.out
            .println(" Time Unit (SECONDS/MINUTES/HOURS/DAYS/MONTHS/YEARS ... and so on):");
        timeUnit = in.nextLine();
      } while (!isValidTimeUnit(timeUnit));

      groupByTimeUnit = ChronoUnit.valueOf(timeUnit.toUpperCase());
    }

    return groupingByTimeUnit.toUpperCase().equals("Y");
  }

  private void readForPredicate() {
    while (!(hasReadUserName() |
        hasReadStartDateTime() |
        hasReadEndDateTime() |
        hasReadWordsContain())
    ) {
      System.out.println("Please set at least one parameter ");
    }
    System.out.println(
        "Filter Parameters: " + userName + " " + startDateTime + " " + endDateTime + " "
            + wordsContain);
  }

  private void readForGrouping() {
    while (!(hasReadGroupingByUserName() |
        hasReadGroupingByTimeUnit())
    ) {
      System.out.println("Please set at least one parameter ");
    }
    System.out.println("Group Parameters: " + groupByUserName + " " + groupByTimeUnit);

  }

  private void readCountThreads() {
    String useThreads = "";
    while (!(useThreads.toUpperCase().equals("Y") || useThreads.toUpperCase().equals("N"))) {
      System.out.println("Use threads to process files ? (Y/N):");
      useThreads = in.nextLine();
    }

    if (useThreads.toUpperCase().equals("Y")) {
      countThreads = 0;
      String strCountThreads;
      do {
        System.out.println("Count of threads used to process files:");
        strCountThreads = in.nextLine();
      } while (!isCountThread(strCountThreads));
      countThreads = Integer.parseInt(strCountThreads);
    }
  }

  private void readFileName() {
    System.out.print("Filename to output file:");
    fileName = in.next();
    fileName = fileName + ".txt";
  }

  public void startMenu() {
    readForPredicate();
    readForGrouping();
    readCountThreads();
    readFileName();
  }


  public String getUserName() {
    return userName;
  }

  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public String getWordsContain() {
    return wordsContain;
  }

  public int getCountThreads() {
    return countThreads;
  }

  public String getFileName() {
    return fileName;
  }

  public String getGroupByUserName() {
    return groupByUserName;
  }

  public ChronoUnit getGroupByTimeUnit() {
    return groupByTimeUnit;
  }
}
