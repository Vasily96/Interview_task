package test.task.IOPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import test.task.log.LogClass;
import test.task.logic.PredicateClass;
import test.task.menu.MenuClass;

public class ReaderClass {

  private static final String DELIMITER = " --- ";

  public static List<LogClass> readFromDirectoryAndFilter(MenuClass consoleMenu) {

    Path pathDir = Paths.get("logs/");
    Predicate<LogClass> logPredicate = PredicateClass.createPredicate(consoleMenu);
    List<LogClass> listLogsAfterFilter = new CopyOnWriteArrayList<>(new ArrayList<>());
    ExecutorService service = Executors.newFixedThreadPool(consoleMenu.getCountThreads());

    try (Stream<Path> pathStream = Files.walk(pathDir)) {
      pathStream.filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().endsWith(".txt"))
                .forEach(pathFile -> service.execute(() -> {
                      try (Stream<String> lineStream = Files.lines(pathFile)) {
                        listLogsAfterFilter.addAll(lineStream.map(
                            line -> new LogClass(line.split(DELIMITER)[0], line.split(DELIMITER)[1], line.split(DELIMITER)[2]))
                            .filter(logPredicate)
                            .collect(Collectors.toList()));
                      } catch (IOException e) {
                        e.printStackTrace();
                      }
                    })
                );
    } catch (IOException e) {
      e.printStackTrace();
    }
    service.shutdown();
    try {
      service.awaitTermination(5, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return listLogsAfterFilter;
  }
}
