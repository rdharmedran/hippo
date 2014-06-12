package com.newsamerica.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

@Node(jcrType="smartsource:textdocument")
public class TextDocument extends BaseDocument{
    
    public HippoHtml getHtml(){
        return getHippoHtml("smartsource:body");    
    }

    public String getSummary() {
        return getProperty("smartsource:summary");
    }
 
    public String getTitle() {
        return getProperty("smartsource:title");
    }

}
