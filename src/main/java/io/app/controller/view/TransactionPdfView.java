package io.app.controller.view;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.app.dto.Transaction;
import io.app.util.TransactionList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Component
public class TransactionPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TransactionList  trxs= (TransactionList) model.get("trxList");
        response.setHeader("Content-Disposition","inline; filename="+trxs.getTrxDate()+".pdf");
        List<Transaction> trxList=trxs.getTransactions();
      /*  ClassPathResource resource=new ClassPathResource("s3rice1.jpg");
        Image image=Image.getInstance(resource.getURL());
        image.setWidthPercentage(20f);
        image.setBorderWidth(20f);
        document.add(image);*/
        //document.add(Image.getInstance("S:\\images\\line.png"));
       // document.add(Image.getInstance("S:\\images\\line.png"));

        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        document.add(new Paragraph("Transaction Details  : "+trxs.getTrxDate(),font));

        PdfPTable table=new PdfPTable(7);
        table.setWidths(new float[] {10f,10f,10f,10f,10f,10f,10f});
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(16);



        Font headFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        headFont.setColor(Color.WHITE);
        headFont.setSize(10);



        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Transaction Id", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Account Number", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Name", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Address", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Transaction Date", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Paid Amount", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Remaining Due", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.MAGENTA);
        hcell.setPadding(7);
        table.addCell(hcell);


        Font fontForData=new Font();
        fontForData.setColor(Color.BLACK);
        fontForData.setSize(7);

        for(Transaction trx:trxList){

            PdfPCell cell;


            cell = new PdfPCell(new Phrase(trx.getTransactionId(),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(String.valueOf(trx.getAccount().getAccountNumber()),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(trx.getAccount().getCustomer().getFirstName(),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(trx.getAccount().getCustomer().getAddr().getVillage(),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);




            cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MM-YYYY hh:mm:ss").format(trx.getTimestamp()),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(String.valueOf(trx.getTransactionAmount()),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(String.valueOf(trx.getTheDueAfterPayment()),fontForData));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.white);
            cell.setPadding(7);
            table.addCell(cell);

        }

      //  document.add(new Paragraph(" Generated On "+new Date(),headFont));
        Font f1=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        f1.setColor(Color.DARK_GRAY);
        f1.setSize(9);
        document.add(table);
        document.add(new Paragraph("The Total Collected Amount  : "+trxs.getTotalTrxAmount()+"  |   Generated On : "+new Date(),f1));

        document.close();
    }
}