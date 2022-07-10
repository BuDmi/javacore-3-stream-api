import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GameLoader {
    public static List<GameProgress> loadGame(String folderPath) {
        List<String> filesNames = openZip(folderPath + "//savegames//zip.zip", folderPath + "//savegames");
        List<GameProgress> listOfGameProgress = new ArrayList<>();
        for(String fileName: filesNames) {
            GameProgress gameProgress = openProgress(folderPath + "//savegames//" + fileName);
            listOfGameProgress.add(gameProgress);
        }
        return listOfGameProgress;
    }

    private static List<String> openZip(String zipPath, String unzipDataPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            List<String> filesNames = new ArrayList<>();
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                filesNames.add(name);
                FileOutputStream fout = new FileOutputStream(unzipDataPath + "//" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            return filesNames;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    private static GameProgress openProgress(String savedFilePath) {
        try (FileInputStream  fis = new FileInputStream(savedFilePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             return (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Fail to load game progress from file: " + savedFilePath);
            return null;
        }
    }
}
