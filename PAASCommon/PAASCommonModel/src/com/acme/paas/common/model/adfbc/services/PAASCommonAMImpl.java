package com.acme.paas.common.model.adfbc.services;

import com.acme.paas.common.model.adfbc.views.XxcusLookupValuesEOVOImpl;
import com.acme.paas.common.model.adfbc.views.XxcusLookupValuesVOImpl;
import com.acme.paas.fwk.ApplicationModuleImpl;

import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Oct 04 16:15:05 BST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PAASCommonAMImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public PAASCommonAMImpl() {
    }


    /**
     * Container's getter for XxcusLookupValuesEOVO1.
     * @return XxcusLookupValuesEOVO1
     */
    public XxcusLookupValuesEOVOImpl getXxcusLookupValuesEOVO1() {
        return (XxcusLookupValuesEOVOImpl)findViewObject("XxcusLookupValuesEOVO1");
    }


    /**
     * Container's getter for XxcusLookupValuesVO1.
     * @return XxcusLookupValuesVO1
     */
    public XxcusLookupValuesVOImpl getXxcusLookupValuesVO1() {
        return (XxcusLookupValuesVOImpl)findViewObject("XxcusLookupValuesVO1");
    }


}
