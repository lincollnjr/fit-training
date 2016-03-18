package controle.util;

import java.io.IOException;
import java.io.Serializable;

import javax.el.ELResolver;
import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.bean.AutenticacaoBean;

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
@WebFilter("/*")
public class AutorizacaoFilter implements Filter, Serializable {

	/**
	 * 
	 */

	private AutenticacaoBean autenticacaoBean;
	private static final long serialVersionUID = 1L;
	private AutorizacaoUtil autorizacaoUtils;

	/**
	 * Default constructor.
	 */
	public AutorizacaoFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String currentView = httpRequest.getRequestURI();
		FacesContext context = getFacesContext(request, response);
		ELResolver resolver = context.getApplication().getELResolver();
		try{
			autenticacaoBean = (AutenticacaoBean) resolver.getValue(
					context.getELContext(), null, "autenticacaoBean");
			System.out.println("Ok");
		}catch(Exception e){
			System.out.println("Erro");
		}
		

		if (autorizacaoUtils.verificarPaginaDoSistema(currentView)) {

			if (autenticacaoBean != null) {
				if (autenticacaoBean.getAutenticacaoBO().isLogado()) {
					if (autorizacaoUtils
							.verificarVoltarPaginaLoginLogado(currentView)) {
						if (!response.isCommitted())
							httpResponse
									.sendRedirect("/FitTraining/pages/erro/erroAcessoLogout.jsf");
					}
				}
			}
			if (!autorizacaoUtils
					.verificarPaginasLivreDeAutorizacao(currentView)) {

				if (!autorizacaoUtils.verificarAutorizacao(autenticacaoBean.getAutenticacaoBO()
						.getUsuario().getContaLogada(), currentView)) {

					if (!response.isCommitted())
						httpResponse
								.sendRedirect("/FitTraining/pages/erro/erroAcesso.jsf");

				}

			}
		}

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		autorizacaoUtils = new AutorizacaoUtil();
	}

	public AutorizacaoUtil getAutorizacaoUtils() {
		return autorizacaoUtils;
	}

	public void setAutorizacaoUtils(AutorizacaoUtil autorizacaoUtils) {
		this.autorizacaoUtils = autorizacaoUtils;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FacesContext getFacesContext(final ServletRequest request,
			final ServletResponse response) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			return facesContext;
		}

		FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder
				.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
		LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder
				.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		Lifecycle lifecycle = lifecycleFactory
				.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

		ServletContext servletContext = ((HttpServletRequest) request)
				.getSession().getServletContext();
		facesContext = contextFactory.getFacesContext(servletContext, request,
				response, lifecycle);
		InnerFacesContext.setFacesContextAsCurrentInstance(facesContext);
		if (null == facesContext.getViewRoot()) {
			facesContext.setViewRoot(new UIViewRoot());
		}

		return facesContext;
	}

	private abstract static class InnerFacesContext extends FacesContext {
		protected static void setFacesContextAsCurrentInstance(
				final FacesContext facesContext) {
			FacesContext.setCurrentInstance(facesContext);
		}
	}

}
