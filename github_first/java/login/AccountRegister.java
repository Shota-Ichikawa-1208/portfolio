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
 * Servlet implementation class Accountregister
 */
@WebServlet("/AccountRegister")
public class AccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegister() {
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
		//新規登録ページから登録情報を受け取る
		String name = req.getParameter("name");
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		
		//beansにセット
		AccountBeans newAccount = new AccountBeans();
		newAccount.setLoginId(loginId);
		newAccount.setPassword(password);
		newAccount.setName(name);
		
		AccountRegisterDAO arDAO = new AccountRegisterDAO();
		int result = arDAO.insert(newAccount);
		
		HttpSession session = req.getSession();
		if(result != 0) {
			msg = "アカウント登録が成功しました";
			
			session.setAttribute("msg", msg);
			session.setAttribute("User",newAccount);
			RequestDispatcher rd = req.getRequestDispatcher("Register_Success.jsp");
			rd.forward(req, res);
		}else {
			msg = "アカウント登録が失敗しました";
			session.setAttribute("msg", msg);
			RequestDispatcher rd = req.getRequestDispatcher("Register_miss.jsp");
			rd.forward(req, res);
		}
		
		
		
	}

	
}
