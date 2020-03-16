package com.es.phoneshop.web;

import com.es.phoneshop.model.Cart;
import com.es.phoneshop.service.CartService;
import com.es.phoneshop.service.impl.HttpSessionCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartItemDeleteServlet extends HttpServlet {

    CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = HttpSessionCartService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = getProductId(req);
        Cart cart = cartService.getCart(req);
        cartService.delete(cart, productId);

        resp.sendRedirect(req.getContextPath() + "/cart?message=Cart item deleted successfully");
    }

    private long getProductId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        return Long.parseLong(pathInfo.split("/")[1]);
    }
}
