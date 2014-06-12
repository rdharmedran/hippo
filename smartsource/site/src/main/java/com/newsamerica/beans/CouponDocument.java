package com.newsamerica.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;

@Node(jcrType="smartsource:couponsdocument")
public class CouponDocument extends BaseDocument{

    public Calendar getDate() {
        return getProperty("smartsource:date");
    }

    public HippoHtml getHtml(){
        return getHippoHtml("smartsource:body");    
    }

    /**
     * Get the imageset of the newspage
     *
     * @return the imageset of the newspage
     */
    public HippoGalleryImageSetBean getImage() {
        return getLinkedBean("smartsource:image", HippoGalleryImageSetBean.class);
    }

    public String getSummary() {
        return getProperty("smartsource:summary");
    }

    public String getTitle() {
        return getProperty("smartsource:title");
    }

}
