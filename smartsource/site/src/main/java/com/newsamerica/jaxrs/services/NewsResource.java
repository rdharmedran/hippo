
package com.newsamerica.jaxrs.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ObjectBeanPersistenceException;
import org.hippoecm.hst.content.beans.manager.ObjectBeanManagerImpl;
import org.hippoecm.hst.content.beans.manager.ObjectConverter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.util.NodeUtils;
import org.hippoecm.repository.api.Document;
import org.hippoecm.repository.api.HippoNodeType;
import org.hippoecm.repository.api.HippoWorkspace;
import org.hippoecm.repository.api.StringCodec;
import org.hippoecm.repository.api.StringCodecFactory;
import org.hippoecm.repository.api.Workflow;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.api.WorkflowManager;
import org.hippoecm.repository.standardworkflow.DefaultWorkflow;
import org.hippoecm.repository.standardworkflow.EditableWorkflow;
import org.hippoecm.repository.standardworkflow.FolderWorkflow;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryManager;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.hippoecm.hst.util.HstRequestUtils;
import org.hippoecm.hst.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;



import com.newsamerica.beans.NewsDocument;
import com.newsamerica.jaxrs.model.DocumentRepresentation;
/**
 * @version $Id$
 */
@Path("/news/")
public class NewsResource extends AbstractResource {
    
    private static Logger log = LoggerFactory.getLogger(NewsResource.class);
    
    
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String SUCCESS = "success";
    private static final String ERRORS = "errors";
    private static final String DATE_PATTERN = "yyyy-MM-dd HH.mm.ss.SSS";

    
    
    
    
    
    
    
    
    
    
    @PUT
    @Path("/{item}/{description}")
    public void addNews(@PathParam("item") String item, @QueryParam("description") String desc) {
    	//this works to
    	System.out.println("item: "+item+", "+desc);
    }
 
    
    
    @GET
    @Path("/{item}/{description}")
    public DocumentRepresentation getNewsResource(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context UriInfo uriInfo,
            @QueryParam("sortby") @DefaultValue("hippogogreen:rating") String sortBy, 
            @QueryParam("sortdir") @DefaultValue("descending") String sortDirection,
            @QueryParam("max") @DefaultValue("10") String maxParam) {
        
        List<DocumentRepresentation> newsList = new ArrayList<DocumentRepresentation>();
        HstRequestContext requestContext = getRequestContext(servletRequest);
        NewsDocument retnewsBean = null;
        try {
            String mountContentPath = requestContext.getResolvedMount().getMount().getContentPath();
            log.warn("Failed to retrieve mountContentPath items.", mountContentPath);     
            Node mountContentNode = requestContext.getSession().getRootNode().getNode(PathUtils.normalizePath(mountContentPath));
            log.warn("Failed to retrieve mountContentPath items.", mountContentPath);     
  
            HstQueryManager manager = getHstQueryManager(requestContext.getSession(), requestContext);
            HstQuery hstQuery = manager.createQuery(mountContentNode, NewsDocument.class, true);
            log.warn("Failed to retrieve  hstQuery.getQueryAsString() items.",  hstQuery.getQueryAsString(true));  
           
            HstQueryResult result = hstQuery.execute();
            HippoBeanIterator iterator = result.getHippoBeans();

            while (iterator.hasNext()) {
            	  retnewsBean =(NewsDocument) iterator.nextHippoBean();
            	return new DocumentRepresentation(requestContext, null).represent(retnewsBean);
                
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Failed to retrieve news items.", e);
            } else {
                log.warn("Failed to retrieve newsitems. {}", e.toString());
            }
            
            throw new WebApplicationException(e);
        }
        return null;
    }
    
    
    
    
    
    
    
    
    @GET
    @Path("/")
    public List<DocumentRepresentation> getNewsResources(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context UriInfo uriInfo,
            @QueryParam("sortby") @DefaultValue("hippogogreen:rating") String sortBy, 
            @QueryParam("sortdir") @DefaultValue("descending") String sortDirection,
            @QueryParam("max") @DefaultValue("10") String maxParam) {
        
        List<DocumentRepresentation> newsList = new ArrayList<DocumentRepresentation>();
        HstRequestContext requestContext = getRequestContext(servletRequest);
        
        try {
            String mountContentPath = requestContext.getResolvedMount().getMount().getContentPath();
            log.warn("Failed to retrieve mountContentPath items.", mountContentPath);     
            Node mountContentNode = requestContext.getSession().getRootNode().getNode(PathUtils.normalizePath(mountContentPath));
            log.warn("Failed to retrieve mountContentPath items.", mountContentPath);     
  
            HstQueryManager manager = getHstQueryManager(requestContext.getSession(), requestContext);
            HstQuery hstQuery = manager.createQuery(mountContentNode, NewsDocument.class, true);
  
            HstQueryResult result = hstQuery.execute();
            HippoBeanIterator iterator = result.getHippoBeans();
            log.warn("Failed to retrieve  hstQuery.getQueryAsString() items.",  hstQuery.getQueryAsString(true));  
            while (iterator.hasNext()) {
                NewsDocument newsBean = (NewsDocument) iterator.nextHippoBean();
                
                if (newsBean != null) {
                    DocumentRepresentation productRep = new DocumentRepresentation(requestContext, null).represent(newsBean);
                    productRep.addLink(getNodeLink(requestContext, newsBean));
                    productRep.addLink(getSiteLink(requestContext, newsBean));
                    newsList.add(productRep);
                }
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Failed to retrieve news items.", e);
            } else {
                log.warn("Failed to retrieve newsitems. {}", e.toString());
            }
            
            throw new WebApplicationException(e);
        }
        return newsList;
    }
    
    @Persistable

    @POST
    @Path("/save")
    public  void createNewsResources(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse, @Context UriInfo uriInfo
            ) throws RepositoryException, ObjectBeanManagerException {
        
        HstRequestContext requestContext = getRequestContext(servletRequest);
        HstRequest hstRequest = HstRequestUtils.getHstRequest(servletRequest);
        HstResponse hstResponse = HstRequestUtils.getHstResponse(servletRequest, servletResponse);
        WorkflowPersistenceManager wpm = (WorkflowPersistenceManager) getPersistenceManager(requestContext);
        HippoFolderBean contentBaseFolder = getMountContentBaseBean(requestContext);
        String productFolderPath = contentBaseFolder.getPath() + "/news/2011";
        String mountContentPath = requestContext.getResolvedMount().getMount().getContentPath();
        String beanPath =null;
        try {
        	 
              log.warn("Failed to retrieve mountContentPath items.", mountContentPath);     
              Node mountContentNode = requestContext.getSession().getRootNode().getNode(PathUtils.normalizePath(mountContentPath));
        	
              
            
        	
            System.out.println(">>>>>>>>>>>>>>>>>>>>>mountContentNode>>>>>>>>>>"+mountContentNode);

        	
            System.out.println(">>>>>>>>>>>>>>>>>>>mountContentPath>>>>>>>>>>>>>"+mountContentPath);

        	


             beanPath = wpm.createAndReturn(productFolderPath, "smartsource:newsdocument", servletRequest.getParameter("name"), false);
             
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
        	  System.out.println(">>>>>>>>>>>>>>>>>>>beanPath>>>>>>>>>>>>>"+beanPath);
            NewsDocument news = (NewsDocument) wpm.getObject(beanPath);
            
              System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+productFolderPath+"/"+servletRequest.getParameter("name"));
            HippoHtml html=new HippoHtml();
          
    		news.setDate(new Date());
    		news.setHtml(servletRequest.getParameter("body"));
    		news.setSummary(servletRequest.getParameter("summary"));
    		news.setTitle(servletRequest.getParameter("title"));
    		
            wpm.update(news);
            wpm.save();
            // Note: Retrieve bean again from the repository to return.
          
        } 
      //  NewsDocument news = (NewsDocument) wpm.getObject(mountContentPath+"/"+servletRequest.getParameter("name")+"/");
    }
    
 
   
    public static Response buildServerErrorResponse(Throwable th) {
        return Response.serverError().entity(th.getCause() != null ? th.getCause().toString() : th.toString()).build();
    }
    
    
    
 
}

