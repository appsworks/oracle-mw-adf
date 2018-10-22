package com.acme.paas.properties.views.mb;

import com.acme.paas.common.view.ADFUtils;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.binding.BindingUtils;
import oracle.adf.model.BindingContext;

import oracle.binding.OperationBinding;

public class PropertyDefMB {
    public PropertyDefMB() {
    }

    public void cancelEdit(ActionEvent evt) {


        // User has hit Cancel.  Put a message on the stack if changes
        // were made and allow the task flow to rollback

        Boolean isTxnDirty = Boolean.FALSE;

        isTxnDirty =
                    (Boolean)ADFUtils.executeOperation("isTransactionDirty");

    }
    
    public void saveAndContinue(ActionEvent evt) {
        validateAndSave(Boolean.FALSE);
    }


    public void saveAndExit(ActionEvent evt) {

        if (validateAndSave(Boolean.TRUE)) {

            // Navigate the task flow to the completion activity

            FacesContext ctx = FacesContext.getCurrentInstance();
            NavigationHandler nh = ctx.getApplication().getNavigationHandler();
            nh.handleNavigation(ctx, null, "complete");

        }

    }
    
    private boolean validateAndSave(Boolean clearCache) {

        HashMap<String, Object> params = new HashMap<String, Object>(1);
        params.put("clearCache", clearCache);

        OperationBinding op = ADFUtils.findOperation("saveTransaction");
        Map paramsMap = op.getParamsMap();
        paramsMap.clear();
        paramsMap.putAll(params);
        op.execute();

        if (!op.getErrors().isEmpty()) {
            return false;
        }

        return true;

    }    
}
