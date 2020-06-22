package test.task;

import java.util.List;
import test.task.IOPackage.ReaderClass;
import test.task.IOPackage.WriterClass;
import test.task.log.LogClass;
import test.task.logic.GroupClass;
import test.task.menu.MenuClass;

public class Main {

  public static void main(String[] args) {

    MenuClass consoleMenu = new MenuClass();
    consoleMenu.startMenu();
    List<LogClass> listLogsAfterFilter = ReaderClass.readFromDirectoryAndFilter(consoleMenu);
    List<String> listLinesAfterGrouping = GroupClass.groupBy(listLogsAfterFilter, consoleMenu);
    WriterClass.writeInFile(consoleMenu.getFileName(), listLogsAfterFilter, listLinesAfterGrouping);

  }
}

