import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        installGame();
//        saveGame();

        loadGame();
    }

    private static void loadGame() {
        List<GameProgress> loadedGameProgresses = GameLoader.loadGame("C://Work//Netology//Games");
        for(GameProgress gameProgress: loadedGameProgresses) {
            System.out.println(gameProgress);
        }
    }

    private static void saveGame() {
        GameProgress gameProgress1 = new GameProgress(100, 2, 3, 12.0);
        GameProgress gameProgress2 = new GameProgress(90, 2, 1, 1.0);
        GameProgress gameProgress3 = new GameProgress(75, 2, 6, 163.0);
        List<GameProgress> gameProgresses = new ArrayList<>();
        gameProgresses.add(gameProgress1);
        gameProgresses.add(gameProgress2);
        gameProgresses.add(gameProgress3);

        GameSaver.saveGame("C://Work//Netology//Games", gameProgresses);
    }

    private static void installGame() {
        Installer installer = new Installer("C://Work//Netology//Games");
        StringBuilder str = new StringBuilder();
        str.append("Installation result\n");
        str.append(installer.createNewFolder("", "src"));
        str.append(installer.createNewFolder("", "res"));
        str.append(installer.createNewFolder("", "savegames"));
        str.append(installer.createNewFolder("", "temp"));
        str.append(installer.createNewFolder("src//", "main"));
        str.append(installer.createNewFolder("src//", "test"));
        str.append(installer.createNewFile("src//main", "Main.java"));
        str.append(installer.createNewFile("src//main", "Utils.java"));
        str.append(installer.createNewFolder("res//", "drawables"));
        str.append(installer.createNewFolder("res//", "vectors"));
        str.append(installer.createNewFolder("res//", "icons"));
        str.append(installer.createNewFile("temp//", "temp.txt"));

        writeTextToFile("C://Work//Netology//Games//temp//temp.txt", str.toString());
    }

    private static void writeTextToFile(String s, String text) {
        try (FileWriter writer = new FileWriter(s, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
