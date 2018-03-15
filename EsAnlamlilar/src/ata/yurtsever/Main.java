package ata.yurtsever;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableElement;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;

import javax.swing.text.html.HTML;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        String userInput;
        try {
            WebClient client = new WebClient( BrowserVersion.CHROME);

            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage page = client.getPage("http://www.tdk.gov.tr/index.php?option=com_gts");






            HtmlTextInput searchBox = (HtmlTextInput)page.getFirstByXPath(".//input");

            HtmlButton ara = (HtmlButton) page.getFirstByXPath(".//button");
            System.out.println("input");
            userInput = sc.nextLine();

            searchBox.setValueAttribute(userInput);


            page = ara.click();
         ;

            HtmlTable tab = (HtmlTable) page.getElementById( "hor-minimalist-a");

            HtmlTableBody body =  tab.getBodies().get(0);

            HtmlTableRow myRow = body.getRows().get(0);
            HtmlTableCell cell = myRow.getCell(0);
            System.out.println( cell.asText());
            printWords( cell.asText());
        }
        catch ( Exception e) {
            //this is a bad code
        }
    }

    public static void printWords( String s){
        s = s.substring( 2);
        if( s.charAt(0) == 'i' || s.charAt(1) == 'i')
            s = s.substring(5);
        s = removeParanthesis(s);
        System.out.println("isimsiz parantezsiz ---" +s);
        String[] arr = s.split( " ");
        for( int i = 0; i < arr.length ; i++){
            String el = arr[i];

            if(el.indexOf(',') != -1 && arr[ i - 1].indexOf(',') != -1){
                System.out.println(el);
            }
        }

    }

    public static String removeParanthesis( String s){
        boolean inBetween = false; // < and >
        String filteredContent = "";
        boolean bool;
        for( int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            do{
                bool = false;
                if( c == '&'){
                    i = s.substring(i).indexOf( ';') + i + 1;
                    c = s.charAt(i);
                    bool = true;
                }
            }while(bool);

            if( c == '(' )
                inBetween = !inBetween;
            if(!inBetween)
                filteredContent += c;
            if( c == ')')
                inBetween = !inBetween;

        }
        return filteredContent;
    }
}



