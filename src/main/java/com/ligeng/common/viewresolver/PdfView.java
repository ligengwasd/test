package com.ligeng.common.viewresolver;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Map;


/**
 * Created by dev on 16-5-5.
 */
public class PdfView extends AbstractPdfView{

    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,HttpServletRequest request, HttpServletResponse response) throws Exception {
//        DemoObj demoObj = (DemoObj) model.get("demoObj");

        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBackgroundColor(Color.lightGray);

        table.addCell("ID");
        table.addCell("NAME");

        table.addCell("100");
        table.addCell("ligeng");

        document.add(table);

    }
}
