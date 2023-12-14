package login;

import java.io.IOException;

import beans.AccountBeans;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountSerch
 */
@WebServlet("/AccountSerch")
public class AccountSerch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSerch() {
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
		String msg;
		
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		
		AccountSerchDAO asDAO = new AccountSerchDAO();
		AccountBeans User = asDAO.serch(loginId, password);
		
		String l = User.getLoginId();
		HttpSession session = req.getSession();
		if(l != null) {
			msg = "ログインが成功しました";
			session.setAttribute("msg", msg);
			session.setAttribute("User",User);
			RequestDispatcher rd = req.getRequestDispatcher("MyPage.jsp");
			rd.forward(req, res);			
		}else {
			msg = "ログインに失敗しました";
			session.setAttribute("msg", msg);
			RequestDispatcher rd = req.getRequestDispatcher("Login_miss.jsp");
			rd.forward(req, res);			
		}
	}

}
