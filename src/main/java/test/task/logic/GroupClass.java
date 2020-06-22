package test.task.logic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import test.task.log.LogClass;
import test.task.menu.MenuClass;

public class GroupClass {

  public static List<String> groupBy(List<LogClass> listLogClass, MenuClass reader) {

    if (reader.getGroupByUserName().toUpperCase().equals("Y")
        && reader.getGroupByTimeUnit() != null) {
      return groupByTimeUnitAndUserName(listLogClass, reader.getGroupByTimeUnit());
    } else if (reader.getGroupByUserName().toUpperCase().equals("Y")) {
      return groupByUserName(listLogClass);
    } else if (reader.getGroupByTimeUnit() != null) {
      return groupByTimeUnit(listLogClass, reader.getGroupByTimeUnit());
    }

    return null;
  }

  private static List<String> groupByUserName(List<LogClass> listLogs) {
    List<String> lines = new ArrayList<>();
    Map<String, Long> logByUserName = listLogs.stream().collect(
        Collectors.groupingBy(LogClass::getUserName, Collectors.counting()));

    for (Map.Entry<String, Long> item : logByUserName.entrySet()) {
      lines.add(item.getKey() + " - " + item.getValue());
    }
    return lines;
  }

  private static List<String> groupByTimeUnit(List<LogClass> listLogs, ChronoUnit timeUnit) {
    List<String> lines = new ArrayList<>();
    Map<LocalDateTime, Long> logByDate = listLogs.stream()
        .collect(Collectors.groupingBy(e -> LocalDateTime.from(e.getDateTime().truncatedTo(timeUnit)), Collectors.counting())
        );

    for (Map.Entry<LocalDateTime, Long> item : logByDate.entrySet()) {
      lines.add(item.getKey() + " - " + item.getValue());
    }
    return lines;
  }

  private static List<String> groupByTimeUnitAndUserName(List<LogClass> listLogs,
      ChronoUnit timeUnit) {
    List<String> lines = new ArrayList<>();
    Map<LocalDateTime, List<LogClass>> logByDateAndUser = listLogs.stream()
        .collect(Collectors.groupingBy(e ->
            LocalDateTime.from(e.getDateTime().truncatedTo(timeUnit)))
        );

    for (Map.Entry<LocalDateTime, List<LogClass>> item : logByDateAndUser.entrySet()) {

      lines.add(item.getKey().toString());
      Map<String, Long> logByUserName = item.getValue().stream().collect(
          Collectors.groupingBy(LogClass::getUserName, Collectors.counting()));

      for (Map.Entry<String, Long> item1 : logByUserName.entrySet()) {
        lines.add(item1.getKey() + " - " + item1.getValue());
      }
      lines.add("\n");
    }
    return lines;
  }

}
