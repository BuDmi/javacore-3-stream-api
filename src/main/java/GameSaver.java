import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameSaver {
    public static void saveGame(String folderPath, List<GameProgress> listOfGameProgress) {
        List<String> filesToZip = new ArrayList<>();
        for (int i = 0; i < listOfGameProgress.size(); i++) {
            String filePath = folderPath + "//savegames//save" + (i + 1) + ".dat";
            saveGameStatusToFile(filePath, listOfGameProgress.get(i));
            filesToZip.add(filePath);
        }
        zipGameStatus(folderPath + "//savegames//zip.zip", filesToZip);
        deleteFiles(filesToZip);
    }

    private static void saveGameStatusToFile(String saveFilePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(saveFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println("Failed on saving game status to file");
        }
    }

    private static void zipGameStatus(String archivePath, List<String> filesToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archivePath))) {
            for (int i = 0; i < filesToZip.size(); i++) {
                try (FileInputStream fis = new FileInputStream(filesToZip.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + (i + 1) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deleteFiles(List<String> filesToDelete) {
        for (String filePath: filesToDelete) {
            File fileToDelete = new File(filePath);
            if (!fileToDelete.delete()) {
                System.out.println("Error on deleting file: " + filePath);
            }
        }
    }
}
