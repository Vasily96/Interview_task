package test.task.logic;

import java.util.function.Predicate;
import test.task.log.LogClass;
import test.task.menu.MenuClass;

public class PredicateClass {

  public static Predicate<LogClass> createPredicate(MenuClass reader) {
    Predicate<LogClass> predicate = log -> true;
    if (reader.getUserName() != null) {
      predicate = predicate.and(log -> log.getUserName().equals(reader.getUserName()));
    }
    if (reader.getStartDateTime() != null) {
      predicate = predicate.and(log -> log.getDateTime().isAfter(reader.getStartDateTime()));
    }
    if (reader.getEndDateTime() != null) {
      predicate = predicate.and(log -> log.getDateTime().isBefore(reader.getEndDateTime()));
    }
    if (reader.getWordsContain() != null) {
      predicate = predicate.and(log -> log.getMessage().contains(reader.getWordsContain()));
    }

    return predicate;
  }

}
