package cybersoft.java12.crmapp.filter;

import javax.servlet.annotation.WebFilter;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

import cybersoft.java12.crmapp.util.Path;

@WebFilter(filterName = "sitemesh", urlPatterns = Path.ROOT)
public class SitemeshFilter extends SiteMeshFilter{

}
