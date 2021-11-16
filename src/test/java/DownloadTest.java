import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DownloadTest {
    @Test
    public void downloadTxt() throws IOException {
        open("https://github.com/selenide/selenide/blob/master/README.md");
        File file = $("#raw-url").download();
        String fileContent = IOUtils.toString(new FileReader(file));
        Assertions.assertTrue(fileContent.contains("Andrei Solntsev"));
    }

    @Test
    public void downloadPdf() throws IOException {
        open("https://junit.org/junit5/docs/current/user-guide/");
        PDF pdfFile = new PDF($(byText("PDF download")).download());
        int numberOfPages = pdfFile.numberOfPages;
        Assertions.assertEquals(164, numberOfPages);
        Assertions.assertTrue(pdfFile.author.contains("Stefan Bechtold"));
    }

    @Test
    public void downloadXls() throws FileNotFoundException {
        open("https://www.ikar100.ru/index.php?v=price");
        XLS xls = new XLS($(".em1.fcb").download());
        Assertions.assertEquals("dsdsd", xls.excel.getNameAt(0).getNameName());
    }

    @Test
    public void openCsvFile() throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();
        try (InputStream stream = loader.getResourceAsStream("csvFile.csv");
             Reader reader = new InputStreamReader(stream)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> scvStrings = csvReader.readAll();
            String[] firstScvArr = scvStrings.get(0);
            Assertions.assertEquals("Andrey", firstScvArr[0]);
        }
    }

    @Test
    public void openZipFile() throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();

        try (InputStream stream = loader.getResourceAsStream("zipFile.zip");
             ZipInputStream zip = new ZipInputStream(stream)) {
            ZipEntry entry;
            List<ZipEntry> zipList = new ArrayList<>();
            while ((entry = zip.getNextEntry()) != null) {
                zipList.add(entry);
            }
            Assertions.assertEquals(3, zipList.size());
        }
    }
}
