package com.newsamerica.beans;

import java.util.Calendar;
import java.util.Date;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;

@Node(jcrType="smartsource:newsdocument")
public class NewsDocument extends BaseDocument implements ContentNodeBinder{
	
	
	
	 private Date date;
	    private String html;
	    private HippoGalleryImageSetBean image;
	    private String summary;
	    private String title;
	
	
	
	

    public void setDate(Date date2) {
			this.date = date2;
		}

		public void setHtml(String html) {
			this.html = html;
		}

		public void setImage(HippoGalleryImageSetBean image) {
			this.image = image;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public void setTitle(String title) {
			this.title = title;
		}

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

	@Override
	public boolean bind(Object content, javax.jcr.Node node)
			throws ContentNodeBindingException {
		try {
			  super.bind(content, node);
			NewsDocument product = (NewsDocument) content;
			System.out.println("bind Me>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+product.getDate());
         //   node.setProperty("smartsource:date", product.getDate());
       //     node.setProperty("smartsource:body", "Test body");
            node.setProperty("smartsource:summary", getSummary());
            node.setProperty("smartsource:title", getTitle());
          //  node.setProperty("smartsource:image", product.getImage().getPath());
          //  node.setProperty("smartsource:tags", product.getTags());
            return true;
        } catch (RepositoryException e) {
            throw new ContentNodeBindingException(e);
        }
	}

}
