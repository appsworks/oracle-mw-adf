package com.acme.paas.properties.views.mb;

import com.acme.paas.common.view.ADFUtils;

import com.acme.paas.common.view.JSFUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.util.ComponentReference;

public class SearchPropertiesMB {
    private ComponentReference searchPropertiesResults;

    public SearchPropertiesMB() {
    }


    public void editProperty(ActionEvent actionEvent) {
        // Add event code here...
        // MR: Get a handle on the selected rows
        RowKeySet rks = getSearchPropertiesResults().getSelectedRowKeys();
        Iterator it = rks.iterator();
        DCIteratorBinding srchIter = ADFUtils.findIterator("XxcusPersonPropertiesVO1Iterator");
        if (srchIter != null)
        {      
        RowSetIterator srchRSI = srchIter.getRowSetIterator();
        ArrayList<Number> structList = new ArrayList<Number>();

        Key k = null;
        Row srchRow = null;
        Number structId = null;
        
        // MR: Create an arraylist of struct identifiers
        while(it.hasNext()){

            k = (Key)((List)it.next()).get(0);
            srchRow = srchRSI.getRow(k);
            structId = (Number)srchRow.getAttribute("PersonPropertyId");
            structList.add(structId);

        }
        
        
        if(structList.size() == 0){
            return;
        }
        
        
        // MR: Place struct list on PFS
        JSFUtils.setExpressionValue("#{pageFlowScope.pCallMode}", "EDIT");
        JSFUtils.setExpressionValue("#{pageFlowScope.pStructList}", structList);
        
        // MR: Navigate the task flow to the Cascade def flow
        FacesContext ctx = FacesContext.getCurrentInstance();
        NavigationHandler nh = ctx.getApplication().getNavigationHandler();       
        nh.handleNavigation(ctx, null, "launchPersonPropertyDef");
        } else {
          return;      
        }
    }

    // ++ Accessors from here

    public void setSearchPropertiesResults(RichTable searchPropertiesResults) {
        this.searchPropertiesResults = ComponentReference.newUIComponentReference(searchPropertiesResults);
    }

    public RichTable getSearchPropertiesResults() {
        if (this.searchPropertiesResults != null) {
            return (RichTable)this.searchPropertiesResults.getComponent();
        }
        return null;
    }
}
