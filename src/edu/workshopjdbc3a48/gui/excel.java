/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import edu.workshopjdbc3a48.entities.Credit;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.workshopjdbc3a48.services.ServiceCredit;

/**
 *
 * @author Sejir
 */
public class excel {





    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;

public void setOutputFile(String inputFile) {
    this.inputFile = inputFile;
    }

    public void write() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);
        

        // Write a few headers
        addCaption(sheet, 0, 0, "Mont_credit");
        addCaption(sheet, 1, 0, "NomClient");
        addCaption(sheet, 2, 0, "Prenom Client 1");
        addCaption(sheet, 3, 0, "Id Activité");
        addCaption(sheet, 4, 0, "Nombre Personnes");
        addCaption(sheet, 5, 0, "Numero Cabine");
        addCaption(sheet, 6, 0, "Numero Cabine");
        addCaption(sheet, 7, 0, "Numero Cabine");
        addCaption(sheet, 8, 0, "Numero Cabine");
        addCaption(sheet, 9, 0, "Numero Cabine");
        addCaption(sheet, 10, 0, "Numero Cabine");
        


    }

    private void createContent(WritableSheet sheet) throws WriteException,
          
            RowsExceededException {
        try {
            
            int i=2;
            
            
            Credit per=new Credit();
            ServiceCredit a = new ServiceCredit();
            
            String requete="SELECT *FROM credit";
            Statement st=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            // Write a few number
           
                while(rs.next()){
                    
                    
                        
                        // First column
                        addNumber(sheet, 0,i,rs.getInt(1) );
                        // Second column
                         addNumber(sheet, 1,i,rs.getInt("Mont_credit"));
                        addLabel(sheet, 2, i, rs.getString("datepe"));
                        
                        addLabel(sheet, 3, i,rs.getString("datepe"));
                        addNumber(sheet, 4, i, rs.getInt("duree_c"));
                        addLabel(sheet, 5, i,rs.getString("echeance"));
                        addNumber(sheet, 6, i, rs.getInt("taux_Interet"));
                        addNumber(sheet, 7, i, rs.getInt("decision"));
                        addLabel(sheet, 8, i,rs.getString("etat_credit"));
                        addLabel(sheet, 9, i,rs.getString("Type_credit"));
                        addNumber(sheet, 10, i, rs.getInt("numero_compte_id"));
                        
                       
                        
                        
                        
                        
                        
                        i++;
                       }}catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
            
       
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    
}
    


