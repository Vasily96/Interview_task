package test.task.log;

import java.time.LocalDateTime;

public class LogClass {

  private LocalDateTime dateTime;
  private String userName;
  private String message;

  public LogClass(String stringDateTime, String userName, String message) {
    this.dateTime = LocalDateTime.parse(stringDateTime);
    this.userName = userName;
    this.message = message;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public String getUserName() {
    return userName;
  }

  public String getMessage() {
    return message;
  }


  @Override
  public String toString() {
    return "Log{" +
        "dateTime=" + dateTime +
        ", userName='" + userName + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
