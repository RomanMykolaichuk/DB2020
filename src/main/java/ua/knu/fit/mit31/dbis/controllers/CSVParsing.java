/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

import org.springframework.web.multipart.MultipartFile;
import ua.knu.fit.mit31.dbis.dao.NewTable;

/**
 *
 * @author Roman
 */
public class CSVParsing {

    public static ArrayList<NewTable> loadData(MultipartFile multipartFile) throws IOException {
        ArrayList<NewTable> result = new ArrayList<>();

        Reader in = null;

        try {

            File helperFile = File.createTempFile("data", "csv");

            FileUtils.writeByteArrayToFile(helperFile, multipartFile.getBytes());

            in = new FileReader(helperFile);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {

                NewTable tableRow = new NewTable(record.get(0).trim() + " " + record.get(1).trim(),
                        Integer.parseInt(record.get(2).trim()));
                result.add(tableRow);

            }
            helperFile.deleteOnExit();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVParsing.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();

            } catch (IOException ex) {
                Logger.getLogger(CSVParsing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

    }
}
