package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class shoppingCart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_varStatus_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_varStatus_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_varStatus_var_items.release();
    _jspx_tagPool_c_if_test.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"utf-8\">\n");
      out.write("\t\t<meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n");
      out.write("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\t\t<title>Basket</title\n");
      out.write("\t\t<script src=\"cartJS.js\"></script>\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"DECOR/shoppingCart.css\">\n");
      out.write("\t\t<script>\n");
      out.write("\t\t\tfunction checkRemove() {\n");
      out.write("\t\t\t\tvar check = confirm(\"Do You Want To Remove This Product From Cart?\");\n");
      out.write("\t\t\t\tif (check === true)\n");
      out.write("\t\t\t\t\treturn true;\n");
      out.write("\t\t\t\telse\n");
      out.write("\t\t\t\t\treturn false;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t</script>\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("\t<body>\n");
      out.write("\t\t<main>\n");
      out.write("\t\t\t<div class=\"basket\">\n");
      out.write("\t\t\t\t<form action=\"MainController?page=\" method=\"POST\">\n");
      out.write("\t\t\t\t\t<div class=\"basket-module\">\n");
      out.write("\t\t\t\t\t\t<button class=\"promo-code-cta\">Back</button>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t\t<div class=\"basket-labels\">\n");
      out.write("\t\t\t\t\t<ul>\n");
      out.write("\t\t\t\t\t\t<li class=\"item item-heading\">Item</li>\n");
      out.write("\t\t\t\t\t\t<li class=\"price\">Price</li>\n");
      out.write("\t\t\t\t\t\t<li class=\"quantity\">Quantity</li>\n");
      out.write("\t\t\t\t\t\t<li class=\"quantity\">Update Quantity</li>\n");
      out.write("\t\t\t\t\t\t<li class=\"sub-total\">Subtotal</li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<form action=\"MainController\" method=\"POST\">\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<aside>\n");
      out.write("\t\t\t\t<div class=\"summary\">\n");
      out.write("\t\t\t\t\t<div class=\"summary-total-items\"><span class=\"total-items\"></span> Items in your Bag</div>\n");
      out.write("\t\t\t\t\t<div class=\"summary-total\">\n");
      out.write("\t\t\t\t\t\t<div class=\"total-title\">Total</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"total-value final-value\" id=\"basket-total\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.totalPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</aside>\n");
      out.write("\t\t</form>\n");
      out.write("\t</main>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_varStatus_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.shoppingCart.cart.values()}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("dto");
    _jspx_th_c_forEach_0.setVarStatus("counter");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t<script>\n");
          out.write("\t\t\t\t\t\t\tfunction updatePrice");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("() {\n");
          out.write("\t\t\t\t\t\t\t\tvar newQuantity = Number(document.getElementById(\"quantity");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\").value);\n");
          out.write("\t\t\t\t\t\t\t\tvar price = Number(document.getElementById(\"price");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\").innerHTML);\n");
          out.write("\t\t\t\t\t\t\t\tvar totalPrice = Number(document.getElementById(\"basket-total\").innerHTML);\n");
          out.write("\t\t\t\t\t\t\t\t\n");
          out.write("\t\t\t\t\t\t\t\tvar oldQuantity = price/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(";\n");
          out.write("\t\t\t\t\t\t\t\tvar newPrice = price - (oldQuantity*");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(") + newQuantity*");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(";\n");
          out.write("\t\t\t\t\t\t\t\tdocument.getElementById(\"price");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\").innerHTML = newPrice.toString();\n");
          out.write("\t\t\t\t\t\t\t\ttotalPrice = totalPrice - price + newPrice;\n");
          out.write("\t\t\t\t\t\t\t\tdocument.getElementById(\"basket-total\").innerHTML = totalPrice.toString();\n");
          out.write("\t\t\t\t\t\t\t}\n");
          out.write("\t\t\t\t\t\t</script>\n");
          out.write("\t\t\t\t\t\t<div class=\"basket-product\">\n");
          out.write("\t\t\t\t\t\t\t<div class=\"item\">\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"product-image\">\n");
          out.write("\t\t\t\t\t\t\t\t\t<img src=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.picture}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" alt=\"Placholder Image 2\" class=\"product-frame\">\n");
          out.write("\t\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"product-details\">\n");
          out.write("\t\t\t\t\t\t\t\t\t<h1><strong> ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(" </strong></h1>\n");
          out.write("\t\t\t\t\t\t\t\t\t<p><strong>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.cateID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</strong></p>\n");
          out.write("\t\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t<div class=\"price\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</div>\n");
          out.write("\t\t\t\t\t\t\t<div class=\"quantity\">\n");
          out.write("\t\t\t\t\t\t\t\t<input id=\"quantity");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" type=\"number\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.quantity}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" min=\"1\" max=\"1000\" name=\"txtCartQuan\" onchange=\"updatePrice");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("()\" class=\"quantity-field\">\n");
          out.write("\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t<div class=\"quantity\">\n");
          out.write("\t\t\t\t\t\t\t\t<a href=\"MainController?action=updateQuantity\">Update Quantity</a>\n");
          out.write("\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t<div>\n");
          out.write("\t\t\t\t\t\t\t\t");
          if (_jspx_meth_c_if_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
            return true;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t\t<div id=\"price");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" class=\"sub-total\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</div>\n");
          out.write("\t\t\t\t\t\t\t<div class=\"remove\">\n");
          out.write("\t\t\t\t\t\t\t\t<a href=\"MainController?action=Remove&txtProductID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dto.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" onclick=\"return checkRemove()\">Remove</a>\n");
          out.write("\t\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t\t</div>\n");
          out.write("\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_varStatus_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_if_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty requestScope.OutOfStock and requestScope.StockProduct eq dto.productID}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t<h4>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.OutOfStock}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</h4>\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty sessionScope.shoppingCart.cart.values()}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t<div class=\"summary-checkout\">\n");
        out.write("\t\t\t\t\t\t\t<button class=\"checkout-cta\" name=\"action\" value=\"Checkout\">Checkout</button>\n");
        out.write("\t\t\t\t\t\t</div>\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }
}
