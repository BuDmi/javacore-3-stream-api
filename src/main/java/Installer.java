import java.io.File;
import java.io.IOException;

public class Installer {
    private final String initialFolderPath;

    public Installer(String initialFolderPath) {
        this.initialFolderPath = initialFolderPath;
    }

    public String createNewFolder(String relativeFolderPath, String folderName) {
        File folder = new File(initialFolderPath + "//" + relativeFolderPath + folderName);

        if (folder.mkdir()) {
            return folderName + ": success\n";
        } else {
            return folderName + ": fail\n";
        }
    }

    public String createNewFile(String relativeFolderPath, String fileName) {
        File file = new File(initialFolderPath + "//" + relativeFolderPath, fileName);

        try {
            if (file.createNewFile()) {
                return fileName + ": success\n";
            } else {
                return fileName + ": fail\n";
            }
        } catch (IOException ex) {
            return fileName + ": fail\n";
        }
    }
}
