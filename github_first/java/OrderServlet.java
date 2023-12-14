

import java.io.IOException;
import java.util.ArrayList;

import beans.Order_ProductBeans;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(true);
		Order_ProductBeans rb;
		ArrayList<Order_ProductBeans> cart_list = (ArrayList<Order_ProductBeans>)session.getAttribute("cart_list");
		req.setCharacterEncoding("utf-8");
		
		if(cart_list == null) {
			cart_list = new ArrayList<Order_ProductBeans>();
			req.setCharacterEncoding("utf-8");
			rb = new Order_ProductBeans(req.getParameter("size"),req.getParameter("quantity"));
			//商品判別コードのみを引数とする
			rb.setProduct_code(req.getParameter("product_color").substring(0,1));
			//商品名の最初の製品判別コードを除いたものを引数とする
			rb.setProduct_color(req.getParameter("product_color").substring(1));
			//商品の単価をセット
			rb.setProduct_price(req.getParameter("product_color").substring(1));
			cart_list.add(rb);
		}else {
			req.setCharacterEncoding("utf-8");
			rb = new Order_ProductBeans(req.getParameter("size"),req.getParameter("quantity"));
			//商品判別コードのみを引数とする
			rb.setProduct_code(req.getParameter("product_color").substring(0,1));
			//商品名の最初の製品判別コードを除いたものを引数とする
			rb.setProduct_color(req.getParameter("product_color").substring(1));
			//商品の単価をセット
			rb.setProduct_price(req.getParameter("product_color").substring(1));
			cart_list.add(rb);
		}
		session.setAttribute("cart_list", cart_list);
		RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
		rd.forward(req,res);
	}

}
