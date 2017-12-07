import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FilesSearcherTest {

    @Test
    public void testFilesCount() {
        Path root = Paths.get("J:\\Public\\Release_Area\\TLMQ\\PrintingForms");
        try {
            Map<Integer, Long> stats = Files.walk(root)
                    .parallel()
                    .collect(groupingBy(n -> Files.isDirectory(n, LinkOption.NOFOLLOW_LINKS) ? 1 : 2, counting()));
            System.out.format("Files: %d, dirs: %d. ", stats.get(2), stats.get(1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindFilesOfUser() {
        Path root = Paths.get("J:\\Public\\Release_Area\\TLMQ\\PrintingForms");
        try {
            Map<Integer, Long> stats = Files.walk(root)
                    .parallel()
                    .collect(groupingBy(n -> Files.isDirectory(n, LinkOption.NOFOLLOW_LINKS) ? 1 : 2, counting()));
            System.out.format("Files: %d, dirs: %d. ", stats.get(2), stats.get(1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
