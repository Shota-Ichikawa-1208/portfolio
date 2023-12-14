

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
 * Servlet implementation class Product_Delete_Servlet
 */
@WebServlet("/Product_Delete_Servlet")
public class Product_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product_Delete_Servlet() {
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
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession(true);
		
		//セッションスコープから現在のカートの情報を取得
		ArrayList<Order_ProductBeans> cart_list  = (ArrayList<Order_ProductBeans>)session.getAttribute("cart_list");
		//削除したい商品のカートリストでのindex番号を取得
		int cart_index = Integer.parseInt(req.getParameter("cart_index"));
		//cart_listから削除
		cart_list.remove(cart_index);
		//cart_listをセッションスコープへセット
		session.setAttribute("cart_list", cart_list);
		//jspへ投げる
		RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
		rd.forward(req, res);
	}

}
