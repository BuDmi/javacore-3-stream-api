import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String gameFolderPath = "C://Work//Netology//Games";
        installGame(gameFolderPath);
        saveGame(gameFolderPath);
        loadGame(gameFolderPath);
    }

    private static void loadGame(String gameFolderPath) {
        List<GameProgress> loadedGameProgresses = GameLoader.loadGame(gameFolderPath);
        for(GameProgress gameProgress: loadedGameProgresses) {
            System.out.println(gameProgress);
        }
    }

    private static void saveGame(String gameFolderPath) {
        List<GameProgress> gameProgresses = new ArrayList<>();
        gameProgresses.add(new GameProgress(100, 2, 3, 12.0));
        gameProgresses.add(new GameProgress(90, 2, 1, 1.0));
        gameProgresses.add(new GameProgress(75, 2, 6, 163.0));

        GameSaver.saveGame(gameFolderPath, gameProgresses);
    }

    private static void installGame(String gameFolderPath) {
        Installer installer = new Installer(gameFolderPath);
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

        writeTextToFile( gameFolderPath + "//temp//temp.txt", str.toString());
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
