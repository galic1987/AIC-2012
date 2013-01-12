package tuwien.aic12.server;

/**
 *
 * @author hp
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class FileSearcher {

    public static String Read(String filePath) throws java.io.IOException {
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        try {
            String foundFile = FindFile(filePath);
            File file = new File(foundFile);
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append("\n");
            }
        } catch (Exception e) {
            contents.append(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                contents.append(e.getMessage());
                e.printStackTrace();
            }
        }
        return contents.toString();
    }

    public static String FindFile(String fileName) {
        URL url = FileSearcher.class.getResource("/files/" + fileName);
        if(url != null) {
            return url.getPath();            
        } 
        String path = searchFolder(".", fileName);
        return path;
    }

    private static String searchFolder(String folder, String fileName) {
        String fullPath = "";
        File dir = new File(folder);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                // Get filename of file or directory
                    if (children[i].toLowerCase().indexOf(fileName.toLowerCase()) >= 0) {
                        fullPath = folder + "/" + fileName;
                        return fullPath;
                    } else if (children[i].indexOf("..") < 0) {
                        String subSearch = searchFolder(folder + "/" + children[i], fileName);
                        if (subSearch.toLowerCase().indexOf(fileName.toLowerCase()) >= 0) {
                            return subSearch;
                        }
                    }
                }
            }
        }       
        return "File not found!";        
    }
}
