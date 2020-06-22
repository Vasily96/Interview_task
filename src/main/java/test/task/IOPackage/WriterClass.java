package test.task.IOPackage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import test.task.log.LogClass;

public class WriterClass {

  public static void writeInFile(String fileName, List<LogClass> logsAfterFilter,
      List<String> dataGroupingToFile) {

    logsAfterFilter.stream()
                   .map(log -> dataGroupingToFile.add(log.toString()))
                   .collect(Collectors.toList());

    try {
      Path path = Files.createFile(Paths.get(fileName));
      Files.write(path, dataGroupingToFile, StandardCharsets.UTF_8);
      System.out.println(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
