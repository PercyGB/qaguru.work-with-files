package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.selenide.AllureSelenide;
import net.lingala.zip4j.exception.ZipException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import utils.Files;

import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.*;
import static utils.Zip.unzip;

public class AssertDataFromFilesTests {

    private String txtFilePath = "./src/test/resources/files/1.txt";
    private String csvFilePath = "./src/test/resources/files/1.csv";
    private String pdfFilePath = "./src/test/resources/files/1.pdf";
    private String xlsFilePath = "./src/test/resources/files/1.xls";
    private String xlsxFilePath = "./src/test/resources/files/1.xlsx";
    private String zipFilePath = "./src/test/resources/files/1.zip";
    private String docFilePath = "./src/test/resources/files/1.doc";
    private String docxFilePath = "./src/test/resources/files/1.docx";

    private String expectedData = "Hello, World!";

    @Test
    void assertDataFromTxtFile() throws IOException {
        String actualData = Files.readTextFromPath(txtFilePath);

        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void assertDataFromCsvFile() throws IOException {
        String actualData = readTextFromPath(csvFilePath);

        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void assertDataFromPdfFile() throws IOException {
        PDF pdf = getPdfFile(pdfFilePath);

        assertThat(pdf, PDF.containsText(expectedData));
    }

    @Test
    void assertDataFromXlsFile() throws IOException {
        XLS xls = Files.getXlsFromPath(xlsFilePath);

        assertThat(xls, XLS.containsText(expectedData));
    }

    @Test
    void assertDataFromXlsxFile() throws IOException {
        String actualData = readXlsxFromPath(xlsxFilePath);

        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void zipWithPasswordTest() throws IOException, ZipException {
        String unzipFolderPath = "./src/test/resources/files/unzip";
        String zipPassword = "123";
        String unzipTxtFilePath = "./src/test/resources/files/unzip/1.txt";

        unzip(zipFilePath, unzipFolderPath, zipPassword);

        String actualData = readTextFromPath(unzipTxtFilePath);

        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void assertDataFromDocFile() throws IOException {
        String actualData = readDocFromPath(docFilePath).getText();

        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void assertDataFromDocxFile() throws IOException {
        String actualData = readDocxFromPath(docxFilePath).getText();

        assertThat(actualData, containsString(expectedData));
        System.out.println();
    }

}
