package com.acme.paas.common.model;

import java.io.ByteArrayOutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ApplicationModule;
import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.XMLInterface;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.SequenceImpl;

import oracle.jbo.server.ViewObjectImpl;

import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.XMLNode;

import org.w3c.dom.Text;


/**
 *
 * Utility class for ADF model projects
 */
public class ModelUtils {

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ModelUtils.class);

    /**
     * Fetches next database sequence value
     * @param am Application Module
     * @param sequenceName Sequence Name (schema optional)
     * @return Sequence value
     */
    public static Number getSequenceValue(ApplicationModule am, String sequenceName){

        SequenceImpl sq = new SequenceImpl(sequenceName, am);
        return sq.getSequenceNumber();

    }


    /**
     *
     * @param txn DBTransaction
     * @param sequenceName Sequence name (schema optional)
     * @return Sequence value
     */
    public static Number getSequenceValue(DBTransaction txn, String sequenceName){

        SequenceImpl sq = new SequenceImpl(sequenceName, txn);
        return sq.getSequenceNumber();

    }


    /**
     *
     * Creates an instance of oracle.jbo.domain.Number from a String
     * @param numberStr String to convert
     */
    public static Number getOracleNumber(String numberStr)
    {

        if (numberStr == null || numberStr.equals("")) {
            return null;
        }

        Number oraNumber = null;
        try {
            oraNumber = new Number(numberStr);
        } catch (Exception e) {
            throw new JboException(
                new StringBuilder("Failed to create oracle.jbo.domain.Number from String value '").append
                                (numberStr).append
                                ("' Error: ").append
                                (e.toString()).toString());
        }
        return oraNumber;
    }


    /**
     *
     * Creates an instance of oracle.jbo.domain.Date from a String
     * @param dateStr String to convert
     * @param fmt Java date format of string
     */
    public static Date getOracleDate(String dateStr,
                                     String fmt) {

        if (dateStr == null || dateStr.equals("")) {
            return null;
        }

        Date oraDate = null;
        if (fmt == null)
            fmt = "yyyy-MM-dd";
        try {
            DateFormat sf = new SimpleDateFormat(fmt);
            java.util.Date dateVal = sf.parse(dateStr);
            Timestamp timeSt = new Timestamp(dateVal.getTime());
            oraDate = new Date(timeSt);
        } catch (Exception e) {
            throw new JboException(
                new StringBuilder("Failed to create oracle.jbo.domain.Date from String value '").append
                                (dateStr).append
                                ("' with format '").append
                                (fmt).append
                                ("' Error: ").append
                                (e.toString()).toString());
        }
        return oraDate;

    }


    /**
     *
     * Creates an instance of oracle.jbo.domain.Date from a java.util.date
     * @param dateStr String to convert
     * @param fmt Java date format of string
     */
    public static Date getOracleDate(java.util.Date javaDate) {
    
        if(javaDate == null){
            return null;
        }
    
        long t = javaDate.getTime();
        Timestamp ts = new Timestamp(t);

        return new Date(ts);
    
    }


    /**
     *
     * Creates an instance of oracle.jbo.domain.Date as of truncated system time
     * (time as of midnight)
     */
     public static Date getCurrentSystemDate(){
         return new Date(new java.sql.Date(System.currentTimeMillis()));
     }


    /**
     *
     * Creates an instance of oracle.jbo.domain.Date as of system time including
     * time element
     */
    public static Date getCurrentSystemDateTime(){
        return new Date(new Timestamp(System.currentTimeMillis()));
    }


    /**
     * Adds number of seconds to date object returning new date instance
     * @param dateValue Date value
     * @param seconds Number of seconds
     * @return new Date instance
     */
    public static Date addSeconds(Date dateValue, int seconds){

        if(dateValue == null){
            return null;
        }

        Timestamp olddateValts = dateValue.timestampValue();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(olddateValts.getTime());
        cal.add(Calendar.SECOND, seconds);

        return new Date(new Timestamp(cal.getTimeInMillis()));

    }


    /**
     * Adds number of minutes to date object returning new date instance
     * @param dateValue Date value
     * @param minutes Number of seconds
     * @return new Date instance
     */
    public static Date addMinutes(Date dateValue, int minutes){

        if(dateValue == null){
            return null;
        }

        Timestamp olddateValts = dateValue.timestampValue();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(olddateValts.getTime());
        cal.add(Calendar.MINUTE, minutes);

        return new Date(new Timestamp(cal.getTimeInMillis()));

    }


    /**
     * Adds number of hours to date object returning new date instance
     * @param dateValue Date value
     * @param hours Number of seconds
     * @return new Date instance
     */
    public static Date addHours(Date dateValue, int hours){

        if(dateValue == null){
            return null;
        }

        Timestamp olddateValts = dateValue.timestampValue();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(olddateValts.getTime());
        cal.add(Calendar.HOUR, hours);

        return new Date(new Timestamp(cal.getTimeInMillis()));

    }


    /**
     * Fetches connection from containing transaction
     * @param txn DBTransaction
     * @return JDBC connection
     */
    public static Connection getTransactionConnection(DBTransaction txn) {

        if(txn == null){
            return null;
        }

        PreparedStatement st =
            txn.createPreparedStatement("commit", 1);
        Connection conn = null;
        try {
            conn = st.getConnection();
        } catch (SQLException e) {
            LOGGER.warning("Failed to fetch connection: " + e.getMessage());
        } finally {
            try {
                st.close();
            } catch (Exception e) {
                LOGGER.warning("Failed to close statement: " + e.getMessage());
            }
        }

        return conn;

    }

    /**
     * Creates a text XML node based on the parameters passed
     * @param tag The XML tags for the node
     * @param tagValue The value to be held in the tag
     */
    public static XMLNode createNode(String tag, String tagValue)
    {
      if (tag == null)
        return null;

      XMLDocument xmldocument = new XMLDocument();

      XMLNode element = (XMLNode)xmldocument.createElement(tag);
      xmldocument.appendChild(element);

      if (tagValue != null)
      {
        Text text = xmldocument.createTextNode("text#");
        text.setNodeValue(tagValue);
        element.appendChild(text);
      }
      XMLNode element2 = (XMLNode)xmldocument.getDocumentElement();
      element2.getParentNode().removeChild(element2);
      return element2;
    }

    /**
     * Adds a child node to the parent node
     * @param parentNode The parent node
     * @param childNode The child node
     */
     public static XMLNode addNode(XMLNode parentNode, XMLNode childNode)
     {
       if (parentNode == null)
         return null;
       if (childNode == null)
         return parentNode;

       XMLDocument xmldocument = (XMLDocument)parentNode.getOwnerDocument();
       XMLNode node = null;

       try
       {
         parentNode.appendChild(childNode);
       }
       catch (Exception e)
       {
         node = (XMLNode)xmldocument.adoptNode(childNode);
         parentNode.appendChild(node);
       }
       return parentNode;
     
     }

    /**
     * Converts the specified XMLNode into string format
     * @param node The node to convert into String
     */
     public static String convertNodeToString(XMLNode node)
     {
       if (node == null)
         return null;

       ByteArrayOutputStream st = new ByteArrayOutputStream();
       String xml = null;
       try
       {
         node.print(st);
         if (st != null)
           xml = st.toString();
       }
       catch (Exception e)
       {
           throw new JboException(
               new StringBuilder("Failed to convert Node to String Error:").append
                               (e.toString()).toString());
       }
       finally
       {
         if (st != null)
         {
          try
          {
            st.flush();
            st.close();
          }
          catch (Exception e){}
         }
       }
       return xml;
     }

    /**
     *
     * Returns the XML document for data in the supplied row
     * @param row The row data to convert into a XML document
     */
     public static XMLNode getRowXML(Row row)
     {
       if (row==null)
         return null;

       XMLNode node = (XMLNode)row.writeXML(-1,XMLInterface.XML_OPT_ALL_ROWS);
       return node;
     }

    /**
     *
     * Returns the XML document for data in the supplied VO
     * @param vo The view object data to convert into a XML document
     */
     public static XMLNode getVOXML(ViewObject vo)
     {
       if (vo==null)
         return null;

       XMLNode node = (XMLNode)vo.writeXML(-1,XMLInterface.XML_OPT_ALL_ROWS);
       return node;
     }

    /**
     * Returns existing view object instance if it exists otherwise returns new
     * instance
     * @param defName VO definition
     * @param instanceName VO instance name
     * @param am Application Module
     */
    public static ViewObject getViewObject(String defName, String instanceName, ApplicationModule am){


        if(am == null){
            return null;
        }

        ViewObject vo = am.findViewObject(instanceName);
        if(vo == null && defName != null){

            return am.createViewObject(instanceName, defName);

        }

        return vo;

    }
    
    
    /**
     * Utility method to return java date (with timestamp) from JBO date
     * @param jboDate Jbo Date
     */
    public static java.util.Date toJavaDate(Date jboDate){
        
        if(jboDate == null){
            return null;
        }
        
        return new java.util.Date(jboDate.timestampValue().getTime());
        
    }
    
    public static List<String> getBindsInString(String sql) {
        List<String> bindList = new ArrayList<String>();
        String withoutQuotes = sql.replaceAll("[\'\"].*?[\'\"]", "");
        Pattern pattern = Pattern.compile("(?<!')(:[\\w]*)(?!')");
        Matcher matcher = pattern.matcher(withoutQuotes);
        while (matcher.find()) {
            String bindName = matcher.group().substring(1);
            bindList.add(bindName);
        }
        return bindList;
    }
}
