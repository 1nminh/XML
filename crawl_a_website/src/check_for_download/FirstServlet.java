package check_for_download;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
  
  
public class FirstServlet extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
          
   
    String remoteAddr = request.getRemoteAddr();
    ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
    reCaptcha.setPrivateKey("6Ld8LQ8UAAAAALhmZ0OlkhHrw5WdNRH-LTBJZImt");

    String challenge = request.getParameter("recaptcha_challenge_field");
    String uresponse = request.getParameter("recaptcha_response_field");
    ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

    if (reCaptchaResponse.isValid()) {
      out.print("download started!!");
      
      out.println("<script type='text/javascript'>");
		out.println("window.location.href='http://f06.wapka-files.com/download/b/b/4/36414_bb4389fbf2469ae35763db47.mp3/c1bf4739a0ee3f1d49d2/Kanne_En_Kanmaniye_%28Shareef144.com%29.mp3';");
		out.println("</script>");
      
      
    } else {
    	HttpSession session=request.getSession();  
        session.setAttribute("message","Invalid Captcha");  
      
        out.println("<script type='text/javascript'>");
		out.println("window.location.href='http://localhost:8080/recapthca_for_download/'");
		out.println("</script>");
    }
    
    out.println("FINISH");   
    out.close();  
    }  
}  