package com.acme.paas.common.model.adfbc.entities;

import com.acme.paas.fwk.EntityImpl;

import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Oct 04 16:14:02 BST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxcusLookupTypesEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        LookupType,
        Description,
        CreatedBy,
        CreationDate,
        LastUpdatedBy,
        LastUpdateLogin,
        LastUpdateDate,
        ObjectVersionNumber,
        XxcusLookupValuesEO;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return XxcusLookupTypesEOImpl.AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return XxcusLookupTypesEOImpl.AttributesEnum.firstIndex() + XxcusLookupTypesEOImpl.AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = XxcusLookupTypesEOImpl.AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int LOOKUPTYPE = AttributesEnum.LookupType.index();
    public static final int DESCRIPTION = AttributesEnum.Description.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATELOGIN = AttributesEnum.LastUpdateLogin.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int OBJECTVERSIONNUMBER = AttributesEnum.ObjectVersionNumber.index();
    public static final int XXCUSLOOKUPVALUESEO = AttributesEnum.XxcusLookupValuesEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public XxcusLookupTypesEOImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.acme.paas.common.model.adfbc.entities.XxcusLookupTypesEO");
    }

    /**
     * Gets the attribute value for LookupType, using the alias name LookupType.
     * @return the LookupType
     */
    public String getLookupType() {
        return (String)getAttributeInternal(LOOKUPTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LookupType.
     * @param value value to set the LookupType
     */
    public void setLookupType(String value) {
        setAttributeInternal(LOOKUPTYPE, value);
    }

    /**
     * Gets the attribute value for Description, using the alias name Description.
     * @return the Description
     */
    public String getDescription() {
        return (String)getAttributeInternal(DESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for Description.
     * @param value value to set the Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CreationDate, using the alias name CreationDate.
     * @return the CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreationDate.
     * @param value value to set the CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy.
     * @return the LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedBy.
     * @param value value to set the LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin.
     * @return the LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdateLogin.
     * @param value value to set the LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**
     * Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate.
     * @return the LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdateDate.
     * @param value value to set the LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for ObjectVersionNumber, using the alias name ObjectVersionNumber.
     * @return the ObjectVersionNumber
     */
    public Number getObjectVersionNumber() {
        return (Number)getAttributeInternal(OBJECTVERSIONNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for ObjectVersionNumber.
     * @param value value to set the ObjectVersionNumber
     */
    public void setObjectVersionNumber(Number value) {
        setAttributeInternal(OBJECTVERSIONNUMBER, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getXxcusLookupValuesEO() {
        return (RowIterator)getAttributeInternal(XXCUSLOOKUPVALUESEO);
    }

    /**
     * @param lookupType key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String lookupType) {
        return new Key(new Object[]{lookupType});
    }


}
