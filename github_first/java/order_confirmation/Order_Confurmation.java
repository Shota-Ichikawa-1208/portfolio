package order_confirmation;

import java.io.IOException;
import java.util.ArrayList;

import beans.AccountBeans;
import beans.Order_ProductBeans;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Order_Confurmation
 */
@WebServlet("/Order_Confurmation")
public class Order_Confurmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order_Confurmation() {
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
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		int totalResult = 0;
		
		//セッションスコープ内の注文情報とユーザー情報を取得
		AccountBeans User = (AccountBeans)session.getAttribute("User");
		ArrayList<Order_ProductBeans> cart_list = (ArrayList<Order_ProductBeans>)session.getAttribute("cart_list");
		
		//ログインしていない場合ログインページに飛ばす
		if(User == null) {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req,res);
		}
		//カート内に商品があるかを判定
		if(cart_list == null || cart_list.size() == 0) {
			RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
			rd.forward(req,res);
		}
		//データベースに登録する情報の取得
		String loginId = User.getLoginId();
		
		//DAOクラスへ
		Order_ConfimationDAO ocDAO = new Order_ConfimationDAO();
		totalResult = ocDAO.insert(loginId,cart_list);
		
		//更新結果の判定メッセージをリクエスト属性に保存
		String msg ;
		if(totalResult > 0) {
			 msg = totalResult + "件のご注文が確定しました‼";
		}else if(totalResult == -1) {
			
			String ermsg = ocDAO.getErrorMsg();
			 msg = "ご注文が失敗しました。"+ ermsg;
			 cart_list.clear();
			 session.setAttribute("cart_list", cart_list);
		}else {
			msg = "ご注文が失敗しました。";
		}
		session.setAttribute("msg", msg);
		RequestDispatcher rd = req.getRequestDispatcher("order_result.jsp");
		rd.forward(req, res);
		}
		
	}


